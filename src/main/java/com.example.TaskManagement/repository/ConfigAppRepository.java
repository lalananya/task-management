package com.example.TaskManagement.repository;

import com.example.TaskManagement.model.ConfigApp;
import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//public interface ConfigTaskManagementAppRepository extends MongoRepository<ConfigTaskManagementApp, ObjectId> {
//}


@Repository
public interface ConfigAppRepository extends MongoRepository<ConfigApp, ObjectId> {
}
