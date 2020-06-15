package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.persistence.CartItemMapper;
import org.csu.mypetstore.persistence.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private ItemMapper itemMapper;



    //获取购物车中特定的商品
    public CartItem getCartItem(String cartId, String itemId)
    {
        return cartItemMapper.getCartItem(cartId, itemId);
    }

   //修改商品数量并更新购物车商品状态
    public void updateCartItemQuantity(String cartId, String itemId, int quantity)
    {
        CartItem cartItem=cartItemMapper.getCartItem(cartId, itemId);
        if(quantity > 0)
        {
            if( cartItem== null)
            {
                cartItemMapper.insertCartItem(cartId,itemId,quantity);
            }
            else
            {
                cartItemMapper.updateQuantity(cartId,itemId,quantity);
            }

        }
        else if(cartItem != null)
        {
            cartItemMapper.deleteCartItem(cartId, itemId);
        }
    }

    //向购物车中添加商品
    public void addItem(String cartId, String itemId)
    {
            CartItem cartItem = cartItemMapper.getCartItem(cartId, itemId);
            if(cartItem == null)
            {
                cartItemMapper.insertCartItem(cartId,itemId,1);
            }
            else
            {
                cartItemMapper.updateQuantity(cartId,itemId,cartItem.getQuantity()+1);
            }

    }

    //从购物车中删除商品
    public void removeItem(String cartId,String itemId)
    {
        cartItemMapper.deleteCartItem(cartId,itemId);
    }



    //修改商品库存数
    public void updateItemInventory(String itemId, int quantity)
    {
        itemMapper.updateQuantity(itemId,quantity);
    }

}
