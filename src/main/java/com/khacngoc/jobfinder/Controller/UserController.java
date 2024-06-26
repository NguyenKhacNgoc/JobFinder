package com.khacngoc.jobfinder.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khacngoc.jobfinder.DTO.Request.ChangePassWordRequest;
import com.khacngoc.jobfinder.DTO.Request.ProfileRequest;
import com.khacngoc.jobfinder.DTO.Request.RecruiterRequest;
import com.khacngoc.jobfinder.DTO.Request.UserDTO;
import com.khacngoc.jobfinder.DTO.Request.idRequest;
import com.khacngoc.jobfinder.DTO.Response.ApiResponse;
import com.khacngoc.jobfinder.DTO.Response.UserDTOResponse;
import com.khacngoc.jobfinder.Entity.User;
import com.khacngoc.jobfinder.Exception.AppException;
import com.khacngoc.jobfinder.Exception.ErrorCode;
import com.khacngoc.jobfinder.Repository.UserRepository;
import com.khacngoc.jobfinder.Services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getUsers")
    public ApiResponse<?> getUsers() {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getAllUser());
        return apiResponse;
    }

    @GetMapping("/findUser")
    public User fiUser(@RequestParam("userName") String userName) {
        return userRepository.findUserByEmail(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    @PostMapping("/createUser")
    public ApiResponse<UserDTOResponse> createUser(@Valid @RequestBody UserDTO request) {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;

    }

    @PutMapping("/updateInfoUser")
    public ApiResponse<?> updateInfoUser(@RequestBody ProfileRequest request) {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.updateInfoUser(request));
        return apiResponse;
    }

    @PostMapping("/createRecruiter")
    public ApiResponse<?> createRecruiter(@Valid @RequestBody UserDTO request) {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createRecruiter(request));
        return apiResponse;

    }

    @PutMapping("/updateInfoRecruiter")
    public ApiResponse<?> updateInfoRecruiter(@RequestBody RecruiterRequest request) {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.updateInfoRecruiter(request));
        return apiResponse;
    }

    @DeleteMapping("deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody idRequest request) {
        Optional<User> exU = userRepository.findUserById(request.getId());
        if (exU.isPresent()) {
            userRepository.delete(exU.get());
            return ResponseEntity.ok().body("Đã xoá");
        }
        return ResponseEntity.badRequest().body("Người dùng không tồn tại");
    }

    @PutMapping("changePassWord")
    public ApiResponse<UserDTOResponse> changePassword(@RequestBody ChangePassWordRequest request) {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.changePassWord(request));
        return apiResponse;
    }

    @GetMapping("getUser")
    public ApiResponse<UserDTOResponse> getUser(@RequestParam("id") String id) {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUser(id));
        return apiResponse;
    }

    @GetMapping("getMyInfo")
    public ApiResponse<UserDTOResponse> getMyInfo() {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getMyInfo());
        return apiResponse;
    }

}
