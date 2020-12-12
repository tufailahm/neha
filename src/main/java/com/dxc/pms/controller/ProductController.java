package com.dxc.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.pms.model.Product;
import com.dxc.pms.service.ProductService;

@RestController
@RequestMapping("product")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
public class ProductController {

	@Autowired
	ProductService productService;

	// To Fetch all the products
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		System.out.println("FETCHING ALL PRODUCTs called ");
		List<Product> products =  productService.getProducts();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	// Getting a single product
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") int productId) {
		System.out.println("GET PRODUCT 1 called" + productId);
		Product product =  productService.getProduct(productId);
		return new ResponseEntity<Product>(product, HttpStatus.OK);

	}

	// Delete a single product
	@DeleteMapping("/{productId}")
	public boolean deleteProduct(@PathVariable("productId") int productId) {
		System.out.println("DELETING PRODUCT called with : " + productId);
		return productService.deleteProduct(productId);
	}

	// To save one product
	@PostMapping()
	public boolean saveProduct(@RequestBody Product product) {
		System.out.println("Saving a PRODUCT called ");
		System.out.println(product);
		return productService.addProduct(product);
	}

	// To update one product
	@PutMapping()
	public boolean updateProduct(@RequestBody Product product) {
		System.out.println("Updating a PRODUCT called ");
		System.out.println(product);
		return productService.updateProduct(product);
	}
}
