package org.csu.mypetstore.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.csu.mypetstore.annotation.UserLoginToken;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartApi {

    @Autowired
    private CartService cartService;

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/itemShowList/{username}")
    @UserLoginToken
    public  ResponseTemplate getItemShowList(@PathVariable String  username)
    {
        JSONArray data = new JSONArray();
        List<CartItem> cartItemList = cartService.getCart(username);
        for(int i =0; i<cartItemList.size();i++)
        {
            CartItem cartItem = cartItemList.get(i);
            JSONObject itemShow = new JSONObject();
            Item item = catalogService.getItemByItemId(cartItem.getItemId());
            itemShow.put("url", item.getUrl());
            itemShow.put("itemId", item.getItemId());
            itemShow.put("productId", item.getProductId());
            itemShow.put("text", item.getAttribute());
            itemShow.put("inStock" , cartItem.getInStock());
            itemShow.put("quantity", cartItem.getQuantity());
            itemShow.put("price", item.getPrice());
            itemShow.put("totalCost", item.getPrice()*cartItem.getQuantity());
            data.add(itemShow);
        }

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @PostMapping("/cartItem/{username}/{itemId}")
    @UserLoginToken
    public ResponseTemplate addCartItem(@PathVariable String username, @PathVariable String itemId)
    {
        cartService.addItem(username, itemId);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PutMapping("/cartItem/{username}/{itemId}/{quantity}")
    public ResponseTemplate updateCartItem(@PathVariable String username, @PathVariable String itemId, @PathVariable int quantity)
    {
        cartService.updateCartItemQuantity(username, itemId, quantity);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @DeleteMapping ("/cartItem/{username}")
    public ResponseTemplate deleteCart(@PathVariable String username)
    {
        cartService.deleteCart(username);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @DeleteMapping("/cartItem/{username}/{itemId}")
    public ResponseTemplate deleteCartItem(@PathVariable String username, @PathVariable String itemId)
    {
        cartService.removeItem(username, itemId);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @GetMapping("/cartItem/{username}")
    public ResponseTemplate getCart(@PathVariable String username)
    {
        JSONObject data = new JSONObject();
        data.put("cartItemList",cartService.getCart(username));
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @GetMapping("/cartItem/{username}/{itemId}")
    public ResponseTemplate getCartItem(@PathVariable String username, @PathVariable String itemId)
    {
        JSONObject data = new JSONObject();
        data.put("cartItem",cartService.getCartItem(username, itemId));
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

}
