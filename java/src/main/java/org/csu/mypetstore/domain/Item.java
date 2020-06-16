package org.csu.mypetstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String itemId;
    private String productId;
    private String url;
    private float price;
    private String attribute;
    private int quantity;


}
