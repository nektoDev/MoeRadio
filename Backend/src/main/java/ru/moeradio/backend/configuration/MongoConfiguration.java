package ru.moeradio.backend.configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * ru.moeradio.backend.configuration
 * Backend
 *
 * @author Tsykin V.A.
 *         tsykin.vyacheslav@otr.ru
 * @date 20.08.15
 */

@Configuration
@EnableMongoRepositories(basePackages = "ru.moeradio.backend.repository")
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "moeradio";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("192.168.99.100", 32769);
    }
}
