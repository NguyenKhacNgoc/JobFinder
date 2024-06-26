package com.khacngoc.jobfinder.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column
    String companyName;
    @Column
    String title;
    @Column
    LocalDateTime startTime;
    @Column
    LocalDateTime endTime;
    @Column
    String jobDescription;
    @OneToOne(mappedBy = "workExperience")
    Profile profile;

}
