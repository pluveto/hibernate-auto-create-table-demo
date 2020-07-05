package com.pluvet.auto_table_demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 权限标识符
     */
    @Column(nullable = false)
    private String permission;

    /**
     * 权限名
     */
    @Column(nullable = false)
    private String permissionName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private PermissionGroup permissionGroup;
}
