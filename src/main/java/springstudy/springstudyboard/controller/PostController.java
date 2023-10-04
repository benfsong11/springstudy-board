package springstudy.springstudyboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springstudy.springstudyboard.domain.User;
import springstudy.springstudyboard.domain.content.Comment;
import springstudy.springstudyboard.domain.content.Post;
import springstudy.springstudyboard.domain.content.PostHitsLog;
import springstudy.springstudyboard.dto.CommentDto;
import springstudy.springstudyboard.dto.PostDto;
import springstudy.springstudyboard.service.CommentService;
import springstudy.springstudyboard.service.PostHitsLogService;
import springstudy.springstudyboard.service.PostService;
import springstudy.springstudyboard.service.UserService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/board/post")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final PostHitsLogService postHitsLogService;
    private final CommentService commentService;

    @Autowired
    public PostController(PostService postService,
                          UserService userService,
                          PostHitsLogService postHitsLogService,
                          CommentService commentService) {
        this.postService = postService;
        this.userService = userService;
        this.postHitsLogService = postHitsLogService;
        this.commentService = commentService;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public void createBoard(@RequestBody PostDto dto) {
        postService.create(dto);
    }

    @GetMapping("/read")
    public Post getBoardById(@RequestParam("userId") Long userId, @RequestParam("boardId") Long boardId) {
        User user = userService.getUserById(userId).orElse(null);
        Post post = postService.getPostById(boardId).orElse(null);
        PostHitsLog postHitsLog = post.createHitsLog(user);

        postHitsLogService.save(postHitsLog);

        return post;
    }

    @PutMapping("/update/{id}")
    public void updateBoard(@PathVariable Long id, @RequestBody PostDto updatePost) {
        postService.updatePost(id, updatePost);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @PostMapping("/comment/new")
    public void createComment(@RequestBody CommentDto dto) {
        commentService.create(dto);
    }

    @GetMapping("/comment")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }
}
