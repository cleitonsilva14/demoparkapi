package io.dev.demoparkapi.web.dto.mapper;


import io.dev.demoparkapi.entity.Usuario;
import io.dev.demoparkapi.web.dto.UsuarioCreateDto;
import io.dev.demoparkapi.web.dto.UsuarioResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioCreateDto createDto){
        return new ModelMapper().map(createDto, Usuario.class);
    }

    public static UsuarioResponseDto toDto(Usuario usuario){
        String role = usuario.getRole().name().substring("ROLE_".length());
        ModelMapper mapper = new ModelMapper();
        TypeMap<Usuario, UsuarioResponseDto> props = mapper.createTypeMap(Usuario.class, UsuarioResponseDto.class);

        props.addMappings(
                mapping -> mapping.map(source -> role, UsuarioResponseDto::setRole)
        );

        return mapper.map(usuario, UsuarioResponseDto.class);
    }

    public static List<UsuarioResponseDto> toListDto(List<Usuario> usuarios){
        return usuarios.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }



}
