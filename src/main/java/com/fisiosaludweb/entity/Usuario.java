package com.fisiosaludweb.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String nombreCompleto;
    private boolean activo = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_roles",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles;

    public Usuario() {}
    public Usuario(Long id, String username, String password, String nombreCompleto, boolean activo, Set<Rol> roles) {
        this.id = id; this.username = username; this.password = password;
        this.nombreCompleto = nombreCompleto; this.activo = activo; this.roles = roles;
    }
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; } public void setUsername(String v) { this.username = v; }
    public String getPassword() { return password; } public void setPassword(String v) { this.password = v; }
    public String getNombreCompleto() { return nombreCompleto; } public void setNombreCompleto(String v) { this.nombreCompleto = v; }
    public boolean isActivo() { return activo; } public void setActivo(boolean v) { this.activo = v; }
    public Set<Rol> getRoles() { return roles; } public void setRoles(Set<Rol> v) { this.roles = v; }
}
