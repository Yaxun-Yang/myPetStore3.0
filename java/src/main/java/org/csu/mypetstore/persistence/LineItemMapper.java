package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.LineItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineItemMapper {
    List<LineItem> getLineItemsByOrderId(String orderId);

    void insertLineItem(LineItem lineItem);

    int getTotalCount(String orderId);

    float getSubTotal(String orderId);

    void deleteOrder(String orderId);
}
