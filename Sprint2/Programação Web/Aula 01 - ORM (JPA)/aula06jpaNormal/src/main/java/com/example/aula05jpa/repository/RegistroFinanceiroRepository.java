package com.example.aula05jpa.repository;

import com.example.aula05jpa.entity.RegistroFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroFinanceiroRepository extends JpaRepository<RegistroFinanceiro, Integer> {

}