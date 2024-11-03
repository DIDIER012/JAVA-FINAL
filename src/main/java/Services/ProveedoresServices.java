package Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Models.ProveedoresModel;
import Repositories.ProveedoresRepositori;

@Service
public class ProveedoresServices {

    @Autowired
    private ProveedoresRepositori proveedoresRepositori;

    public List<ProveedoresModel> getAllProvedores() {
        return proveedoresRepositori.findAll();
    }

    public boolean existeId(Long id){
        return this.proveedoresRepositori.existsById(id);
    }

    public void crearProveedor(ProveedoresModel proveedor){
        proveedoresRepositori.save(proveedor);
    }

    public boolean eliminarProveedor(ProveedoresModel eliminarProveedor) {
        if(this.proveedoresRepositori.existsById(eliminarProveedor.getId())) {
            this.proveedoresRepositori.delete(eliminarProveedor);
            return true;
        } else {
            System.err.println("El proveedor que quieres eliminar no existe");
            return false;
        }
    }

    public void actualizarProveedor(ProveedoresModel actualizar) {
        if(this.proveedoresRepositori.existsById(actualizar.getId())){
            this.proveedoresRepositori.save(actualizar);
        } else {
            System.err.println("El usuario que quieres actualizar no existe!");
        }
    }
}
