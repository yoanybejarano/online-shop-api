package com.hatefulbug.onlineshop.request;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;
}
