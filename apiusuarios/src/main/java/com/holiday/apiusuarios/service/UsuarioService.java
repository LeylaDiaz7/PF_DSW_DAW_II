package com.holiday.apiusuarios.service;

import com.holiday.apiusuarios.dto.UsuarioDTO;
import com.holiday.apiusuarios.mapper.UsuarioMapper;
import com.holiday.apiusuarios.model.Usuario;
import com.holiday.apiusuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder; // ✅ nuevo

    public UsuarioDTO registrar(UsuarioDTO dto) {
        Usuario usuario = mapper.toEntity(dto);
        usuario.setContraseña(passwordEncoder.encode(dto.getContraseña())); // ✅ encriptar contraseña
        return mapper.toDTO(usuarioRepository.save(usuario));
    }

    public List<UsuarioDTO> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> obtenerPorId(Long id) {
        return usuarioRepository.findById(id).map(mapper::toDTO);
    }

    public Optional<UsuarioDTO> actualizar(Long id, UsuarioDTO dto) {
        return usuarioRepository.findById(id).map(u -> {
            u.setNombre(dto.getNombre());
            u.setApellido(dto.getApellido());
            u.setEmail(dto.getEmail());
            u.setContraseña(passwordEncoder.encode(dto.getContraseña())); // ✅ encriptar también en update
            u.setRol(dto.getRol());
            return mapper.toDTO(usuarioRepository.save(u));
        });
    }

    public Optional<UsuarioDTO> login(String email, String contraseña) {
        return usuarioRepository.findByEmail(email)
                .filter(usuario -> passwordEncoder.matches(contraseña, usuario.getContraseña()))
                .map(mapper::toDTO);
    }



    public boolean eliminar(Long id) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuarioRepository.delete(usuario);
            return true;
        }).orElse(false);
    }
}
