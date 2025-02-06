package io.dev.demoparkapi.web.controller;


import io.dev.demoparkapi.entity.Usuario;
import io.dev.demoparkapi.service.UsuarioService;
import io.dev.demoparkapi.web.dto.UsuarioCreateDto;
import io.dev.demoparkapi.web.dto.UsuarioResponseDto;
import io.dev.demoparkapi.web.dto.UsuarioSenhaDto;
import io.dev.demoparkapi.web.dto.mapper.UsuarioMapper;
import io.dev.demoparkapi.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuarios", description = "Contém todas as operações relacionadas aos usuários")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(
            summary = "Criar um novo usuário",
            description = "Endpoint responsável para a criação de um novo usuário",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Criado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UsuarioResponseDto.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Usuário já está cadastrado",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ErrorMessage.class
                                    )
                            )

                    ),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Dados não processados, devido aos dados inválidos",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ErrorMessage.class
                                    )
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioCreateDto createDto){
        Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id){
        Usuario user = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(user));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UsuarioSenhaDto dto){
        Usuario user = usuarioService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> getAll(){
        List<Usuario> users = usuarioService.buscarTodos();
        return ResponseEntity.ok(UsuarioMapper.toListDto(users));
    }





}
