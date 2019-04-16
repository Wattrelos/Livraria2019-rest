package fatec.layer10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.domain.Logradouro;


@Repository
public interface TEnderecoRepository extends JpaRepository<Logradouro, Integer>{

}
