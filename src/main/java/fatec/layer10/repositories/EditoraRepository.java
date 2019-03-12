package fatec.layer10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.domain.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Integer>{

}