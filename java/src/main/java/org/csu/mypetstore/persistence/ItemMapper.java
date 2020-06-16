package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMapper {


    void updateQuantity(String itemId, int quantity);

    int  getQuantity(String itemId);

    List<Item> getItemListByProductId(String productId);

    Item getItemByItemId(String itemId);

}
