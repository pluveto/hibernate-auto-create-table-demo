package com.pluvet.auto_table_demo;

import com.pluvet.auto_table_demo.entity.Permission;
import com.pluvet.auto_table_demo.entity.PermissionGroup;
import com.pluvet.auto_table_demo.entity.Role;
import com.pluvet.auto_table_demo.entity.User;
import com.pluvet.auto_table_demo.model.UserStatusEnum;
import com.pluvet.auto_table_demo.util.HibernateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class AutoTableDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void hibernateTest() {


        var session = HibernateUtil.getSession();
        var transaction = session.beginTransaction();

        var user = createUser();

        var roleAdmin = createAdminRole();

        var roleUser = createUserRole();

        var userRoles = new HashSet<Role>();
        userRoles.add(roleAdmin);
        userRoles.add(roleUser);

        user.setRoles(userRoles);
        user.getRoles().remove(roleUser);

        roleAdmin.setPermissions(createPermissions(1, 5));
        var userPermissions = createPermissions(6, 10);
        roleUser.setPermissions(userPermissions);

        var group = new PermissionGroup();
        group.setPermissionGroupName("Auth");
        group.setPermissionGroup("auth");
        group.setPermissions(userPermissions);

        session.save(user);
        session.save(roleAdmin);
        session.save(roleUser);
        session.save(group);
        printPermission(user);

        transaction.commit();
        HibernateUtil.closeSession(session);
    }

    private void printPermission(User user) {
        var roles = user.getRoles();
        System.out.println(user.getScreenName());
        for (var role : roles) {
            var perms = role.getPermissions();
            for (var perm : perms) {
                System.out.println(perm.getPermissionName());
            }
        }
    }

    private Role createUserRole() {
        var roleUser = new Role();
        roleUser.setRoleName("用户");
        roleUser.setRole("user");
        return roleUser;
    }

    private Role createAdminRole() {
        var roleAdmin = new Role();
        roleAdmin.setRoleName("管理员");
        roleAdmin.setRole("admin");
        return roleAdmin;
    }

    private User createUser() {
        var user = new User();
        user.setUsername("pluvet");
        user.setEmail("test@mail.com");
        user.setPassword("pass11111111");
        user.setScreenName("Pluvet");
        user.setStatus(UserStatusEnum.Normal);
        return user;
    }

    private Set<Permission> createPermissions(int from, int to) {
        var permissions = new HashSet<Permission>();
        for (int i = from; i < to; i++) {
            var permission = new Permission();
            permission.setPermission("permission." + i);
            permission.setPermissionName("Permission #" + i);
            permissions.add(permission);
        }
        return permissions;
    }

}
