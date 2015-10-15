package com.ciena.sca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = "com.ciena.sca")

class MongoConfig extends AbstractMongoConfiguration {

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient();
	}
	
	@Override
	protected String getDatabaseName() {
		return "CienaSCA";
	}
	
	
	// set "." replacement char so we don't fail if "." chars used as keys in a Map object
	@Bean
	@Override
	public MappingMongoConverter mappingMongoConverter() throws Exception {
		MappingMongoConverter mmc = super.mappingMongoConverter();
		mmc.setMapKeyDotReplacement("\\+");
		return mmc;
	}
}

