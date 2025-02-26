package com.echo.api.model;

 
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "jobs")
public class JobServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 255)
    private String company;

    @Column(length = 500)
    private String logoUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    public JobServiceEntity() {
        this.createDate = Instant.now();
    }
    
    @Column(name = "lookup_type",nullable = false, length = 255)
    private String lookupType; //posttype;
    
    @Column(name = "feature_description",nullable = false, length = 255)
    private String featureDescription;
    
    @Column(nullable = false, length = 255)
    private String brand;

    public JobServiceEntity(String title, String company, String logoUrl, String description,String lookupType,String brand,String featureDescription) {
        this.title = title;
        this.company = company;
        this.logoUrl = logoUrl;
        this.description = description;
        this.createDate = Instant.now();
        this.lookupType = lookupType;
        this.brand = brand;
        this.featureDescription = featureDescription;
    }
}
