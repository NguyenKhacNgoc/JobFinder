package com.khacngoc.jobfinder.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.khacngoc.jobfinder.DTO.Request.CareerRequest;
import com.khacngoc.jobfinder.DTO.Request.PostCreationRequest;
import com.khacngoc.jobfinder.DTO.Response.ApiResponse;
import com.khacngoc.jobfinder.Services.CareerService;
import com.khacngoc.jobfinder.Services.PostService;

@RestController
@ResponseBody
@RequestMapping("/api")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private CareerService careerService;

    @PostMapping("/career/create")
    public ApiResponse<?> createNewCareer(@RequestBody CareerRequest request) {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResult(careerService.createNewCareer(request));
        return apiResponse;
    }

    @GetMapping("/career/get")
    public ApiResponse<?> getAllCareer() {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResult(careerService.getAllCareer());
        return apiResponse;
    }

    @PostMapping("/createNewPost")
    public ApiResponse<?> createNewPost(@RequestBody PostCreationRequest request) {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResult(postService.createNewPost(request));
        return apiResponse;

    }

    @GetMapping("/getAllPost")
    public ApiResponse<?> getAllPost() {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResult(postService.getAllPost());
        return apiResponse;
    }

    @GetMapping("/getPost")
    public ApiResponse<?> getPostByID(String id) {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResult(postService.getPostByID(id));
        return apiResponse;
    }

    @GetMapping("getMyPost")
    public ApiResponse<?> getMyPost() {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResult(postService.getMyPost());
        return apiResponse;
    }

}
