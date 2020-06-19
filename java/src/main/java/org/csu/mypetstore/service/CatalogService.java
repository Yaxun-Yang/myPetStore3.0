package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.CategoryMapper;
import org.csu.mypetstore.persistence.ItemMapper;
import org.csu.mypetstore.persistence.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.PortUnreachableException;
import java.util.List;

@Service
public class CatalogService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ItemMapper itemMapper;

    public void insertCategory(Category category)
    {
        categoryMapper.insertCategory(category);
    }

    public void insertProduct(Product product)
    {
        productMapper.insertProduct(product);
    }

    public void insertItem(Item item)
    {
        itemMapper.insertItem(item);
    }

    public void updateCategory(Category category)
    {
        categoryMapper.updateCategory(category);
    }

    public void updateProduct(Product product)
    {
        productMapper.updateProduct(product);
    }

    public void updateItem(Item item)
    {
        itemMapper.updateItem(item);
    }

    public void updateItemQuantity(String itemId, int quantity)
    {
        itemMapper.updateQuantity(itemId, quantity);
    }

    public List<Category> getCategoryList()
     {
        return categoryMapper.getCategoryList();
    }

    public Category getCategoryByCategoryId(String categoryId)
    {
        return categoryMapper.getCategoryByCategoryId(categoryId);
    }

    public List<Product> getProductList()
    {
        return productMapper.getProductList();
    }

    public List<Product> getProductListByCategoryId(String categoryId)
    {
        return productMapper.getProductListByCategoryId(categoryId);
    }

    public List<Product> searchProductList(String keyword) {
        return productMapper.searchProductList("%" + keyword.toLowerCase() + "%");
    }

    public Product getProductByProductId(String productId) {
        return productMapper.getProductByProductId(productId);
    }

    public List<Item> getItemList()
    {
        return itemMapper.getItemList();
    }

    public List<Item> getItemListByProductId(String productId){
        return itemMapper.getItemListByProductId(productId);
    }

    public Item getItemByItemId(String itemId){
        return itemMapper.getItemByItemId(itemId);
    }

    public int getItemQuantity (String itemId){
        return itemMapper.getQuantity(itemId) ;
    }

    public void deleteCategoryList()
    {
        categoryMapper.deleteCategoryList();
    }

    public void deleteCategory(String categoryId)
    {
        categoryMapper.deleteCategory(categoryId);
    }

    public void deleteProductList()
    {
        productMapper.deleteProductList();
    }

    public void deleteProduct(String productId)
    {
        productMapper.deleteProduct(productId);
    }

    public void deleteItemList()
    {
        itemMapper.deleteItemList();
    }

    public void deleteItem(String itemId)
    {
        itemMapper.deleteItem(itemId);
    }
}
