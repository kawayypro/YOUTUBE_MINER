package youtube.YoutubeMiner.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import youtube.YoutubeMiner.model.youtube.channel.Channel;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChannelServiceTest {

    @Autowired
    ChannelService channelService;

    @Test
    @DisplayName("Get a channel")
    void channelSearch() {
        Channel channel = channelService.channelSearch("UCZbWTaVsdcX0lJPZRyw33sQ");
        assertFalse(channel==null, "Channel is empty");
        System.out.println(channel);
    }
}