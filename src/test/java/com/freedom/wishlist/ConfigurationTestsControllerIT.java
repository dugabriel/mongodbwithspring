//package com.freedom.wishlist;
//
//import com.freedom.wishlist.domain.Customer;
//import com.freedom.wishlist.repository.CustomerRepository;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.util.TestPropertyValues;
//import org.springframework.context.ApplicationContextInitializer;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.testcontainers.containers.MongoDBContainer;
//import org.testcontainers.junit.jupiter.Container;
//
//import java.util.LinkedList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(locations = "classpath:applicationtests.properties")
//public abstract class ConfigurationTestsControllerIT {
//
//    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
//
//    static {
//        mongoDBContainer.start();
//    }
//
//    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//        @Override
//        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
//            List<String> pairs = new LinkedList<>();
//
//            pairs.add("spring.data.mongodb.uri=" + mongoDBContainer.getReplicaSetUrl());
//
//            TestPropertyValues.of(pairs).applyTo(configurableApplicationContext.getEnvironment());
//
//        }
//    }
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Test
//    void shouldReturnListOfCustomerWithMatchingRate() {
//        this.customerRepository.save(new Customer());
//
//        List<Customer> customers = customerRepository.findAll();
//
//        assertEquals(1, customers.size());
//    }
//}
