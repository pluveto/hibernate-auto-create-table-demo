package com.pluvet.auto_table_demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class PermissionGroup {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 权限分组标识符
     */
    @Column( nullable = false)
    private String permissionGroup;

    /**
     * 权限分组名
     */
    @Column(nullable = false)
    private String permissionGroupName;

    @OneToMany(cascade = CascadeType.MERGE)
    private Set<Permission> permissions;
}
