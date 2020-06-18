package org.csu.mypetstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {


    private String orderId;
    private String username;
    private Date orderDate;
    private float subTotal;
    private int totalCount;
    private String paid;
    private String sendTo;
    private String checkout;

}
