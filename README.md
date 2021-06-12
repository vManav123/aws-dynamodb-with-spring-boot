# AWS-DynamoDB-with-Spring-Boot

we’ll explore the basics of integrating DynamoDB into a Spring Boot Application with a hands-on, practical example project.

We’ll demonstrate how to configure an application to use a local DynamoDB instance using Spring Data. We'll also create an example data model and repository class as well as perform actual database operations using an integration test.

## DynamoDB
DynamoDB is a fully-managed hosted NoSQL database on AWS, similar to other NoSQL databases such as Cassandra or MongoDB. DynamoDB offers fast, consistent and predictable performance and is massively scalable.

You can learn more about DynamoDB on the AWS Documentation.

Let's install a local instance of DynamoDB to avoid incurring the cost of running a live instance.

For development, running DynamoDB locally makes more sense than running on AWS; the local instance will be run as an executable JAR file.

You can find instructions on how to run DynamoDB locally here.

## Maven Dependencies
Add the following dependencies to start working with DynamoDB using Spring Data:

```javascript
<dependency>
  <groupId>com.amazonaws</groupId>
  <artifactId>aws-java-sdk-dynamodb</artifactId>
  <version>1.12.4</version>
</dependency>
```
## Configuration
Next, let's define the following properties in the application.properties file:

```javascript
cloud:
  aws:
    credentials:
      access-key: AKIA57Y75DGUH2HM52BX
      secret-key: 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
    endpointUrl: https://dynamodb.us-east-2.amazonaws.com
    region:
      static: us-east-2
    stack:
      auto: false
```
The access and secret keys listed above are just arbitrary values for your local config. When accessing a local instance of DynamoDB these fields need to be populated by some values but are not needed to actually authenticate.

The properties will be dynamically pulled out of the application.properties file in the Spring config:

```javascript
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;
    @Value("${cloud.aws.region.static}")
    private String region;
    @Value("${cloud.aws.endpointUrl}")
    private String url;


    @Bean
    public DynamoDBMapper dynamoDBMapper(){return new DynamoDBMapper(buildAmazonDynamoDB());}

    @Bean
    public AmazonDynamoDB buildAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(url,region))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey,secretKey)))
                .build();
    }
```

## Conclusion
And we're done – we can now connect to DynamoDB from a Spring Boot Application.

Of course, after completing testing locally, we should be able to transparently use a live instance of DynamoDB on AWS and run the deployed code with only minor configuration changes.
