package com.digitalinnovationone.heroesapi.config;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import static com.digitalinnovationone.heroesapi.constans.HeroesConstant.REGION_DYNAMO;
import static com.digitalinnovationone.heroesapi.constans.HeroesConstant.ENDPOINT_DYNAMO;
/*No Dynamo não criamos tabela, apenas passamos os
 *arquivos de configurações da tabela /, criamos o ID
 *e o restante das colunas, quando formos fazer uma
 *requisição POST é que vamos realmente criar as tabelas*/
@Configuration
@EnableDynamoDBRepositories
public class HeroesTable 
{	public static void main(String[] args)
	{	AmazonDynamoDB client = AmazonDynamoDBClientBuilder
		.standard().withEndpointConfiguration(new AwsClientBuilder
				.EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO)).build();
	
		DynamoDB dynamoDB = new DynamoDB(client);
		
		String tableName = "Heroes_Table_Demo1";

		try
		{	Table table = dynamoDB.createTable(tableName,
				Arrays.asList(new KeySchemaElement("id", KeyType.HASH)),
				Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.S)),
				new ProvisionedThroughput(5L, 5L));
			table.waitForActive();
		}
		catch(Exception e)	{System.out.println(e.getMessage());}
	}
} 