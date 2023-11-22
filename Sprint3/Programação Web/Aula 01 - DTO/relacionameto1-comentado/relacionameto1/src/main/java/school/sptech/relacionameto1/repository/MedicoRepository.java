package school.sptech.relacionameto1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.relacionameto1.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
}
