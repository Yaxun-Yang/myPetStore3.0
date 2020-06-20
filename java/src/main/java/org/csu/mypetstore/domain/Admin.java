package org.csu.mypetstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Size(max = 10 , min = 3, message = "username: Between 3-10 characters")
    private String username;

    @Size(max = 15, min = 6, message = "password: Between 6-15 characters")
    private String password;

    @NotBlank(message = "phone: Not blank")
    private String phone;

    @NotBlank(message = "url: Not blank")
    private String url;
}
