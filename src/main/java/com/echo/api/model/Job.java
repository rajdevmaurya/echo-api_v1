package com.echo.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import java.time.Instant;

@Entity
public class Job {

    @Id
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String company;

    @Column
    private String logoUrl;

    @Column
    private String description;

    @Column(nullable = false)
    private String createDate;

    public Job() {
        this.createDate = Instant.now().toString();
    }

    public Job(String title, String company, String logoUrl, String description) {
        this.title = title;
        this.company = company;
        this.logoUrl = logoUrl;
        this.description = description;
        this.createDate = Instant.now().toString();
    }
}
