package youtube.YoutubeMiner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import youtube.YoutubeMiner.model.youtube.videoSnippet.VideoSnippetSearch;
import youtube.YoutubeMiner.model.youtube.videoSnippet.VideoSnippet;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    RestTemplate restTemplate;

    private String token = "AIzaSyDCGwmBSn9UcI-x6zv38s1wR73HW_i_Stg";

    public List<VideoSnippet> videoSearch(String videoId){
        String uri = "https://youtube.googleapis.com/youtube/v3/videos?part=snippet&id=" +videoId
                +"&key="+token;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-goog-api-key", token);
        HttpEntity<VideoSnippetSearch> request = new HttpEntity<>(null, headers);
        ResponseEntity<VideoSnippetSearch> response =
                restTemplate.exchange(uri, HttpMethod.GET, request, VideoSnippetSearch.class);
        return response.getBody().getItems();
    }
}
