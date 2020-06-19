package org.csu.mypetstore.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import org.csu.mypetstore.annotation.UserLoginToken;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.AliPayService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.OrderService;
import org.csu.mypetstore.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderApi {

    @Autowired
    OrderService orderService;

    @Autowired
    CatalogService catalogService;

    @Autowired
    AccountService accountService;

    @PostMapping("/order/{username}")
    @UserLoginToken
    @Transactional
    public  ResponseTemplate creatOrder(@RequestBody JSONArray req, @PathVariable String username)
    {
        Order order = new Order();
        order.setUsername(username);
        orderService.insertOrder(order);
        String orderId = orderService.getOrderId(username);
        order.setOrderId(orderId);
        order.setOrderDate(new Date());
        order.setCheckout("N");
        order.setPaid("N");
        order.setDispatched("N");
        order.setSendTo(accountService.getUser(username).getAddress());
        for(int i=0; i<req.size();i++)
        {
            JSONObject  itemShow = req.getJSONObject(i);
            System.out.println(JSONObject.toJSONString(itemShow));
            LineItem lineItem = new LineItem();
            lineItem.setOrderId(orderId);
            lineItem.setItemId(itemShow.getString("itemId"));
            lineItem.setQuantity(itemShow.getInteger("quantity"));
            lineItem.setTotalPrice(itemShow.getFloat("totalCost"));
            orderService.insertLineItem(lineItem);
        }
        order.setTotalCount(orderService.getTotalCount(orderId));
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

    @GetMapping("/order/{orderId}")
    @UserLoginToken
    public ResponseTemplate getOrder(@PathVariable String orderId)
    {
        JSONObject data = new JSONObject();

        data.put("order", orderService.getOrder(orderId));

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @GetMapping("/itemShowList/{orderId}")
    @UserLoginToken
    public ResponseTemplate getItemShowList(@PathVariable String orderId)
    {
        JSONArray data = new JSONArray();

        List<LineItem> lineItemList = orderService.getLineItemList(orderId);
        for(int i =0; i<lineItemList.size();i++)
        {
            LineItem lineItem = lineItemList.get(i);
            JSONObject itemShow = new JSONObject();
            Item item = catalogService.getItemByItemId(lineItem.getItemId());
            itemShow.put("url", item.getUrl());
            itemShow.put("itemId", item.getItemId());
            itemShow.put("productId", item.getProductId());
            itemShow.put("text", item.getAttribute());
            itemShow.put("inStock" , item.getQuantity()>0?"Y":"N");
            itemShow.put("quantity", lineItem.getQuantity());
            itemShow.put("price", item.getPrice());
            itemShow.put("totalCost", lineItem.getTotalPrice());
            data.add(itemShow);
        }

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }



    @GetMapping("/orderList/{username}")
    @UserLoginToken
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

    @GetMapping("/recentOrder/{username}")
    @UserLoginToken
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
    @UserLoginToken
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

    @GetMapping("/paid/{orderId}")
    @UserLoginToken
    @Transactional
    public ResponseTemplate paid(@PathVariable String orderId)
    {
        JSONObject data = new JSONObject();
        try{
            data.put("response", AliPayService.toPay(orderService.getOrder(orderId)));
        }catch (AlipayApiException e)
        {
            e.printStackTrace();
        }

        orderService.paid(orderId);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @PutMapping("/address/{orderId}")
    @UserLoginToken
    public ResponseTemplate updateAddress(@RequestBody  JSONObject req,@PathVariable String orderId)
    {
        Order order = orderService.getOrder(orderId);
        String address = req.getString("address");
        order.setSendTo(address);
        orderService.updateOder(order);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PutMapping("/checkout/{orderId}")
    @UserLoginToken
    public ResponseTemplate checkout(@PathVariable String orderId)
    {
        orderService.checkout(orderId);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PutMapping("/dispatched/{orderId}")
    public ResponseTemplate dispatched(@PathVariable String orderId)
    {
        orderService.dispatched(orderId);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @DeleteMapping("/order/{orderId}")
    @UserLoginToken
    public ResponseTemplate deleteOrder(@PathVariable String orderId)
    {
        orderService.deleteOrder(orderId);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @DeleteMapping("/orderList/{username}")
    @UserLoginToken
    public ResponseTemplate deleteOrderList(@PathVariable String username)
    {
        orderService.deleteOrders(username);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }
}
