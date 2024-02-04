package com.example.demo.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DynamoDBConfig {

  @Bean
  protected DynamoDBMapper dynamoDBMapper() {
    return new DynamoDBMapper(amazonDynamoDB());
  }

  private AmazonDynamoDB amazonDynamoDB() {
    var adb = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
        new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "kz")
    ).withCredentials(awsCredential()).build();
    var request = new CreateTableRequest(
        List.of(
            new AttributeDefinition("id", ScalarAttributeType.S)
        ),
        "product",
        List.of(
            new KeySchemaElement("id", KeyType.HASH)
        ),
        new ProvisionedThroughput(1L, 1L)
    );
    adb.createTable(request);
    return adb;
  }

  private AWSCredentialsProvider awsCredential() {
    return new AWSStaticCredentialsProvider(new BasicAWSCredentials("id", "key"));
  }

}
