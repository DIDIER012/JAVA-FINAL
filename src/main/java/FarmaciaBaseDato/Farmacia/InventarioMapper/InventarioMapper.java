package FarmaciaBaseDato.Farmacia.InventarioMapper;

import FarmaciaBaseDato.Farmacia.InventarioDto.InventarioDto;
import FarmaciaBaseDato.Farmacia.Models.InventarioModel;

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