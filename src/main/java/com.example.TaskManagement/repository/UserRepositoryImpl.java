package com.example.TaskManagement.repository;

import com.example.TaskManagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {


    @Autowired
    private MongoTemplate monogoTemplate; // to interact with mongodb easily we can use mongo template

    public List<User> getUsersForSentimentAnalysis() {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").exists(true).ne(null).ne(""));
        query.addCriteria(Criteria.where("sentimentAnalysis").is("true"));
        List<User> users =  monogoTemplate.find(query, User.class); // the ultimate use of ORM, we have provided entity class not the actual collection
        return users;
    }
}
