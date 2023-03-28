package org.example.service;

import org.example.exception.NotFoundException;
import org.example.model.Post;
import org.example.model.PostDTO;
import org.example.repository.PostRepositoryStubImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepositoryStubImpl repository;
    
    public PostService(PostRepositoryStubImpl repository) {
        this.repository = repository;
    }
    
    public List<PostDTO> all() {
        return repository.all()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public PostDTO getById(long id) {
        return repository.getById(id)
                .map(this::convertToDTO)
                .orElseThrow(NotFoundException::new);
    }
    
    public PostDTO save(PostDTO post) {
        return convertToDTO(repository.save(convertToEntity(post)));
    }
    
    public void removeById(long id) {
       repository.removeById(id);
    }
    
    private PostDTO convertToDTO(Post post) {
        return new PostDTO(post.getId(), post.getContent());
    }
    
    private Post convertToEntity(PostDTO postDTO) {
        return new Post(postDTO.getId(), postDTO.getContent());
    }
}

