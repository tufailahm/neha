package com.dxc.pms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.pms.dao.ProductDAO;
import com.dxc.pms.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;
	
	@Override
	public boolean addProduct(Product product) {
		System.out.println("Inside Product Service : "+product);
		return productDAO.addProduct(product);
	}


	@Override
	public Product getProduct(int productId) {
		return productDAO.getProduct(productId);
	}

	@Override
	public boolean isProductExists(int productId) {
		return productDAO.isProductExists(productId);
	}

	@Override
	public boolean deleteProduct(int productId) {
		return productDAO.deleteProduct(productId);
	}

	@Override
	public boolean updateProduct(Product product) {
		return productDAO.updateProduct(product);
	}

	@Override
	public List<Product> getProducts() {
		return productDAO.getProducts();
	}

	@Override
	public List<Product> searchProductByName(String productName) {
		return productDAO.searchProductByName(productName);
	}

}
