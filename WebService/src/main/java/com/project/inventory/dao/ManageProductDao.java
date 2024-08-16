package com.project.inventory.dao;

import com.project.inventory.entity.ProductDetails;

import java.util.List;

public interface ManageProductDao {
    int saveProductDetails(ProductDetails productDetails);
    List<String> getAllProductCategory();
    List<ProductDetails> getAllProductDetails();
}
