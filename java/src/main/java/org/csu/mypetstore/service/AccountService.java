package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.User;
import org.csu.mypetstore.domain.Admin;
import org.csu.mypetstore.persistence.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public User getUser(String username){
        return accountMapper.getUserByUsername(username);
    }

    public User getUser(String username, String password){
        return accountMapper.getUserByUsernameAndPassword(username, password);
    }

    public Admin getAdmin(String username, String password)
    {
        return accountMapper.getAdminByUsernameAndPassword(username, password);
    }
    public Admin getAdmin (String username)
    {
        return accountMapper.getAdminByUsername(username);
    }

    public void updateAdmin(Admin admin)
    {
         accountMapper.updateAdmin(admin);
    }

    public void insertAdmin(Admin admin)
    {
        accountMapper.insertAdmin(admin);
    }

    public void insertUser(User user){
        accountMapper.insertUser(user);
    }

    public void updateUser(User user){
        accountMapper.updateUser(user);
    }
}
