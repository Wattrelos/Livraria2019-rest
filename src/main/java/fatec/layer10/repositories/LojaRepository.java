package fatec.layer10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.domain.Loja;


@Repository
public interface LojaRepository extends JpaRepository<Loja, Integer>{

}