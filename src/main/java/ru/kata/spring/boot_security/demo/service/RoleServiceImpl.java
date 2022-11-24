package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public boolean addRole(Role role) {
        Role userPrimary = roleRepository.findByName(role.getName());
        if(userPrimary != null) {return false;}
        roleRepository.save(role);
        return true;
    }
    @Override
    public List<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {

        System.out.println(roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


}