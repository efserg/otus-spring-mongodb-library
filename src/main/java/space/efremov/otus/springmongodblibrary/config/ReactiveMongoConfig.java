package space.efremov.otus.springmongodblibrary.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "space.efremov.otus.springmongodblibrary")
public class ReactiveMongoConfig extends AbstractReactiveMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "reactive_library";
    }

    @Bean
    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create();
    }
}
