package edu.mum.springbatchproject.service;


import edu.mum.springbatchproject.domain.Role;

public interface RoleService {
    void saveRole(Role role);
    Role getRole(String role);
}
