package org.csu.mypetstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Size(max = 20, min = 3, message = "categoryId: Between 3-20 characters")
    private String categoryId;

    @Size(max =20, min = 3, message = "categoryName: Between 3-20 characters")
    private String name;
}
