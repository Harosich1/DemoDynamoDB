package com.example.demo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.example.demo.model.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

  private final DynamoDBMapper mapper;

  public Product findById(String id) {
    return mapper.load(Product.class, id);
  }

  public void create(Product product) {
    mapper.save(product);
  }

  public List<Product> getAllProducts() {
    return mapper.scan(Product.class, new DynamoDBScanExpression());
  }

}