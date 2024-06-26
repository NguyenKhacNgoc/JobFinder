package com.khacngoc.jobfinder.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.khacngoc.jobfinder.DTO.Request.PostCreationRequest;
import com.khacngoc.jobfinder.DTO.Request.idRequest;
import com.khacngoc.jobfinder.DTO.Response.PostResponse;
import com.khacngoc.jobfinder.Entity.Post;
import com.khacngoc.jobfinder.Exception.AppException;
import com.khacngoc.jobfinder.Exception.ErrorCode;
import com.khacngoc.jobfinder.Mapper.PostMapper;
import com.khacngoc.jobfinder.Repository.PostRepository;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CareerService careerService;

    @PreAuthorize("hasAuthority('SCOPE_RECRUITER')")
    public PostResponse createNewPost(PostCreationRequest request) {
        return postMapper.toPostResponse(postRepository.save(Post.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .career(careerService.getCareerFromID(request.getCareer().getId()))
                .exp(request.getExp())
                .level(request.getLevel())
                .salaries(request.getSalaries())
                .status("pending")

                .user(userService.getMyUser())
                .build()));

    }

    @PostAuthorize("returnObject.user.email = authentication.name")
    public PostResponse hidenPost(idRequest request) {
        Post post = postRepository.findById(request.getId())
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_EXISTED));
        post.setStatus("hiden");
        return postMapper.toPostResponse(postRepository.save(post));
    }

    public List<PostResponse> postResponseTodoList(List<Post> posts) {
        List<PostResponse> postResponses = new ArrayList<>();
        for (Post post : posts) {
            postResponses.add(postMapper.toPostResponse(post));
        }
        return postResponses;
    }

    public List<PostResponse> getAllPost() {
        List<Post> posts = postRepository.findAll();
        return postResponseTodoList(posts);
    }

    public PostResponse getPostByID(String id) {
        return postMapper.toPostResponse(
                postRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_EXISTED)));
    }

    public List<PostResponse> getMyPost() {
        return postResponseTodoList(postRepository.findByUser(userService.getMyUser()));
    }
    /*
     * USER:
     * - đăng ký
     * - đăng nhập
     * - đăng xuất
     * - đổi mật khẩu
     * - xem thông tin tài khoản
     * - cập nhật thông tin tài khoản
     * - tìm kiếm việc làm(theo nhiều tiêu chí)
     * - xem chi tiết bài tuyển dụng
     * - xem thông tin nhà tuyển dụng(công ty)
     * - xem danh sách các việc làm của nhà tuyển dụng
     * - apply việc làm(có thể tải cv dạng pdf lên hệ thống)
     * - thống kê các việc làm mà bản thân đã apply
     * - nhắn tin(làm firebase - client)
     * RECRUITER:
     * - đăng bài
     * - ẩn bài
     * - thống kê số lượng ứng viên đã apply cho từng công việc
     * - xem danh sách các ứng viên đã apply
     * - xem thông tin ứng tuyển của từng ứng viên
     * - xem profile của ứng viên
     * ADMIN:
     * - crud đối với bảng career
     * - duyệt bài đăng
     * - tiếp nhận tố cáo và xử lý các trường hợp phá website
     */

}
