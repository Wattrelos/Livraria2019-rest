package fatec.layer10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.domain.Logradouro;


@Repository
public interface LogradouroRepository extends JpaRepository<Logradouro, Integer>{

}
