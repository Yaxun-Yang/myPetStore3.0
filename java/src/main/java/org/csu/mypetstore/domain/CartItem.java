package org.csu.mypetstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Size(max = 10, min = 3, message = "username: Between 3-10 characters")
    private String username;

    @Size(max = 20, min = 3, message = "itemId: Between 3-20 characters")
    private String itemId;

    @Min(value = 0, message = "quantity: Bigger than zero")
    private int quantity;

}