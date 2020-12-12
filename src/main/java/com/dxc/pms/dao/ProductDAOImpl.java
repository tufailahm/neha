package com.dxc.pms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.dxc.pms.model.Product;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public boolean addProduct(Product product) {

		System.out.println("Inside Product DAO : " + product);
		mongoTemplate.save(product);
		return false;
	}

	@Override
	public Product getProduct(int productId) {
		return mongoTemplate.findById(productId, Product.class, "product");
	}

	@Override
	public boolean isProductExists(int productId) {
		Product product = mongoTemplate.findById(productId, Product.class, "product");
		if (product == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean deleteProduct(int productId) {
		Product product = new Product();
		product.setProductId(productId);
		DeleteResult writeResult = mongoTemplate.remove(product);
		System.out.println(writeResult);
		long rowsAffected = writeResult.getDeletedCount();
		if (rowsAffected == 0)
			return false;
		else
			return true;
	}

	@Override
	public boolean updateProduct(Product product) {
		// mongoTemplate.save(product);

		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(product.getProductId()));

		Update update= new Update();
		update.set("productName",product.getProductName());
		update.set("quantityOnHand",product.getQuantityOnHand());
		update.set("price",product.getPrice());
		
		UpdateResult writeResult = mongoTemplate.updateFirst(query, update, Product.class);
		System.out.println(writeResult);
		long rowsAffected = writeResult.getMatchedCount();
		if (rowsAffected == 0)
			return false;
		else
			return true;
	}

	@Override
	public List<Product> getProducts() {

		return mongoTemplate.findAll(Product.class);
	}

	@Override
	public List<Product> searchProductByName(String productName) {

		Query query = new Query();
		query.addCriteria(Criteria.where("productName").is(productName));

		List<Product> allProducts = mongoTemplate.find(query, Product.class);
		System.out.println(allProducts);
		return allProducts;
	}

}
