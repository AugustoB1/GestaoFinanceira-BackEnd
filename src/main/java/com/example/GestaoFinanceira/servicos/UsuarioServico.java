package com.example.GestaoFinanceira.servicos;

import com.example.GestaoFinanceira.entidades.Usuario;
import com.example.GestaoFinanceira.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServico {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    public Usuario cadastrar(Usuario user){
        String criptografando = this.passwordEncoder.encode(user.getSenha());
        user.setSenha(criptografando);
        Usuario usuario = new Usuario(user.getNome(), user.getEmail(), user.getCpf(), user.getSenha());
        return usuarioRepositorio.save(usuario);
    }

    public Usuario login(String email, String senha){
        Usuario usuario = usuarioRepositorio.findByEmail(email);
        if(usuario != usuarioRepositorio && validarSenha(usuario, senha)){
            return usuario;
        }
        throw new RuntimeException();
    }

    public boolean validarSenha(Usuario usuario, String senha){
        return  passwordEncoder.matches(senha, usuario.getSenha());
    }
}
