package youtube.YoutubeMiner.service;

import org.junit.jupiter.api.DisplayName;
import youtube.YoutubeMiner.model.youtube.videoSnippet.VideoSnippet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VideoServiceTest {

    @Autowired
    VideoService videoService;

    @Test
    @DisplayName("Get a video")
    void videoSearch() {
        List<VideoSnippet> videos = videoService.videoSearch("UCZbWTaVsdcX0lJPZRyw33sQ",10,10);
        assertFalse(videos==null, "Videos list is empty");
        System.out.println(videos);
    }
}
