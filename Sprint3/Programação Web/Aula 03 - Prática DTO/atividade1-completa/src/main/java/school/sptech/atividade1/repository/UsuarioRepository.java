package school.sptech.atividade1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.atividade1.entity.Usuario;

// NAO ALTERAR ESTE ARQUIVO
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
