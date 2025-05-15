package com.example.GestaoFinanceira.controladores;

import com.example.GestaoFinanceira.entidades.Usuario;
import com.example.GestaoFinanceira.repositorios.UsuarioRepositorio;
import com.example.GestaoFinanceira.servicos.CadastrarServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping
public class UsuarioControlador {

    @Autowired
    UsuarioRepositorio repositorio;
    @Autowired
    CadastrarServico servico;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario) {
        if(repositorio.findByEmail(usuario.getEmail()) != null) {
            return ResponseEntity.badRequest().body(("Email já cadastrado"));
        }
        if (repositorio.findByCpf(usuario.getCpf()) != null) {
            return ResponseEntity.badRequest().body(("Cpf já cadastrado"));
        }
        Usuario usuario1 = servico.cadastrar(usuario);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(usuario1.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario1);
    }
}
