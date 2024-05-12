package youtube.YoutubeMiner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import youtube.YoutubeMiner.exception.MaxValueException;
import youtube.YoutubeMiner.model.youtube.videoSnippet.VideoSnippetSearch;
import youtube.YoutubeMiner.model.youtube.videoSnippet.VideoSnippet;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CaptionService captionService;

    @Autowired
    CommentService commentService;

    public List<VideoSnippet> videoSearch(String channelId, Integer maxComments, Integer maxVideos) {

        String token = "AIzaSyDY4ScR2b2JbKLI8oUPMphj2ibylDVJCZA";
        String uri = "https://www.googleapis.com/youtube/v3/search?part=snippet&type=video&channelId="+channelId+"&maxResults=" + maxVideos+"&key="+ token;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-goog-api-key", token);
        HttpEntity<VideoSnippetSearch> request = new HttpEntity<>(null, headers);
        ResponseEntity<VideoSnippetSearch> response =
                restTemplate.exchange(uri, HttpMethod.GET, request, VideoSnippetSearch.class);
        List<VideoSnippet> videos = response.getBody().getItems();
        List<VideoSnippet> videosNew = new ArrayList<>();
        for (VideoSnippet v:videos){
            v.setCaptions(captionService.captionSearch(v.getId().getVideoId()));
            v.setComments(commentService.commentsSearch(v.getId().getVideoId(),maxComments));
            videosNew.add(v);
        }
        return videosNew;
    }
}
