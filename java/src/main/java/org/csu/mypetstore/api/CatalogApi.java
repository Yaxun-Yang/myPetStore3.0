package org.csu.mypetstore.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.qiniu.common.QiniuException;
import org.csu.mypetstore.domain.Category;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.PhotoService;
import org.csu.mypetstore.domain.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogApi {
    @Autowired
    private CatalogService catalogService;

    @PostMapping("/categoryList")
    public ResponseTemplate addCategoryList(@RequestBody JSONArray req)
    {
        for(int i=0; i<req.size(); i++)
        {
            JSONObject _req = req.getJSONObject(i);
            Category  category = new Category();
            category.setCategoryId(_req.getString("categoryId"));
            category.setName(_req.getString("name"));
            catalogService.insertCategory(category);
        }
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PostMapping("/category")
    public ResponseTemplate addCategory(@RequestBody JSONObject req)
    {

        Category  category = new Category();
        category.setCategoryId(req.getString("categoryId"));
        category.setName(req.getString("name"));
        catalogService.insertCategory(category);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PostMapping("productList")
    public ResponseTemplate addProductList(@RequestBody JSONArray req)
    {
        for(int i=0; i<req.size(); i++) {
            JSONObject _req = req.getJSONObject(i);
            Product product = new Product();
            product.setProductId(_req.getString("productId"));
            product.setCategoryId(_req.getString("categoryId"));
            product.setName(_req.getString("name"));
            product.setText(_req.getString("text"));

            catalogService.insertProduct(product);

        }
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PostMapping("/product")
    public ResponseTemplate addProduct(@RequestBody JSONObject req)
    {
        Product product = new Product();
        product.setProductId(req.getString("productId"));
        product.setCategoryId(req.getString("categoryId"));
        product.setName(req.getString("name"));
        product.setText(req.getString("text"));

        catalogService.insertProduct(product);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PostMapping("/itemList")
    public ResponseTemplate addItemList(@RequestBody JSONArray req)
    {
        for(int i=0; i<req.size(); i++) {
            JSONObject _req = req.getJSONObject(i);
            Item item = new Item();
            item.setItemId(_req.getString("itemId"));
            item.setProductId(_req.getString("productId"));
            item.setAttribute(_req.getString("attribute"));
            item.setPrice(_req.getFloat("price"));
            item.setQuantity(_req.getInteger("quantity"));
            item.setUrl(_req.getString("url"));

            catalogService.insertItem(item);

        }
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PostMapping("/item")
    public ResponseTemplate addItem(@RequestBody JSONObject req)
    {
        Item item = new Item();
        item.setItemId(req.getString("itemId"));
        item.setProductId(req.getString("productId"));
        item.setAttribute(req.getString("attribute"));
        item.setPrice(req.getFloat("price"));
        item.setQuantity(req.getInteger("quantity"));
        String file = req.getString("url");
        String fileType = file.substring(file.lastIndexOf(".") , file.length()).toLowerCase();
        String fileName =req.getString("itemId")+fileType;
        PhotoService.uploadImage(file, fileName);
        item.setUrl(PhotoService.domain+fileName);

        catalogService.insertItem(item);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PutMapping("/category")
    public ResponseTemplate updateCategory(@RequestBody JSONObject req)
    {

        Category  category = new Category();
        category.setCategoryId(req.getString("categoryId"));
        category.setName(req.getString("name"));
        catalogService.updateCategory(category);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PutMapping("/product")
    public ResponseTemplate updateProduct(@RequestBody JSONObject req)
    {
        Product product = new Product();
        product.setProductId(req.getString("productId"));
        product.setCategoryId(req.getString("categoryId"));
        product.setName(req.getString("name"));
        product.setText(req.getString("text"));

        catalogService.updateProduct(product);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PutMapping("/item")
    public ResponseTemplate updateItem(@RequestBody JSONObject req)throws QiniuException
    {
        Item item = new Item();
        item.setItemId(req.getString("itemId"));
        item.setProductId(req.getString("productId"));
        item.setAttribute(req.getString("attribute"));
        item.setPrice(req.getFloat("price"));
        item.setQuantity(req.getInteger("quantity"));
        if(req.get("file")!= null)
        {
            String file = req.getString("file");
            String fileName = req.getString("fileName");
            String fileType = fileName.substring(file.lastIndexOf(".")).toLowerCase();
            fileName =req.getString("username")+fileType;
            PhotoService.uploadImage(file, fileName);
            item.setUrl(PhotoService.domain+fileName);
            PhotoService.refresh(item.getUrl());
        }
        else
        {
            item.setUrl(req.getString("url"));
        }

        catalogService.updateItem(item);

        return ResponseTemplate.builder()
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

    @GetMapping("/products/{keyWord}")
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

    @GetMapping("/product/{productId}")
    public ResponseTemplate getProductByProductId(@PathVariable String productId)
    {
        JSONObject data = new JSONObject();
        data.put("product", catalogService.getProductByProductId(productId)) ;
        return ResponseTemplate.builder()
                .data(data)
                .status(200)
                .statusText("OK")
                .build();
    }

    @GetMapping("itemList")
    public ResponseTemplate getItemList()
    {
        JSONObject data = new JSONObject();
        data.put("productList", catalogService.getItemList()) ;
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

    @DeleteMapping("/categoryList")
    public ResponseTemplate deleteCategoryList()
   {
       catalogService.deleteCategoryList();

       return ResponseTemplate.builder()
               .status(200)
               .statusText("OK")
               .build();
   }

    @DeleteMapping("/category/{categoryId}")
    public ResponseTemplate deleteCategory(@PathVariable String categoryId)
    {
        catalogService.deleteCategory(categoryId);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @DeleteMapping("/productList")
    public ResponseTemplate deleteProductList()
    {
        catalogService.deleteProductList();

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @DeleteMapping("/product/{productId}")
    public ResponseTemplate deleteProduct(@PathVariable String productId)
    {
        catalogService.deleteProduct(productId);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @DeleteMapping("/itemList")
    public ResponseTemplate deleteItemList()
    {
        catalogService.deleteItemList();

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @DeleteMapping("/item/{itemId}")
    public ResponseTemplate deleteItem(@PathVariable String itemId)
    {
        catalogService.deleteItem(itemId);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }
}
