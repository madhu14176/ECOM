package com.ecom.registration.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("PARTNER")
@Data
public class Partner extends Account{

    @Column
    private String businessType;

    //@Lob
    //private byte[] image;
}
