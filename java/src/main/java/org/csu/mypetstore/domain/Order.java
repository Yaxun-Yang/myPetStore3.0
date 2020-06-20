package org.csu.mypetstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {


    @Size(min = 4, max = 15, message = "orderId: Between 4-15 character")
    private String orderId;

    @Size(min = 4, max = 15, message = "orderId: Between 4-15 character")
    private String username;

    private Date orderDate;

    @Min(value = 0, message = "subTotal: Bigger than zero")
    private float subTotal;

    @Min(value = 0, message = "totalCount: Bigger than zero")
    private int totalCount;

    @Pattern(regexp = "Y|N", message = "paid: Y or N")
    private String paid;

    @NotBlank( message = "sendTo: Y or N")
    private String sendTo;

    @Pattern(regexp = "Y|N", message = "checkout: Y or N")
    private String checkout;

    @Pattern(regexp = "Y|N", message = "dispatched: Y or N")
    private String dispatched;

}
