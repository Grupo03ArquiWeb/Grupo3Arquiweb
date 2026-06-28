package pe.edu.upc.wasiseguro.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pe.edu.upc.wasiseguro.entities.Usuario;
import pe.edu.upc.wasiseguro.entities.Rol;
import pe.edu.upc.wasiseguro.repositories.IRolRepository;
import pe.edu.upc.wasiseguro.securities.JwtTokenUtil;
import pe.edu.upc.wasiseguro.servicesinterfaces.IFacebookService;
import pe.edu.upc.wasiseguro.servicesinterfaces.IUsuarioService;

import java.util.Map;
import java.util.UUID;

@Service
public class FacebookServiceImplement implements IFacebookService {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRolRepository rolRepository;

    @Override
    public String loginWithFacebook(String fbToken) {
        // 1. Preguntamos a Facebook si el token es real y pedimos el email
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://graph.facebook.com/me?fields=id,name,email&access_token=" + fbToken;

        try {
            Map<String, String> response = restTemplate.getForObject(url, Map.class);
            String email = response.get("email");
            String nombre = response.get("name");

            // 2. Buscamos si el usuario ya existe en nuestra base de datos
            Usuario usuario = usuarioService.buscarPorEmail(email);

            // 3. Si no existe, lo registramos automáticamente
            if (usuario == null) {
                usuario = new Usuario();
                usuario.setEmail(email);

                // Como Facebook devuelve todo el nombre junto, lo dividimos para  BD
                String[] partesNombre = nombre.split(" ", 2);
                usuario.setNombre(partesNombre[0]);
                usuario.setApellido(partesNombre.length > 1 ? partesNombre[1] : "Usuario Facebook");

                // Usamos setPasswordHash como requiere tu entidad Usuario
                usuario.setPasswordHash(passwordEncoder.encode(UUID.randomUUID().toString()));

                // --- ASIGNACIÓN DE ROL ---
                // Asignamos el ID 3 que corresponde al rol de Usuario normal en tu base de datos
                //1 = ADMIN 2 = MODERADOR 3 = USER
                //DEBES BORRAR AL USUARIO DE LA BD PARA LUEGO CAMBIAR EL ROL
                //Solo afectará a usuarios nuevos: Este cambio no afectará a los usuarios que ya se registraron previamente con el ID 3.
                // Si tú ya te registraste como Fabian con Facebook, tu usuario ya existe en la base de datos con el rol 3.
                // Para probar este cambio, debes borrar tu usuario de la tabla de usuarios en tu base de datos (o usar un correo nuevo para
                // registrarte por primera vez) y así verás que ahora el sistema te asigna el rol 1. LO DEJO COMO ROL 3 POR AHORA usuario
                Rol rolBasico = rolRepository.findById(1).orElse(null);
                usuario.setRol(rolBasico);

                usuarioService.insert(usuario);
            }

            // 4. Generamos el JWT oficial de WasiSeguro y lo devolvemos
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            return jwtTokenUtil.generateToken(userDetails);

        } catch (Exception e) {
            throw new RuntimeException("Error validando el token con Facebook", e);
        }
    }
}