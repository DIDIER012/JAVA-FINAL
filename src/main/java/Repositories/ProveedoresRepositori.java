package Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Models.ProveedoresModel;

@Repository
public interface ProveedoresRepositori extends JpaRepository<ProveedoresModel, Long>{

}
