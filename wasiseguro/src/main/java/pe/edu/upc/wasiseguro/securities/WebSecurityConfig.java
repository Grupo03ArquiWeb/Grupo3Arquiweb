package pe.edu.upc.wasiseguro.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver exceptionResolver;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers(
                                "/login",
                                "/facebook",
                                "/api/usuario/crear",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/error"
                        ).permitAll()

                        .requestMatchers(HttpMethod.GET,
                                "/api/rol/listar",
                                "/api/rol/buscarnombre",
                                "/api/rol/buscarporactivo"
                        ).hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERADOR")
                        .requestMatchers(HttpMethod.GET,
                                "/api/usuario/estadisticas/estado",
                                "/api/usuario/estadisticas/idioma"
                        ).hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERADOR")
                        .requestMatchers(HttpMethod.POST, "/api/rol/crear").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/rol/actualizar/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/rol/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/rol/usuariosporol").hasAuthority("ROLE_ADMIN")

                        .requestMatchers(HttpMethod.PUT,
                                "/api/usuario/actualizar/**"
                        ).hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PATCH,
                                "/api/usuario/*/contacto-confianza"
                        ).hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERADOR", "ROLE_USER")
                        .requestMatchers(HttpMethod.GET,
                                "/api/usuario/*/contacto-confianza"
                        ).hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERADOR")
                        .requestMatchers(HttpMethod.GET,
                                "/api/usuario/listar",
                                "/api/usuario/buscarnombre",
                                "/api/usuario/buscarporrol",
                                "/api/usuario/buscarpordominio"
                        ).hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERADOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/usuario/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/usuario/inactivos").hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERADOR")

                        .requestMatchers(HttpMethod.POST,
                                "/api/EventoPanico/crear"
                        ).hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERADOR", "ROLE_USER")
                        .requestMatchers(HttpMethod.GET,
                                "/api/EventoPanico/buscarporusuario"
                        ).hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERADOR")
                        .requestMatchers(HttpMethod.GET,
                                "/api/EventoPanico/listar",
                                "/api/EventoPanico/buscarporatendido"
                        ).hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERADOR")
                        .requestMatchers(HttpMethod.PUT,
                                "/api/EventoPanico/actualizar/**"
                        ).hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE,
                                "/api/EventoPanico/**"
                        ).hasAuthority("ROLE_ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/api/incidentes/eliminar/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/alertas/**").hasAuthority("ROLE_ADMIN")

                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/usuario/crear").permitAll()

                        .requestMatchers(
                                "/api/incidentes/crear",
                                "/api/incidentes/listar",
                                "/api/incidentes/reporte-cantidades",
                                "/api/incidentes/buscartipo",
                                "/api/incidentes/buscarestado"
                        ).hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers("/api/alertas/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers("/api/zonas-favoritas/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")

                        // PLAN SUSCRIPCION
                        .requestMatchers(HttpMethod.POST, "/api/planSuscripcion/registrar").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/planSuscripcion/actualizar").hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERADOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/planSuscripcion/eliminar/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/planSuscripcion/listar").hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERADOR")
                        .requestMatchers(HttpMethod.GET, "/api/planSuscripcion/*").hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERADOR")

                        // SUSCRIPCIONES
                        .requestMatchers(HttpMethod.POST, "/api/suscripciones/registrar").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/suscripciones/actualizar/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERADOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/suscripciones/eliminar/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/suscripciones/listar").hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERADOR")
                        .requestMatchers(HttpMethod.GET, "/api/suscripciones/filtrar/estado/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERADOR")
                        .requestMatchers(HttpMethod.GET, "/api/suscripciones/filtrar/fechas").hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERADOR")
                        .requestMatchers(HttpMethod.GET, "/api/suscripciones/estadisticas/estado").hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERADOR")
                        .requestMatchers(HttpMethod.GET, "/api/suscripciones/estadisticas/plan").hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERADOR")
                        .requestMatchers(HttpMethod.GET, "/api/suscripciones/*").hasAnyAuthority("ROLE_ADMIN", "ROLE_MODERADOR")

                        .requestMatchers(HttpMethod.POST, "/api/tipoIncidente/registrar").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/tipoIncidente/actualizar").hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/tipoIncidente/eliminar/**").hasAuthority("ROLE_ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/suscripciones/vincular-plan").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}