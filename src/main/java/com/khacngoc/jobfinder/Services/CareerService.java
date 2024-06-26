package com.khacngoc.jobfinder.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.khacngoc.jobfinder.DTO.Request.CareerRequest;
import com.khacngoc.jobfinder.Entity.Career;
import com.khacngoc.jobfinder.Exception.AppException;
import com.khacngoc.jobfinder.Exception.ErrorCode;
import com.khacngoc.jobfinder.Repository.CareerRepository;

@Service
public class CareerService {
    @Autowired
    private CareerRepository careerRepository;

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Career createNewCareer(CareerRequest request) {
        if (careerRepository.findByCareerName(request.getCareerName()).isPresent()) {
            throw new AppException(ErrorCode.CAREER_EXISTED);

        }
        Career career = new Career();
        career.setCareerName(request.getCareerName());
        return careerRepository.save(career);
    }

    public List<Career> getAllCareer() {
        return careerRepository.findAll();

    }

    public Career getCareerFromID(String id) {
        return careerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CAREER_NOT_EXISTED));
    }

}
