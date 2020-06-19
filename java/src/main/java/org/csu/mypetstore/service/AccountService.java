package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.domain.User;
import org.csu.mypetstore.domain.Admin;
import org.csu.mypetstore.persistence.AccountMapper;
import org.csu.mypetstore.persistence.CartItemMapper;
import org.csu.mypetstore.persistence.LineItemMapper;
import org.csu.mypetstore.persistence.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private LineItemMapper lineItemMapper;

    @Autowired
    private CartItemMapper cartItemMapper;

    public void insertAdmin(Admin admin)
    {
        accountMapper.insertAdmin(admin);
    }

    public void insertUser(User user){
        accountMapper.insertUser(user);
    }

    public void updateAdmin(Admin admin)
    {
        accountMapper.updateAdmin(admin);
    }


    public void updateUser(User user){
        accountMapper.updateUser(user);
    }

    public User getUser(String username){
        return accountMapper.getUserByUsername(username);
    }

    public User getUser(String username, String password){
        return accountMapper.getUserByUsernameAndPassword(username, password);
    }

    public List<User> getUserList()
    {
        return accountMapper.getUserList();
    }

    public String getUserPassword(String username)
    {
        return accountMapper.getUserPassword(username);
    }

    public Admin getAdmin(String username, String password)
    {
        return accountMapper.getAdminByUsernameAndPassword(username, password);
    }
    public Admin getAdmin (String username)
    {
        return accountMapper.getAdminByUsername(username);
    }

    public List<Admin> getAdminList()
    {
        return accountMapper.getAdminList();
    }

    public String getAdminPassword(String username)
    {
        return accountMapper.getAdminPassword(username);
    }

    @Transactional
    public void deleteUser(String username)
    {
        cartItemMapper.deleteCartItemByCart(username);
        List<Order> orderList = orderMapper.getOrdersByUsername(username);
        for(int i=0; i<orderList.size(); i++)
        {
            lineItemMapper.deleteOrder(orderList.get(i).getOrderId());
        }
        orderMapper.deleteOrders(username);
        accountMapper.deleteUser(username);
    }

    public void deleteUserList()
    {
        accountMapper.deleteUserList();
    }

    public void deleteAdmin(String username)
    {
        accountMapper.deleteUser(username);
    }

    public void deleteAdminList()
    {
        accountMapper.deleteAdminList();
    }
}
