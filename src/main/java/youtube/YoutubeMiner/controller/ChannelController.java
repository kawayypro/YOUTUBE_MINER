package youtube.YoutubeMiner.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;
import youtube.YoutubeMiner.etl.TransformCaption;
import youtube.YoutubeMiner.etl.TransformChannel;
import youtube.YoutubeMiner.etl.TransformComment;
import youtube.YoutubeMiner.etl.TransformVideo;
import youtube.YoutubeMiner.exception.MaxValueException;
import youtube.YoutubeMiner.model.VideoMiner.VMCaption;
import youtube.YoutubeMiner.model.VideoMiner.VMChannel;
import youtube.YoutubeMiner.model.VideoMiner.VMComment;
import youtube.YoutubeMiner.model.VideoMiner.VMVideo;
import youtube.YoutubeMiner.model.youtube.channel.Channel;
import youtube.YoutubeMiner.model.youtube.videoSnippet.VideoSnippet;
import youtube.YoutubeMiner.service.CaptionService;
import youtube.YoutubeMiner.service.ChannelService;
import youtube.YoutubeMiner.service.CommentService;
import youtube.YoutubeMiner.service.VideoService;

import java.util.LinkedList;
import java.util.List;



@Tag(name= "Youtube", description =  "Youtube management API")
@RestController
@RequestMapping("/channels")
public class ChannelController {

    @Autowired
    ChannelService channelService;
    @Autowired
    VideoService videoService;
    @Autowired
    RestTemplate restTemplate;

    @Operation(
            summary = "Retrieve a Youtube channel by id",
            description= "Get a Youtube channel by specifying its id",
            tags = {"channel", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Youtube channel", content = {@Content(schema = @Schema(implementation = Channel.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })

    @GetMapping("/{id}")
    public VMChannel findChannel(@Parameter(description = "User's id of the channel ") @PathVariable String id,
                                 @Parameter(description = "Optional parameter to limit the number of videos")@RequestParam(required = false, defaultValue = "10") Integer sizeVideo,
                                 @Parameter(description = "Optional parameter to limit the number of comments")@RequestParam(required = false, defaultValue = "10") Integer sizeComment
    ) throws MaxValueException {
        if(sizeComment < 0 || sizeVideo <0){
            throw new MaxValueException();
        }
        Channel channelYoutube = channelService.channelSearch(id);

        List<VideoSnippet> videosYoutube = videoService.videoSearch(id,sizeComment,sizeVideo);
        channelYoutube.setVideos(videosYoutube);

        VMChannel channel = TransformChannel.transformChannel(channelYoutube);
        return channel;
    }
    @Operation(
            summary = "Insert a Channel in VideoMiner",
            description= "Add a new Youtube channel (looked by its id in YouTube) whose data is passed in the body of the request in Json format",
            tags = {"channels", "post"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Youtube channel", content = {@Content(schema = @Schema(implementation = Channel.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })



    @PostMapping("/{id}")
    public VMChannel postChannel(@Parameter(description = "User's id of the channel")@PathVariable String id,
                               @Parameter(description = "Optional parameter to limit the number of videos")@RequestParam(required = false, defaultValue = "10") Integer sizeVideo,
                               @Parameter(description = "Optional parameter to limit the number of comments")@RequestParam(required = false, defaultValue = "10") Integer sizeComment
    ) throws MaxValueException {
        VMChannel channel=findChannel(id, sizeVideo, sizeComment);
        String uri = "http://localhost:8080/videominer/channels";

        HttpHeaders headers= new HttpHeaders();

        HttpEntity<VMChannel> request = new HttpEntity<>(channel,headers);
        ResponseEntity<VMChannel> response = restTemplate.exchange(uri, HttpMethod.POST, request, VMChannel.class);
        return response.getBody();
    }

}
