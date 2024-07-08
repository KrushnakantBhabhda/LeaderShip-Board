package com.crio.starterapp.Entity;

import java.util.HashSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class User {

    @Id 
    String userId;

    String userName;

    @Min(0)
    @Max(100)
    int Score;

    HashSet<String> Badges;


    
}
