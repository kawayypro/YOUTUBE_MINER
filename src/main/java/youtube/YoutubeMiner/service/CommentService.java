package youtube.YoutubeMiner.service;

import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import youtube.YoutubeMiner.exception.MaxValueException;
import youtube.YoutubeMiner.model.youtube.comment.Comment;
import youtube.YoutubeMiner.model.youtube.comment.CommentSearch;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    public List<Comment> commentsSearch(String videoId, Integer maxComments) {
        String token = "AIzaSyDY4ScR2b2JbKLI8oUPMphj2ibylDVJCZA";
        String uri = "https://www.googleapis.com/youtube/v3/commentThreads?part=snippet&videoId="+videoId+"&key="+ token + "&maxResults=" + maxComments;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-goog-api-key", token);
        HttpEntity<CommentSearch> request = new HttpEntity<>(null,headers);

        List<Comment> res = new ArrayList<Comment>();
        try{
            ResponseEntity<CommentSearch> response =
                    restTemplate.exchange(uri, HttpMethod.GET, request, CommentSearch.class);
            if(response.getBody().getItems() != null) {
                res = response.getBody().getItems();
            }
        }
        catch (HttpClientErrorException.Forbidden err) {
            if(err.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) {
                throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
            }
        }
        return res;
    }

}