package fatec.layer10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import fatec.domain.Subcategoria;

@Repository
public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Integer>{

}
