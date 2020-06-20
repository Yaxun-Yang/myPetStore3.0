package org.csu.mypetstore.domain;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Size(min = 4, max = 15, message = "orderId: Between 4-15 character")
    private String username;

    @Size(max = 15, min = 6, message = "password: Between 6-15 characters")
    private String password;

    @Email(message = "email: As e-mail Pattern")
    private String email;

    @NotBlank(message = "address: Not blank")
    private String address;

    @NotBlank(message = "phone: Not blank")
    private String phone;

    @NotBlank(message = "url: Not blank")
    private String url;
}
