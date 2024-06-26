package com.khacngoc.jobfinder.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.khacngoc.jobfinder.Entity.Career;

public interface CareerRepository extends JpaRepository<Career, String> {
    @Query("SELECT c FROM Career c WHERE c.careerName = :careerName")
    Optional<Career> findByCareerName(@Param("careerName") String careerName);

}
