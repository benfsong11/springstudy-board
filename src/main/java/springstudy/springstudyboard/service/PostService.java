package springstudy.springstudyboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springstudy.springstudyboard.domain.content.Post;
import springstudy.springstudyboard.dto.PostDto;
import springstudy.springstudyboard.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void create(PostDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setBody(dto.getBody());
        post.setHits(0);
        post.setUser(dto.getUser());

        postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }

    public void updatePost(Long id, PostDto updatePostDto) {
        Post existingPost = postRepository.findById(id).orElse(null);

        if (existingPost != null) {
            existingPost.setTitle(updatePostDto.getTitle());
            existingPost.setBody(updatePostDto.getBody());

            postRepository.save(existingPost);
        }
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElse(null);

        if (post != null) {
            post.setDeleted(true);

            postRepository.save(post);
        }
    }
}
