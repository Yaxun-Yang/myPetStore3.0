package org.csu.mypetstore;

import org.csu.mypetstore.domain.Admin;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.TokenService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@MapperScan("org.csu.mypetstore.persistence")
@SpringBootTest
class MypetstoreApplicationTests {

    @Autowired
    AccountService accountService;


    @Test
    void contextLoads() {

    }

    @Test
    void testAccount()
    {
        Admin admin =  accountService.getAdmin("admin","admin");
        System.out.println(admin.getPhone());
    }

}

