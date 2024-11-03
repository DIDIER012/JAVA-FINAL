package Controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import InventarioDto.InventarioDto;
import Models.InventarioModel;
import Services.InventarioServices;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/inventario")
public class InventarioControlers {
    @Autowired
    private InventarioServices inventarioServices;

    @GetMapping("/All")
    public  List<InventarioModel> getAllInventarioModel() {
        return this.inventarioServices.getAllNombres();
    }
    

    @GetMapping("/Busqueda")  
    public ResponseEntity<List<InventarioDto>> getAllInventarios() {  
        List<InventarioDto> inventarios = inventarioServices.getAllInventarios();  
        return ResponseEntity.ok(inventarios);  
    }  



    @GetMapping("/{id}")
    public ResponseEntity<Boolean> existeProducto(@PathVariable Long id) {
    boolean existe = inventarioServices.existePorId(id);
    
    if (existe) {
        return new ResponseEntity<>( HttpStatus.OK); 
    } else {
        System.err.println("¡El usuario que buscas no existe!");
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND); 
    }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> agregarInventario(@RequestBody InventarioModel inventario) {
        try {
        inventarioServices.creandoInventario(inventario);
        return ResponseEntity.ok(inventario); 
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Error creating inventory: " + e.getMessage());
    }
    }


    @DeleteMapping("/borrar/{id}")  
    public ResponseEntity<String> borrarInventario(@PathVariable Long id) {  
        
        InventarioModel inventario = new InventarioModel();  
        inventario.setId(id);  
        
        
        boolean  eliminado = inventarioServices.borrarInventario(inventario);  
        
        if (eliminado) {  
            return ResponseEntity.ok("Inventario eliminado con éxito.");  
        } else {  
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El inventario que quieres eliminar no existe.");  
        }  
    }  



    @PutMapping("/actualizar/{id}")  
public ResponseEntity<String> actualizarInventario(@PathVariable Long id, @RequestBody InventarioModel inventarioActualizado) {   
    if (!inventarioServices.existePorId(id)) {  
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El inventario que quieres actualizar no existe.");  
    }  
    inventarioActualizado.setId(id);  

    inventarioServices.actualizarInventario(inventarioActualizado);  

    return ResponseEntity.ok("Inventario actualizado con éxito.");  
}
}
