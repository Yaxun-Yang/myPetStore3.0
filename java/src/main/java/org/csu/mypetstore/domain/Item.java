package org.csu.mypetstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Size(max = 20, min = 3, message = "itemId: Between 3-20 characters")
    private String itemId;

    @Size(max = 20, min = 3, message = "productId: Between 3-20 characters")
    private String productId;

    @NotBlank(message = "url: Not blank")
    private String url;

    @Digits(fraction = 2,integer = 9, message = "price: Keep two decimal")
    @Min(value = 0, message = "price: Bigger than zero")
    private float price;

    private String attribute;

    @Min(value = 0, message = "quantity: Bigger than zero")
    private int quantity;


}
