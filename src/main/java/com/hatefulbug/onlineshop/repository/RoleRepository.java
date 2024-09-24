package com.hatefulbug.onlineshop.repository;

import com.hatefulbug.onlineshop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
