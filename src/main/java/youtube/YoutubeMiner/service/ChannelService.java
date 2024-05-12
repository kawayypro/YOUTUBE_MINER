package youtube.YoutubeMiner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import youtube.YoutubeMiner.model.youtube.channel.Channel;
import youtube.YoutubeMiner.model.youtube.channel.ChannelSearch;

@Service
public class ChannelService {
    @Autowired
    RestTemplate restTemplate;

    private String token = "AIzaSyCSI0c-yBEh-9leEGPj7bpk2Yl2CjSd9XM" ;

    public Channel channelSearch(String id) {
        String uri = "https://www.googleapis.com/youtube/v3/channels?part=snippet&id="+"&key="+token;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-goog-api-key", token);
        HttpEntity<ChannelSearch> request = new HttpEntity<>(null, headers);
        ResponseEntity<ChannelSearch> response =
                restTemplate.exchange(uri, HttpMethod.GET, request, ChannelSearch.class);
        System.out.println(response.getBody().getItems().stream().findFirst().get().getVideos());
        return response.getBody().getItems().stream().findFirst().get();
    }

}