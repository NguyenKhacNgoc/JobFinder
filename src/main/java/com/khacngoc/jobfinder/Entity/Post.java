package com.khacngoc.jobfinder.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column
    String title;
    @Column
    String description;
    @ManyToOne
    @JoinColumn(name = "career_id", referencedColumnName = "id")
    Career career;
    @Column
    Float exp;
    @Column
    String level;
    @Column
    Float salaries;
    String status;
    @ManyToOne
    User user;
    @Column
    LocalDateTime createAt;

}
