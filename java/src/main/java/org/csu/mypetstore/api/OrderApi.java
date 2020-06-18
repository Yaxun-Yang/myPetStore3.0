package org.csu.mypetstore.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;
import org.csu.mypetstore.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderApi {

    @Autowired
    OrderService orderService;

    @PostMapping("/itemShowList/{username}")
    public  ResponseTemplate creatOrder(@RequestBody JSONArray req, @PathVariable String username)
    {
        Order order = new Order();
        orderService.insertOrder(order);
        String orderId = orderService.getOrderId(username);
        order.setOrderId(orderId);
        order.setOrderDate(new Date());
        order.setUsername(username);
        order.setCheckout('N');
        order.setPaid('N');
        order.setTotalCount(req.size());
        for(int i=0; i<req.size();i++)
        {
            JSONObject  itemShow = req.getJSONObject(i);
            LineItem lineItem = new LineItem();
            lineItem.setOrderId(orderId);
            lineItem.setItemId(itemShow.getString("itemId"));
            lineItem.setQuantity(itemShow.getInteger("quantity"));
            lineItem.setTotalPrice(itemShow.getFloat("totalPrice"));
            orderService.insertLineItem(lineItem);
        }
        order.setSubTotal(orderService.getSubTotal(orderId));
        orderService.updateOder(order);


        JSONObject data = new JSONObject();

        data.put("orderId",orderId);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @GetMapping("/orderList/{username}")
    public ResponseTemplate getOrderList(@PathVariable String username)
    {
        JSONObject data = new JSONObject();

        data.put("orderList", orderService.getOrdersByUsername(username));

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @GetMapping("/order/{username}")
    public ResponseTemplate getRecentOrder(@PathVariable String username)
    {
        JSONObject data = new JSONObject();


        data.put("order", orderService.getRecentOrder(username));

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }


    @GetMapping("/orderId/{username}")
    public ResponseTemplate getOrderId(@PathVariable String username)
    {
        JSONObject data = new JSONObject();

        data.put("orderId", orderService.getOrderId(username));
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @PutMapping("/paid/{orderId}")
    public ResponseTemplate paid(@PathVariable String orderId)
    {

        orderService.paid(orderId);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }


}
