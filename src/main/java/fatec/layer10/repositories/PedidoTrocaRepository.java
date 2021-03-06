package fatec.layer10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fatec.domain.PedidoTroca;

@Repository
public interface PedidoTrocaRepository extends JpaRepository<PedidoTroca, Integer>{

}