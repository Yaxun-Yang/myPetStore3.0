package org.csu.mypetstore.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineItem{

    @Size(min = 4, max = 15, message = "orderId: Between 4-15 character")
    private String  orderId;

    @Min(value = 0, message = "quantity: Bigger than zero")
    private int quantity;

    @Size(max = 20, min = 3, message = "itemId: Between 3-20 characters")
    private String itemId;

    @Min(value = 0, message = "totalQuantity: Bigger than zero")
    private float totalPrice;

}
