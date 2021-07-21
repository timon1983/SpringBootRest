package com.example.SpringRestProject.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.EVENTS_READ,Permission.FILE_READ)),
    MODERATOR(Set.of(Permission.USERS_WRITE,Permission.EVENTS_WRITE,Permission.FILE_WRITE)),
    ADMIN(Set.of(Permission.USERS_READ, Permission.EVENTS_READ,Permission.FILE_READ, Permission.USERS_WRITE,Permission.EVENTS_WRITE,Permission.FILE_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
