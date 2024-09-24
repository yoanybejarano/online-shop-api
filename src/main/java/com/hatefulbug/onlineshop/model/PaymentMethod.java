package com.hatefulbug.onlineshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "paymentmethods")
public class PaymentMethod {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "method_name", nullable = false, length = 50)
    private String methodName;

    @JsonIgnore
    @OneToMany(mappedBy = "paymentMethod", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

}