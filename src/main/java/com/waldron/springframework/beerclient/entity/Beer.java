package com.waldron.springframework.beerclient.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Beer {

    private String id;
    private String beerName;
    private BeerStyle beerStyle;
    private String upc;
    private int quantityOnHand;
    private BigDecimal price;
    private int version;
    private Date createdDate;
    private Date lastModifiedDate;

}
