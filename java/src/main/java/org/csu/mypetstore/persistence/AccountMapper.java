package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.User;
import org.csu.mypetstore.domain.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapper {

    void insertUser(User user);

    void insertAdmin(Admin admin);

    void updateUser(User user);

    void updateAdmin(Admin admin);

    User getUserByUsername(String username);

    User getUserByUsernameAndPassword(String username, String password);

    List<User> getUserList();

    String getUserPassword(String username);

    Admin getAdminByUsername(String username);

    Admin getAdminByUsernameAndPassword(String username, String password);

    List<Admin> getAdminList();

    String getAdminPassword(String username);

    void deleteUser(String username);

    void deleteUserList();

    void deleteAdmin(String admin);

    void deleteAdminList();


}
