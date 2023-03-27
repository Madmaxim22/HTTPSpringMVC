package org.example.controller;

import com.google.gson.Gson;
import org.example.model.Post;
import org.example.model.PostDTO;
import org.example.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;
    private final Gson gson;
    
    public PostController(PostService service) {
        this.service = service;
        this.gson = new Gson();
    }
    
    @GetMapping
    public List<PostDTO> all() {
        return service.all();
    }
    
    @GetMapping("/{id}")
    public PostDTO getById(@PathVariable long id) {
        return service.getById(id);
    }
    
    @PostMapping
    public PostDTO save(@RequestBody Post post) {
        return service.save(post);
    }
    
    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id) {
        service.removeById(id);
    }
}
