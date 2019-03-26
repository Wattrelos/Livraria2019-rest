package fatec.layer10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.domain.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer>{

}
