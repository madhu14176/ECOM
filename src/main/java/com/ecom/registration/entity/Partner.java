package com.ecom.registration.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("PARTNER")
@Data
public class Partner extends Account{
    private String businessType;

    //@Lob
    //private byte[] image;
}
