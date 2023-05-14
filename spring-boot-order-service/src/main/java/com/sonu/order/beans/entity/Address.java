package com.sonu.order.beans.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String addressId;
    private String addressType;
    private String customerId;
    private String flatNumber;
    private String plotNumber;
    private String buildingName;
    private Integer roadNumber;
    private String street;
    private String city;
    private String country;
    private Long pinCode;
}
