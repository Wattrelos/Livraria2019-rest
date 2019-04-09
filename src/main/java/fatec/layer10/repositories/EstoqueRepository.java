package fatec.layer10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.domain.Estoque;


@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Integer>{

}
