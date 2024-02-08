package school.sptech.avaliacaoweb3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.avaliacaoweb3.entity.Estagiario;

import java.util.List;

public interface EstagiarioRepository extends JpaRepository<Estagiario, Integer>{
    Estagiario findByCpf(String cpf);
    List<Estagiario> findAllByEmpresaParceiraNome(String nome);
    Estagiario findByEmpresaParceiraId(Integer id);
}