package youtube.YoutubeMiner.service;

import org.junit.jupiter.api.DisplayName;
import youtube.YoutubeMiner.exception.MaxValueException;
import youtube.YoutubeMiner.model.youtube.comment.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    @DisplayName("Get a comment")
    void commentsSearch() {
        List<Comment> comments = commentService.commentsSearch("rRZdY6Pnz4A",10);
        assertFalse(comments==null, "Comments list is empty");
        System.out.println(comments);
    }
}