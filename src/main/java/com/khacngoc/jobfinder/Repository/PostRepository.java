package com.khacngoc.jobfinder.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khacngoc.jobfinder.Entity.Post;
import com.khacngoc.jobfinder.Entity.User;

public interface PostRepository extends JpaRepository<Post, String> {
    List<Post> findByUser(User user);

}
