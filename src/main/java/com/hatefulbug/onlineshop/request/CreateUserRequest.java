package com.hatefulbug.onlineshop.request;

import com.hatefulbug.onlineshop.model.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;
    private int roleId;
}
