package com.ecom.registration.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("USER")
@Data
public class User extends Account{
    private String mobile;
    private int age;

}
