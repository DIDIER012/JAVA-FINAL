package FarmaciaBaseDato.Farmacia.Controlers;  

import java.util.List;  

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.http.HttpStatus;  
import org.springframework.http.ResponseEntity;  
import org.springframework.web.bind.annotation.DeleteMapping;  
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.PostMapping;  
import org.springframework.web.bind.annotation.PutMapping;  
import org.springframework.web.bind.annotation.RequestBody;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController;  

import FarmaciaBaseDato.Farmacia.InventarioDto.InventarioDto;  
import FarmaciaBaseDato.Farmacia.Models.InventarioModel;  
import FarmaciaBaseDato.Farmacia.Services.InventarioServices;  
import io.swagger.v3.oas.annotations.Operation;  
import io.swagger.v3.oas.annotations.responses.ApiResponse;  
import io.swagger.v3.oas.annotations.responses.ApiResponses;  

@RestController  
@RequestMapping("/inventario")  
public class InventarioControlers {  

    @Autowired  
    private InventarioServices inventarioServices;  

    @Operation(summary = "Agregar un nuevo inventario")  
    @ApiResponses(value = {  
        @ApiResponse(responseCode = "200", description = "Inventario creado exitosamente"),  
        @ApiResponse(responseCode = "400", description = "Error al crear el inventario")
    })  
    @PostMapping("/crear")  
    public ResponseEntity<?> agregarInventario(@RequestBody InventarioModel inventario) {  
        System.out.println("Método agregarInventario invocado con el objeto: " + inventario);  
        try {  
            inventarioServices.creandoInventario(inventario);  
            return ResponseEntity.ok(inventario);   
        } catch (Exception e) {  
            System.err.println("Error en agregarInventario: " + e.getMessage());  
            return ResponseEntity.badRequest().body("Error creando inventario: " + e.getMessage());  
        }  
    }  

    @Operation(summary = "Obtener todos los inventarios")  
    @ApiResponses(value = {  
        @ApiResponse(responseCode = "200", description = "Lista de inventarios obtenida exitosamente")  
    })  
    @GetMapping("/All")  
    public List<InventarioModel> getAllInventarioModel() {  
        return this.inventarioServices.getAllNombres();  
    }  
    
    @Operation(summary = "Buscar todos los inventarios")  
    @ApiResponses(value = {  
        @ApiResponse(responseCode = "200", description = "Lista de inventarios encontrada")  
    })  
    @GetMapping("/Busqueda")  
    public ResponseEntity<List<InventarioDto>> getAllInventarios() {  
        List<InventarioDto> inventarios = inventarioServices.getAllInventarios();  
        return ResponseEntity.ok(inventarios);  
    }  

    @Operation(summary = "Verificar si un producto existe")  
    @ApiResponses(value = {  
        @ApiResponse(responseCode = "200", description = "Producto existe"),  
        @ApiResponse(responseCode = "404", description = "El producto no se encontró")  
    })  
    @GetMapping("/{id}")  
    public ResponseEntity<Boolean> existeProducto(@PathVariable Long id) {  
        boolean existe = inventarioServices.existePorId(id);  
        
        if (existe) {  
            return new ResponseEntity<>(true, HttpStatus.OK);   
        } else {  
            System.err.println("¡El usuario que buscas no existe!");  
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);   
        }  
    }  

    @Operation(summary = "Eliminar un inventario")  
    @ApiResponses(value = {  
        @ApiResponse(responseCode = "200", description = "Inventario eliminado con éxito"),  
        @ApiResponse(responseCode = "404", description = "El inventario no existe")  
    })  
    @DeleteMapping("/borrar/{id}")  
    public ResponseEntity<String> borrarInventario(@PathVariable Long id) {  
        InventarioModel inventario = new InventarioModel();  
        inventario.setId(id);  
        
        boolean eliminado = inventarioServices.borrarInventario(inventario);  
        
        if (eliminado) {  
            return ResponseEntity.ok("Inventario eliminado con éxito.");  
        } else {  
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El inventario que quieres eliminar no existe.");  
        }  
    }  

    @Operation(summary = "Actualizar un inventario")  
    @ApiResponses(value = {  
        @ApiResponse(responseCode = "200", description = "Inventario actualizado con éxito"),  
        @ApiResponse(responseCode = "404", description = "El inventario que quieres actualizar no existe")  
    })  
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