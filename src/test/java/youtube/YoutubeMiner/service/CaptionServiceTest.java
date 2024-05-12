package youtube.YoutubeMiner.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import youtube.YoutubeMiner.model.youtube.caption.Caption;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class CaptionServiceTest {

    @Autowired
    CaptionService captionService;

    @Test
    @DisplayName("Get a caption")
    void captionSearch(){
        List<Caption> caption = captionService.captionSearch("rRZdY6Pnz4A");
        assertFalse(caption==null,"caption is empty");
        System.out.println(caption);
    }
}
