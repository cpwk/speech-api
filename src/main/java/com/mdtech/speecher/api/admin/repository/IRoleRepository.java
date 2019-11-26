package com.mdtech.speecher.api.admin.repository;

import com.mdtech.speecher.api.admin.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Integer> {

}