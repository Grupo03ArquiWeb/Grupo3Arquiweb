package pe.edu.upc.wasiseguro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class WasiseguroApplication {

    public static void main(String[] args) {
        SpringApplication.run(WasiseguroApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(JdbcTemplate jdbcTemplate) {
        return args -> {
            jdbcTemplate.execute("DO $$ " +
                    "BEGIN " +
                    "    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='usuario' AND column_name='idioma') THEN " +
                    "        ALTER TABLE usuario ADD COLUMN idioma VARCHAR(5) DEFAULT 'es'; " +
                    "    END IF; " +
                    "    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='usuario' AND column_name='frecuencia_alertas') THEN " +
                    "        ALTER TABLE usuario ADD COLUMN frecuencia_alertas VARCHAR(20) DEFAULT 'INSTANTE'; " +
                    "    END IF; " +
                    "    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='usuario' AND column_name='silenciado_hasta') THEN " +
                    "        ALTER TABLE usuario ADD COLUMN silenciado_hasta TIMESTAMP; " +
                    "    END IF; " +
                    "END $$;");
        };
    }
}