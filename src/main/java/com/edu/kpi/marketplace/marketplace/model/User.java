package com.edu.kpi.marketplace.marketplace.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection  = "Users")
public class User {

    @Id
    private String id;

    private String email;

    private String passwordHash;

    private String role;

    private String phone;
}
