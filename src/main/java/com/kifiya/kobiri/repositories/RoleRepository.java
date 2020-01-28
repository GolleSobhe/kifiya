package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role findByRole(String role);
}
