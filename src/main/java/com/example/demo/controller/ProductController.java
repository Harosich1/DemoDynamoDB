package com.example.demo.controller;

import com.example.demo.model.entity.Product;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

  private final ProductRepository productRepository;

  @PutMapping(value = "/create-product")
  @ResponseStatus(HttpStatus.CREATED)
  public void createProduct(@RequestBody Product product) {
    productRepository.create(product);
  }

  @GetMapping(value = "/get-product/{id}")
  @ResponseStatus(HttpStatus.FOUND)
  public Product getProduct(@PathVariable(name = "id") String id) {
    return productRepository.findById(id);
  }

  @GetMapping(value = "/get-all-product")
  @ResponseStatus(HttpStatus.FOUND)
  public List<Product> getAllProduct() {
    return productRepository.getAllProducts();
  }

  @GetMapping(value = "/get-page-product")
  @ResponseStatus(HttpStatus.FOUND)
  public List<Product> getPageProduct() {
    return productRepository.getProductsByPage();
  }

  @GetMapping(value = "/get-phone-product")
  @ResponseStatus(HttpStatus.FOUND)
  public List<Product> getPhoneProduct() {
    return productRepository.getPhoneProducts();
  }
}
