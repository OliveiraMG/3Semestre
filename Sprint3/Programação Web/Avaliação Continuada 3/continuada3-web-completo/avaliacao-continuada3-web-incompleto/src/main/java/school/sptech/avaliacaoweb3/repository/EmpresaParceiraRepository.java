package school.sptech.avaliacaoweb3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.avaliacaoweb3.entity.EmpresaParceira;

public interface EmpresaParceiraRepository extends JpaRepository<EmpresaParceira, Integer> {
    EmpresaParceira findByCnpj(String cnpj);
}
