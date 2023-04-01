package com.besysoft.integrador.mapper;

import com.besysoft.integrador.domain.Vehiculo;
import com.besysoft.integrador.dto.dto.VehiculoDTO;
import com.besysoft.integrador.dto.re.VehiculoRE;


import java.util.List;


public interface IVehiculoMapper {

    Vehiculo mapToEntity(VehiculoRE vehiculoRE);

    VehiculoDTO mapToDTO(Vehiculo vehiculo);

    VehiculoDTO mapToBasicDTO(Vehiculo vehiculo);

    List<VehiculoDTO> listToDTO(List<Vehiculo>list);

    List<VehiculoDTO> listToBasicDTO(List<Vehiculo>list);
}
