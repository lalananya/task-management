package com.example.TaskManagement.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.stereotype.Indexed;

import java.util.List;


//@Entity
@Document(collection = "users")
//@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class User {

    @Id
    @NonNull
    @Indexed(unique = true)
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String emailId;
    @NonNull
    private String role;
    private boolean sentimentAnalysis; // based on tasks we will send email to users about their moood / sentiments
    @DBRef
    private List<Task> tasks;

}
