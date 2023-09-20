package com.example.aula06jpa.repositories;

import com.example.aula06jpa.entity.RegistroFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegistroFinanceiroRepository extends JpaRepository<RegistroFinanceiro, Long> {

    List<RegistroFinanceiro> findAllByValorGreaterThan(double valor);

    List<RegistroFinanceiro> findAllByValorLessThan(double valor);

    Optional<RegistroFinanceiro> findById(Long id);
}
