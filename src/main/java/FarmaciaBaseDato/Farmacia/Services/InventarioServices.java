package FarmaciaBaseDato.Farmacia.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import FarmaciaBaseDato.Farmacia.InventarioDto.InventarioDto;
import FarmaciaBaseDato.Farmacia.Models.InventarioModel;
import FarmaciaBaseDato.Farmacia.InventarioMapper.InventarioMapper;

@Service
public class InventarioServices {

    @Autowired
    private FarmaciaBaseDato.Farmacia.Repositories.InventarioRepository InventarioRepository;


    public List<InventarioModel> getAllNombres() {
        return InventarioRepository.findAll();
    }


    public void creandoInventario(InventarioModel inventario) {
        this.InventarioRepository.save(inventario);
    }


    public boolean borrarInventario(InventarioModel invetario) {
        if(this.InventarioRepository.existsById(invetario.getId())) {
            this.InventarioRepository.delete(invetario);
            return true;
        } else {
            System.err.println("El cliente que quieres eliminar no existe!");
            return false;
        }
    }


    public void actualizarInventario(InventarioModel inventario) { 
        if (this.InventarioRepository.existsById(inventario.getId())) {  
            this.InventarioRepository.save(inventario);  
        } else {  
            System.err.println("El inventario que quieres actualizar no existe!");  
        }  
    }  
    
    public boolean existePorId(Long id) {  
        return this.InventarioRepository.existsById(id);  
    }



    public List<InventarioDto> getAllInventarios() {
        List<InventarioDto> collect = InventarioRepository.findAll().stream()
                .map(InventarioMapper::toDTO)
                .collect(Collectors.toList());
        return collect;
        }
}
