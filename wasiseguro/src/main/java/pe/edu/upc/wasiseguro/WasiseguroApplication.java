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
            String GREEN = "\u001B[32m";
            String RESET = "\u001B[0m";

            try {
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

                System.out.println("\n" + GREEN + "==========================================================");
                System.out.println("🚀 WASISEGURO API: ¡SISTEMA INICIADO Y SINCRONIZADO!");
                System.out.println("✅ BASE DE DATOS: Estructura de tablas actualizada.");
                System.out.println("🔗 SWAGGER LOCAL: http://localhost:8080/swagger-ui.html");
                System.out.println("                            :D                            ");
                System.out.println("==========================================================" + RESET + "\n");

            } catch (Exception e) {
                System.out.println("\n\u001B[31m" + "❌ ERROR DE INICIO: Error en la base de datos." + RESET);
                System.err.println("Detalle técnico: " + e.getMessage());
            }
        };
    }
}
