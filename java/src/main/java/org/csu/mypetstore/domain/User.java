package org.csu.mypetstore.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private String email;
    private String address;
    private String phone;
}
