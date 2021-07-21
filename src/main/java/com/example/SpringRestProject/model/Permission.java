package com.example.SpringRestProject.model;

public enum Permission {

    USERS_READ("users:read"),
    USERS_WRITE("users:write"),
    EVENTS_READ("events:read"),
    EVENTS_WRITE("events:write"),
    FILE_READ("file:read"),
    FILE_WRITE("file:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
