package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
    void insertProduct(Product product);

    void updateProduct(Product product);

    List<Product> getProductList();

    List<Product> getProductListByCategoryId(String categoryId);

    List<Product> searchProductList(String keywords);

    Product getProductByProductId(String productId);

    void deleteProductList();

    void deleteProduct(String productId);

}
