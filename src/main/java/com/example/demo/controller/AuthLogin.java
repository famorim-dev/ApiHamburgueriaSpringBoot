package com.example.demo.controller;

import com.example.demo.dto.AuthRegistroDTO;
import com.example.demo.dto.AuthenticationDTO;
import com.example.demo.dto.EsqueceuSenhaDTO;
import com.example.demo.model.Role;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.seguranca.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthLogin {

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usuarioSenha = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = authenticationManager.authenticate(usuarioSenha);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(token);
    }

    @PostMapping("/registro")
    public ResponseEntity registroUsuario(@RequestBody @Valid AuthRegistroDTO data){
        if(this.usuarioRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String senhaCriptografada = new BCryptPasswordEncoder().encode(data.senha());
        Usuario usuario = new Usuario(data.nome(), data.email(), senhaCriptografada, Role.USER);

        this.usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/esqueceu")
    public ResponseEntity esqueceuSenha(@RequestBody @Valid EsqueceuSenhaDTO data){
        Usuario usuario = (Usuario) this.usuarioRepository.findByEmail(data.email());
        if (usuario == null) return ResponseEntity.notFound().build();

        if (!usuario.getEmail().equalsIgnoreCase(data.email())) return ResponseEntity.ok().build();

        return ResponseEntity.ok().build();
    }
}
