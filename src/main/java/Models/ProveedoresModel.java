package Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "proveedores")
public class ProveedoresModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    
    @Column(name = "grupoDeMedicamento")
    private String grupoDeMedicamento;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InventarioModel> productos;

    
    public  ProveedoresModel() {}



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getNombre() {
        return nombre;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getGrupoDeMedicamento() {
        return grupoDeMedicamento;
    }



    public void setGrupoDeMedicamento(String grupoDeMedicamento) {
        this.grupoDeMedicamento = grupoDeMedicamento;
    }
}
