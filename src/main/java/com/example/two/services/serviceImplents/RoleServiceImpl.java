package com.example.two.services.serviceImplents;

import com.example.two.exceptions.ApiRequestException;
import com.example.two.models.ERole;
import com.example.two.models.Role;
import com.example.two.repository.RoleRepository;
import com.example.two.services.serviceInterfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public  class RoleServiceImpl implements RoleService {


    @Autowired
    @Lazy
    RoleRepository roleRepository;


    @Override
    public Optional<Role> findByName(ERole name) {
        try {
            return roleRepository.findByName(name);
        }catch (Exception e) {
         throw new ApiRequestException("Something went wrong initializing user roler " );
        }

    }
}