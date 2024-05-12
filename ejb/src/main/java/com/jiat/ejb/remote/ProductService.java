package com.jiat.ejb.remote;

import com.jiat.ejb.entity.Product;

import java.util.List;

public interface ProductService {
    public boolean registerProduct(String title, Float weight, String units, String merchantName);

    public List<Product> getProductsByMerchantName(String merchantName);

    Product getProductsByProductName(String merchantName);
}
