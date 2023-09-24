package com.lexoffice.reactreleases.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchHistory {
    @Id
    private String loginUser;
    private LocalDate startCreatedAt;
    private LocalDate endCreatedAt;
    private String author;
}
