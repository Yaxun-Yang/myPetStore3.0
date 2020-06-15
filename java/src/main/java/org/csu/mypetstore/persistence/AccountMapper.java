package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.User;
import org.csu.mypetstore.domain.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {
    User getUserByUsername(String username);

    User getUserByUsernameAndPassword(String username, String password);

    Admin getAdminByUsername(String username);

    Admin getAdminByUsernameAndPassword(String username, String password);

    void insertUser(User user);

    void insertAdmin(Admin admin);

    void updateUser(User user);

    void updateAdmin(Admin admin);
}
