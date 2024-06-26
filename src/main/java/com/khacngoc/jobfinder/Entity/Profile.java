package com.khacngoc.jobfinder.Entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column
    String fullName;
    @Column
    String phoneNumber;
    @Column
    Date dateOfBirth;
    @Column
    Boolean gender;
    @Column
    String desiredLocation;
    @Column
    String position;
    @Column
    Set<String> advancedSkill;
    @Column
    Set<String> softSkill;
    @Column
    Set<String> interest;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wrok_experience_id", referencedColumnName = "id")
    WorkExperience workExperience;

    @OneToOne(mappedBy = "profile")
    User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    Address address;
}
