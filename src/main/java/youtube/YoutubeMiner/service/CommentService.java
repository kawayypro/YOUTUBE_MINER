package youtube.YoutubeMiner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import youtube.YoutubeMiner.model.youtube.comment.Comment;
import youtube.YoutubeMiner.model.youtube.comment.CommentSearch;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    private String token = "AIzaSyCSI0c-yBEh-9leEGPj7bpk2Yl2CjSd9XM" ;

    public List<Comment> commentsSearch(String videoId){
        String uri = "https://www.googleapis.com/youtube/v3/commentThreads?part=snippet&videoId="+videoId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-goog-api-key", token);
        HttpEntity<CommentSearch> request = new HttpEntity<>(null,headers);
        ResponseEntity<CommentSearch> response =
                restTemplate.exchange(uri, HttpMethod.GET, request, CommentSearch.class);
        return response.getBody().getItems();
    }

}