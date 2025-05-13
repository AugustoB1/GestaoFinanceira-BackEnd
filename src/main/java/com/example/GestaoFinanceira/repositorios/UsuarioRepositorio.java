package com.example.GestaoFinanceira.repositorios;

import com.example.GestaoFinanceira.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

    Usuario findByEmail(String email);

    Usuario findByCpf(String cpf);


}
