package com.fisiosaludweb.config;

import com.fisiosaludweb.entity.Usuario;
import com.fisiosaludweb.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        List<GrantedAuthority> authorities = u.getRoles().stream()
            .map(r -> new SimpleGrantedAuthority(r.getNombre()))
            .collect(Collectors.toList());
        return new User(u.getUsername(), u.getPassword(), u.isActivo(),
            true, true, true, authorities);
    }
}
