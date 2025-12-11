package com.example.demo.service.seguranca;

import com.example.demo.model.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioLogadoService {

    public Usuario getUsuarioLogado(){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getPrincipal().equals("anonymousUser")) {
            throw new RuntimeException("Usuário não autenticado");
        }
        return (Usuario) auth.getPrincipal();
    }
}
