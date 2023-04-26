package com.myblog.myblog.service;

import com.myblog.myblog.payload.PostDto;
import com.myblog.myblog.payload.PostResponse;

import java.util.List;


   public interface PostService {

   PostDto createPost(PostDto postDto);

//   List<PostDto> getAllPosts();

//    List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
      PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

   PostDto updatePost(PostDto postDto, long id);

   void deletePostById(long id);
}
