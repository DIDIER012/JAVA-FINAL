package FarmaciaBaseDato.Farmacia.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import FarmaciaBaseDato.Farmacia.Models.InventarioModel;



@Repository
public interface InventarioRepository extends JpaRepository<InventarioModel, Long>{

}
