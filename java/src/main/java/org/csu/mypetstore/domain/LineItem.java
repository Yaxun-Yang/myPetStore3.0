package org.csu.mypetstore.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineItem{

    private int orderId;
    private int quantity;
    private String itemId;
    private float price;
    private Item item;

}