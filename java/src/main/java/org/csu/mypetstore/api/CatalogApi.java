package org.csu.mypetstore.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.csu.mypetstore.domain.Category;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
@CrossOrigin
public class CatalogApi {
    @Autowired
    private CatalogService catalogService;

    @GetMapping("/categoryList")
    public  ResponseTemplate getCategoryList()
    {
        JSONObject data = new JSONObject();
        data.put("categoryList", catalogService.getCategoryList());
        return ResponseTemplate.builder()
                .data(data)
                .status(200)
                .statusText("OK")
                .build();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseTemplate getCategory(@PathVariable String categoryId)
    {

        JSONObject data=new JSONObject();
        Category category = catalogService.getCategoryByCategoryId(categoryId);
        data.put("category", category);
        return ResponseTemplate.builder()
                            .data(data)
                            .status(200)
                            .statusText("OK")
                            .build();
    }

    @GetMapping("/productList")
    public ResponseTemplate getProductList()
    {
        JSONObject data = new JSONObject();
        data.put("productList", catalogService.getProductList()) ;
        return ResponseTemplate.builder()
                .data(data)
                .status(200)
                .statusText("OK")
                .build();
    }

    @GetMapping("/productList/{categoryId}")
    public ResponseTemplate getProductListByCategoryId(@PathVariable String categoryId)
    {
        JSONObject data = new JSONObject();
        data.put("productList", catalogService.getProductListByCategoryId(categoryId)) ;
        return ResponseTemplate.builder()
                .data(data)
                .status(200)
                .statusText("OK")
                .build();
    }

    @GetMapping("/product/{productId}")
    public ResponseTemplate getProductListByProductId(@PathVariable String productId)
    {
        JSONObject data = new JSONObject();
        data.put("product", catalogService.getProductByProductId(productId)) ;
        return ResponseTemplate.builder()
                .data(data)
                .status(200)
                .statusText("OK")
                .build();
    }

    @GetMapping("/item/{itemId}")
    public ResponseTemplate getItemByItemId(@PathVariable String itemId)
    {
        JSONObject data = new JSONObject();
        data.put("item", catalogService.getItemByItemId(itemId)) ;
        return ResponseTemplate.builder()
                .data(data)
                .status(200)
                .statusText("OK")
                .build();
    }

    @GetMapping("/itemList/{productId}")
    public ResponseTemplate getItemListByProductId(@PathVariable String productId)
    {
        JSONObject data = new JSONObject();
        data.put("itemList", catalogService.getItemListByProductId(productId));
        return ResponseTemplate.builder()
                .data(data)
                .status(200)
                .statusText("OK")
                .build();
    }

   @GetMapping("/productList/{keyWord}")
    public ResponseTemplate searchProductList(@PathVariable String keyWord)
   {
       JSONObject data = new JSONObject();
       data.put("productList", catalogService.searchProductList(keyWord));
       return ResponseTemplate.builder()
               .data(data)
               .status(200)
               .statusText("OK")
               .build();
   }

   @GetMapping("/quantity/{itemId}")
    public ResponseTemplate getItemQuantity(@PathVariable String itemId)
   {
       JSONObject data = new JSONObject();
       data.put("quantity", catalogService.getItemQuantity(itemId));
       return ResponseTemplate.builder()
               .data(data)
               .status(200)
               .statusText("OK")
               .build();
   }

   @PutMapping("/quantity/{itemId}")
    public ResponseTemplate updateItemQuantity(@PathVariable String itemId, int quantity)
   {
       catalogService.updateItemQuantity(itemId, quantity);

       JSONObject data = new JSONObject();
       return ResponseTemplate.builder()
               .data(data)
               .status(200)
               .statusText("OK")
               .build();
   }

   @GetMapping("/")
    public ResponseTemplate getView()
   {
       JSONArray data= new JSONArray();

      List<Category> categories = catalogService.getCategoryList();
      for(int i = 0; i < categories.size(); i++)
      {
          JSONObject category = new JSONObject();
          category.put("id",categories.get(i).getCategoryId());

          JSONArray productList = new JSONArray();
          List<Product> products = catalogService.getProductListByCategoryId(categories.get(i).getCategoryId());


          for(int j = 0; j < products.size(); j++)
          {
              JSONObject product = new JSONObject();
              product.put("id", products.get(j).getProductId());
              product.put("name", products.get(j).getName());
              product.put("text", products.get(j).getText());

              JSONArray itemList = new JSONArray();
              List<Item> items = catalogService.getItemListByProductId(products.get(j).getProductId());
              int u = 1;
            for(int k=0; k<items.size(); k++)
            {
                JSONObject item3 = new JSONObject();
                item3.put("index3",u++);
                JSONArray item = new JSONArray();
                for (int v =0 ;v<3 && k < items.size() ;v++, k++)
                {
                   item.add(items.get(k));

                }
                item3.put("item",  item);
                itemList.add(item3);
            }
            product.put("itemList", itemList);
            productList.add(product);
          }
            category.put("productList", productList);
            data.add(category);
      }


       return ResponseTemplate.builder()
               .data(data)
               .status(200)
               .statusText("OK")
               .build();
   }
}
