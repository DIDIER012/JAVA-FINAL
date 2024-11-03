package InventarioMapper;

import InventarioDto.InventarioDto;
import Models.InventarioModel;

public class InventarioMapper {

    public static InventarioDto toDTO(InventarioModel inventario) {
                InventarioDto dto = new InventarioDto();
                dto.setId(inventario.getId());
                dto.setNombre(inventario.getNombre());
                return dto;
        }
        
        
            public static InventarioModel toModel(InventarioDto dto) {
                InventarioModel inventario = new InventarioModel();
                inventario.setId(dto.getId());
                inventario.setNombre(dto.getNombre());
                return inventario;
        }
}


// import Models.InventarioModel;

// public class InventarioMapper {

//     
//     public static InventarioDTO toDTO(InventarioModel inventario) {
//         InventarioDTO dto = new InventarioDTO();
//         dto.setId(inventario.getId());
//         dto.setNombre(inventario.getNombre());
//         dto.setCantidad(inventario.getCantidad());
//         return dto;
//     }

//     
//     public static InventarioModel toModel(InventarioDTO dto) {
//         InventarioModel inventario = new InventarioModel();
//         inventario.setId(dto.getId());
//         inventario.setNombre(dto.getNombre());
//         inventario.setCantidad(dto.getCantidad());
//         return inventario;
//     }
// }



// Usar el Mapper en el Servicio
// Luego, puedes utilizar este mapper en tu servicio para convertir entre InventarioModel y InventarioDTO. Aquí tienes un ejemplo de cómo hacerlo en el servicio:

// java
// Copiar código
// import java.util.List;
// import java.util.stream.Collectors;

// @Service
// public class InventarioServices {

//     @Autowired
//     private InventarioRepositori inventarioRepositori;

//     public List<InventarioDTO> getAllInventarios() {
//         return inventarioRepositori.findAll().stream()
//             .map(InventarioMapper::toDTO) // Utiliza el mapper aquí
//             .collect(Collectors.toList());
//     }

//     public void creandoInventario(InventarioDTO inventarioDTO) {
//         InventarioModel inventario = InventarioMapper.toModel(inventarioDTO); // Convierte DTO a modelo
//         this.inventarioRepositori.save(inventario);
//     }

//     // Otras funciones (borrar, actualizar, etc.) usando el mapper según sea necesario
// }



// Controlador
// Finalmente, tu controlador puede seguir utilizando InventarioDTO como antes:

// java
// Copiar código
// @RestController
// @RequestMapping("/inventario")
// public class InventarioController {

//     @Autowired
//     private InventarioServices inventarioServices;

//     @PostMapping
//     public ResponseEntity<Void> crearInventario(@RequestBody InventarioDTO inventarioDTO) {
//         inventarioServices.creandoInventario(inventarioDTO);
//         return ResponseEntity.status(HttpStatus.CREATED).build();
//     }

//     @GetMapping
//     public ResponseEntity<List<InventarioDTO>> obtenerInventarios() {
//         List<InventarioDTO> inventarios = inventarioServices.getAllInventarios();
//         return ResponseEntity.ok(inventarios);
//     }
// }