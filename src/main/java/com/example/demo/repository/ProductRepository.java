package com.example.demo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.example.demo.model.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.List;
import java.util.Map;

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

  public List<Product> getProductsByPage() {
    return mapper.scan(Product.class,
        new DynamoDBScanExpression()
            .withLimit(2)
            .withExclusiveStartKey(Map.of(":productType", new AttributeValue().withS("TV")))
    );
  }

  public List<Product> getPhoneProducts() {
    var expressionValue = Map.of(
        ":productType", new AttributeValue().withS("PHONE")
    );
    return mapper.scan(Product.class, new DynamoDBScanExpression()
        .withFilterExpression(":productType = productType")
        .withExpressionAttributeValues(expressionValue));
  }

}