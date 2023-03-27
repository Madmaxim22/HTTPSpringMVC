package org.example.controller;

import com.google.gson.Gson;
import org.example.model.Post;
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
    public List<Post> all() {
        return service.all();
    }
    
    @GetMapping("/{id}")
    public Post getById(@PathVariable long id) {
        return service.getById(id);
    }
    
    @PostMapping
    public Post save(@RequestBody Post post) {
        return service.save(post);
    }
    
    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id) {
        service.removeById(id);
    }
}
