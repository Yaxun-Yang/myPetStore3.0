package org.csu.mypetstore.persistence;

import org.apache.ibatis.annotations.Param;
import org.csu.mypetstore.domain.CartItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemMapper {

    List<CartItem> getCartItemList(String username);

    CartItem getCartItem(String username, String itemId);

    void insertCartItem(String username,String itemId, int quantity);

    void deleteCartItem(String username, String itemId);

    void deleteCartItemByCart( String username);

    void updateQuantity(String username,  String itemId, int quantity);

}
