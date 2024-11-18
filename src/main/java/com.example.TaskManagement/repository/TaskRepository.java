package com.example.TaskManagement.repository;

import com.example.TaskManagement.model.Task;
import com.example.TaskManagement.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TaskRepository extends MongoRepository<Task, ObjectId> {
}
