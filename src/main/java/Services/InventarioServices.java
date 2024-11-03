package Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import InventarioDto.InventarioDto;
import InventarioMapper.InventarioMapper;
import Models.InventarioModel;
import Repositories.InventarioRepositori;

@Service
public class InventarioServices {

    @Autowired
    private InventarioRepositori inventarioRepositori;

    public List<InventarioModel> getAllNombres() {
        return inventarioRepositori.findAll();
    }


    public void creandoInventario(InventarioModel inventario) {
        this.inventarioRepositori.save(inventario);
    }


    public boolean borrarInventario(InventarioModel invetario) {
        if(this.inventarioRepositori.existsById(invetario.getId())) {
            this.inventarioRepositori.delete(invetario);
            return true;
        } else {
            System.err.println("El cliente que quieres eliminar no existe!");
            return false;
        }
    }


    public void actualizarInventario(InventarioModel inventario) { 
        if (this.inventarioRepositori.existsById(inventario.getId())) {  
            this.inventarioRepositori.save(inventario);  
        } else {  
            System.err.println("El inventario que quieres actualizar no existe!");  
        }  
    }  
    
    public boolean existePorId(Long id) {  
        return this.inventarioRepositori.existsById(id);  
    }



    public List<InventarioDto> getAllInventarios() {
            return inventarioRepositori.findAll().stream()
                .map(InventarioMapper::toDTO) 
                .collect(Collectors.toList());
        }
}
