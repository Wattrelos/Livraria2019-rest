package fatec.layer10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.domain.Endereco;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{

}
