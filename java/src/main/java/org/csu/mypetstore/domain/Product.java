package org.csu.mypetstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Size(max = 20, min = 3, message = "productId: Between 3-20 characters")
    private String productId;

    @Size(max = 20, min = 3, message = "categoryId: Between 3-20 characters")
    private String categoryId;

    @Size(max = 20, min = 3, message = "productName: Between 3-20 characters")
    private String name;


    private String text;

}
