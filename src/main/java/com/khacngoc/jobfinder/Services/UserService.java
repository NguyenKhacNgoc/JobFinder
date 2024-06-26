package com.khacngoc.jobfinder.Services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.khacngoc.jobfinder.DTO.Request.ChangePassWordRequest;
import com.khacngoc.jobfinder.DTO.Request.ProfileRequest;
import com.khacngoc.jobfinder.DTO.Request.RecruiterRequest;
import com.khacngoc.jobfinder.DTO.Request.UserDTO;
import com.khacngoc.jobfinder.DTO.Response.UserDTOResponse;
import com.khacngoc.jobfinder.Entity.User;
import com.khacngoc.jobfinder.Enums.Role;
import com.khacngoc.jobfinder.Exception.AppException;
import com.khacngoc.jobfinder.Exception.ErrorCode;
import com.khacngoc.jobfinder.Mapper.UserMapper;
import com.khacngoc.jobfinder.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public UserDTOResponse toUserDTOResponse(User user) {
        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public List<UserDTOResponse> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDTOResponse> userDTOResponses = new ArrayList<>();
        for (User user : users) {
            userDTOResponses.add(toUserDTOResponse(user));

        }
        return userDTOResponses;

    }

    @PostAuthorize("returnObject.email == authentication.name")
    public UserDTOResponse getUser(String id) {
        return userMapper.toUserResponse(
                userRepository.findUserById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));

    }

    public UserDTOResponse createUser(UserDTO request) {
        if (userRepository.findUserByEmail(request.getEmail()).isPresent()) {
            throw new AppException(ErrorCode.USER_EXISTED);

        }
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassWord(passwordEncoder.encode(request.getPassWord()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRole(roles);
        return userMapper.toUserResponse(userRepository.save(user));

    }

    @PostAuthorize("returnObject.email == authentication.name")
    public UserDTOResponse updateInfoUser(ProfileRequest request) {
        User user = getMyUser();
        user.setProfile(userMapper.toProfile(request));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserDTOResponse createRecruiter(UserDTO request) {
        if (userRepository.findUserByEmail(request.getEmail()).isPresent()) {
            throw new AppException(ErrorCode.USER_EXISTED);

        }
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassWord(passwordEncoder.encode(request.getPassWord()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.RECRUITER.name());
        user.setRole(roles);
        return userMapper.toUserResponse(userRepository.save(user));

    }

    @PostAuthorize("returnObject.email == authentication.name")
    public UserDTOResponse updateInfoRecruiter(RecruiterRequest request) {
        User user = getMyUser();
        user.setProfile(userMapper.toRecruiter(request));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserDTOResponse changePassWord(ChangePassWordRequest request) {
        User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassWord(), user.getPassWord());
        if (authenticated) {
            user.setPassWord(passwordEncoder.encode(request.getNewPassWord()));
            userRepository.save(user);
            return userMapper.toUserResponse(user);
        }
        throw new AppException(ErrorCode.UNAUTHENTICATED);

    }

    public UserDTOResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String userName = context.getAuthentication().getName();
        return userMapper.toUserResponse(userRepository.findUserByEmail(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    public User getMyUser() {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();
        return userRepository.findUserByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }

}
