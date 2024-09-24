package com.hatefulbug.onlineshop.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private RoleDto role;
    private String address;
    private String phone;
}
