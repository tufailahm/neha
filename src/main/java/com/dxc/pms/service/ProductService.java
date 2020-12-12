package com.dxc.pms.service;

import java.util.List;

import com.dxc.pms.model.Product;

public interface ProductService {
	public boolean addProduct(Product product);
	public Product getProduct(int productId);
	public boolean isProductExists(int productId);
	public boolean deleteProduct(int productId);
	public boolean updateProduct(Product product);
	public List<Product> getProducts();
	public List<Product> searchProductByName(String productName);
}
