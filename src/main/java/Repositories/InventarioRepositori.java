package Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Models.InventarioModel;

@Repository
public interface InventarioRepositori extends JpaRepository<InventarioModel, Long>{

}
