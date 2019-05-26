package fatec.layer10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.domain.Estatus;


@Repository
public interface EstatusRepository extends JpaRepository<Estatus, Integer>{

}
