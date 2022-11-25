package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Component
public class InitializeUsers{
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public InitializeUsers(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    Role adminRole = new Role("ROLE_ADMIN");
    Role userRole = new Role("ROLE_USER");
    Role guestRole = new Role("ROLE_GUEST");

    private final List<Role> roles1 = new LinkedList<>(List.of(adminRole, userRole));
    private final List<Role> roles2 = new LinkedList<>(List.of(userRole, guestRole));
    private final List<Role> roles3 = new LinkedList<>(List.of(guestRole));

    private final User admin = new User( "admin", "admin", roles1);
    private final User user = new User( "user", "user", roles2);
    private final User guest = new User("guest", "guest", roles3);

    @Transactional
    public void init() {
        roleService.save(adminRole);
        roleService.save(userRole);
        roleService.save(guestRole);
        userService.addUser(admin);
        userService.addUser(user);
        userService.addUser(guest);
    }
}
