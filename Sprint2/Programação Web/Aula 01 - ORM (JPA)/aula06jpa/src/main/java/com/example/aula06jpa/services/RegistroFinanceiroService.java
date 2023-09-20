package com.example.aula06jpa.services;

import com.example.aula06jpa.dtos.RegistroFinanceiroDTO;
import com.example.aula06jpa.entity.RegistroFinanceiro;
import com.example.aula06jpa.repositories.RegistroFinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroFinanceiroService {

    @Autowired
    private RegistroFinanceiroRepository registroFinanceiroRepository;

    public RegistroFinanceiro criarRegistroFinanceiro(RegistroFinanceiroDTO data) {
        RegistroFinanceiro newRegistroFinanceiro = new RegistroFinanceiro(data);
        this.saveRegistroFinanceiro(newRegistroFinanceiro);
        return registroFinanceiroRepository.save(newRegistroFinanceiro);
    }

    public List<RegistroFinanceiro> getAllRegistrosFinanceiros() {
        return registroFinanceiroRepository.findAll();
    }

    public RegistroFinanceiro findRegistroFinanceiroById(Long id) throws Exception {
        return registroFinanceiroRepository.findById(id).orElseThrow(() -> new Exception("Registro Financeiro não encontrado"));
    }

    public RegistroFinanceiro updateRegistroFinanceiro(Long id, RegistroFinanceiro data) throws Exception {
        RegistroFinanceiro newRegistroFinanceiro = this.findRegistroFinanceiroById(id);
        newRegistroFinanceiro.setDescricao(data.getDescricao());
        newRegistroFinanceiro.setValor(data.getValor());
        newRegistroFinanceiro.setData(data.getData());

        this.saveRegistroFinanceiro(newRegistroFinanceiro);
        return newRegistroFinanceiro;
    }

    public List<RegistroFinanceiro> getAllRegistrosFinanceirosPositivos() {
        return registroFinanceiroRepository.findAllByValorGreaterThan(0);
    }

    public List<RegistroFinanceiro> getAllRegistrosFinanceirosNegativos() {
        return registroFinanceiroRepository.findAllByValorLessThan(0);
    }

    public void deleteRegistroFinanceiro(RegistroFinanceiro registroFinanceiro) {
        registroFinanceiroRepository.delete(registroFinanceiro);
    }

    public void saveRegistroFinanceiro(RegistroFinanceiro registroFinanceiro) {
        registroFinanceiroRepository.save(registroFinanceiro);
    }
}
