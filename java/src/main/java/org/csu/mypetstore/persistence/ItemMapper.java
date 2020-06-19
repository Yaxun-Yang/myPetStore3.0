package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMapper {
    void insertItem(Item item);

    void updateItem(Item item);

    void updateQuantity(String itemId, int quantity);

    List<Item> getItemList();

    List<Item> getItemListByProductId(String productId);

    Item getItemByItemId(String itemId);

    int  getQuantity(String itemId);

    void deleteItemList();

    void deleteItem(String itemId);
}
