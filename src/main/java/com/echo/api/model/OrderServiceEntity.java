package com.echo.api.model;

 
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "orders")
public class OrderServiceEntity {

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

    public OrderServiceEntity() {
        this.createDate = Instant.now();
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public OrderServiceEntity(String title, String company, String logoUrl, String description) {
        this.title = title;
        this.company = company;
        this.logoUrl = logoUrl;
        this.description = description;
        this.createDate = Instant.now();
    }
}
