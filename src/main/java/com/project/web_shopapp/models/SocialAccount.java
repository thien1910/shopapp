package com.project.web_shopapp.models;

import jakarta.persistence.*;
import lombok.*;

@Table(name= "social_accounts")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SocialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name",length = 20)
    private String fullName;
    @Column(name = "provider_id", nullable = false,length = 50)
    private String providerId;
    @Column(name = "name",length = 150)
    private String name;
    @Column(name = "email",length = 150)
    private String email;

}
