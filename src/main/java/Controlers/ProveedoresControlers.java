package Controlers;


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


import Models.ProveedoresModel;
import Services.ProveedoresServices;

@RestController
@RequestMapping("/proveedores")
public class ProveedoresControlers {

    @Autowired
    private ProveedoresServices proveedoresServices;


    @GetMapping("/all")
public List<ProveedoresModel> getAllNombres() {
    return this.proveedoresServices.getAllProvedores();
}


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



@PostMapping("/crear")
public ResponseEntity<?> agregarProveedor(@RequestBody ProveedoresModel proveedores) {
    this.proveedoresServices.crearProveedor(proveedores);
    return ResponseEntity.ok(proveedores);
}


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
