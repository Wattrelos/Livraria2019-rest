package fatec.layer10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.domain.LojaMatriz;


@Repository
public interface LojaRepository extends JpaRepository<LojaMatriz, Integer>{

}