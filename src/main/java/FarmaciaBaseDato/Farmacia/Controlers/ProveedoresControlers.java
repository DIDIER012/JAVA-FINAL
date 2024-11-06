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


import FarmaciaBaseDato.Farmacia.Models.ProveedoresModel;
import FarmaciaBaseDato.Farmacia.Services.ProveedoresServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/proveedores")
public class ProveedoresControlers {

    @Autowired
    private ProveedoresServices proveedoresServices;

    @Operation(summary = "Buscar todos los proveedores")  
    @ApiResponses(value = {  
        @ApiResponse(responseCode = "200", description = "lista de usuarios"),  
        @ApiResponse(responseCode = "400", description = "Error al bucar lista")
    }) 
    @GetMapping("/all")
public List<ProveedoresModel> getAllNombres() {
    return this.proveedoresServices.getAllProvedores();
}

@Operation(summary = "Buscar por id")  
@ApiResponses(value = {  
    @ApiResponse(responseCode = "200", description = "Proveedor encontrado"),  
    @ApiResponse(responseCode = "400", description = "Error al buscar proveedor")
}) 
@GetMapping("/{id}")
public ResponseEntity<Boolean> existeProducto(@PathVariable Long id) {
    boolean existeId = proveedoresServices.existeId(id);

    if(existeId) {
        return new ResponseEntity<>(true, HttpStatus.OK);
    } else {
        System.err.println("!El usuario que buscas no existe!");
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}


@Operation(summary = "Agregar un nuevo proveedor")  
@ApiResponses(value = {  
    @ApiResponse(responseCode = "200", description = "Proveedor creado exitosamente"),  
    @ApiResponse(responseCode = "400", description = "Error al crear el Proveedor")
}) 
@PostMapping("/crear")
public ResponseEntity<?> agregarProveedor(@RequestBody ProveedoresModel proveedores) {
    this.proveedoresServices.crearProveedor(proveedores);
    return ResponseEntity.ok(proveedores);
}




@Operation(summary = "Borrar proveedor")  
@ApiResponses(value = {  
    @ApiResponse(responseCode = "200", description = "Proveedor borrado exitosamente"),  
    @ApiResponse(responseCode = "400", description = "Error al borrar !Algo salio mal!")
}) 
@DeleteMapping("/borrar{id}")
public ResponseEntity<String> borrarProveedor(@PathVariable long id){
    ProveedoresModel proveedores = new ProveedoresModel();
    proveedores.setId(id);
    
    boolean eliminar = proveedoresServices.eliminarProveedor(proveedores);

    if(eliminar){ 
        return ResponseEntity.ok("Usuario eliminado correctamente");
    } else {
        return ResponseEntity.ok("El usuario que quieres eliminar no existe");
    }
}

@Operation(summary = "Actualizar datos del proveedor")  
@ApiResponses(value = {  
    @ApiResponse(responseCode = "200", description = "Proveedor actualizado exitosamente"),  
    @ApiResponse(responseCode = "400", description = "Error al actualizar !Algo salio mal!")
}) 
@PutMapping ("/actualizar/{id}")
public ResponseEntity<String> actualizarProveedores(@PathVariable Long id, @RequestBody ProveedoresModel actualizar) {
    if(!proveedoresServices.existeId(id)){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario que quieres actualizar no existe!");
    }
    actualizar.setId(id);

    proveedoresServices.actualizarProveedor(actualizar);;

    return ResponseEntity.ok("Usuario actualizado con exito!");
}
}
