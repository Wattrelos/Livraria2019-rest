package fatec.layer10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.domain.TendEndereco;


@Repository
public interface TEnderecoRepository extends JpaRepository<TendEndereco, Integer>{

}
