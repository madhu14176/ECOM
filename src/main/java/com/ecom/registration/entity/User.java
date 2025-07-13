package com.ecom.registration.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("USER")
@Data
public class User extends Account{
    private String mobile;
    private int age;

}
