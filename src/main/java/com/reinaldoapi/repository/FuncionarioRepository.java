package com.reinaldoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reinaldoapi.domain.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

}
