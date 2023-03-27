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
                .filter(post -> !post.isRemoved())
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public PostDTO getById(long id) {
        return repository.getById(id)
                .filter(post -> !post.isRemoved())
                .map(this::convertToDTO)
                .orElseThrow(NotFoundException::new);
    }
    
    public PostDTO save(Post post) {
        if (post.isRemoved()) {
            throw new NotFoundException("NOT FOUND");
        }
        return convertToDTO(repository.save(post));
    }
    
    public void removeById(long id) {
        Post post = repository.getById(id).orElseThrow(NotFoundException::new);
        post.setRemoved(true);
    }
    
    private PostDTO convertToDTO(Post post) {
        return new PostDTO(post.getId(), post.getContent());
    }
}

