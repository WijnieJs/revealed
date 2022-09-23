package com.example.two.services.serviceInterfaces;

import com.example.two.models.ERole;
import com.example.two.models.Role;

import java.util.Optional;

public interface RoleService {


    Optional<Role> findByName(ERole name);

}
