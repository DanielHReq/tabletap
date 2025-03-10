package br.com.tabletap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tabletap.model.AuthenticationDTO;
import br.com.tabletap.model.Cliente;
import br.com.tabletap.model.LoginResponseDTO;
import br.com.tabletap.model.RegisterDTO;
import br.com.tabletap.model.Usuario;
import br.com.tabletap.repository.UsuarioRepository;
import br.com.tabletap.security.TokenService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AuthenticationDTO data){

        // as senhas do usuário serão armazenadas como HASH
        // assim estarão criptografadas e não podem ser diretamente acessadas

        // atualizar para permitir senha nula em caso de Cliente
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());

        try {
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((Usuario)auth.getPrincipal());

            System.out.println("AuthControllerToken: " + token);

            return ResponseEntity.ok(new LoginResponseDTO(token));

        } catch (Exception e) {
            System.out.println("Erro:  ");
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }

        
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        
        // se encontra um usuário já cadastrado com o conteúdo de 'data', retorna badRequest
        if (this.usuarioRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        
        Usuario newUser;
        String encryptedPassword;
        // login é o celular
        // se o papel é USER, não é necessário utilizar senha: padrão 'default'
        switch (data.role()) {
            case USER:
                encryptedPassword = new BCryptPasswordEncoder().encode("default");
                newUser = new Cliente(data.login(), encryptedPassword, data.role());
                break;
            default:
                encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
                newUser = new Usuario(data.login(), encryptedPassword, data.role());
                break;
        }
        
        this.usuarioRepository.save(newUser);
        
        return ResponseEntity.ok().build();
    }
}
