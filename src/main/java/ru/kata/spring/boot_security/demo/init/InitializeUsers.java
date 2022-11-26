package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Component
public class InitializeUsers {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public InitializeUsers(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    Role adminRole = new Role("ROLE_ADMIN");
    Role userRole = new Role("ROLE_USER");

    private final Set<Role> roles1 = new HashSet<>(Set.of(adminRole, userRole));
    private final Set<Role> roles2 = new HashSet<>(Set.of(userRole));


    private final User admin = new User("admin", "admin", roles1);
    private final User user = new User("user", "user", roles2);


    @Transactional
    public void init() {
        roleService.save(adminRole);
        roleService.save(userRole);
        userService.addUser(admin);
        userService.addUser(user);
    }
}
