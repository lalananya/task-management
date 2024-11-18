package com.example.TaskManagement.model;


import com.example.TaskManagement.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
//@Entity
//@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class Task {

    @Id
    @NonNull
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;
    @NonNull
    private String description;
    @NonNull
    private String title;
    @NonNull
    private String status;
    @NonNull
    private String username;

    private TaskStatus taskStatus;
}
