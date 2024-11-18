package com.example.TaskManagement.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "configapi")
//@Entity
@Data
@NoArgsConstructor
//@Table(name = "configapi")
public class ConfigApp {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String configkey;
    private String configvalue;

}
