package youtube.YoutubeMiner.etl;

import youtube.YoutubeMiner.model.VideoMiner.VMChannel;
import youtube.YoutubeMiner.model.VideoMiner.VMVideo;
import youtube.YoutubeMiner.model.youtube.channel.Channel;
import youtube.YoutubeMiner.model.youtube.videoSnippet.VideoSnippet;

import java.util.LinkedList;
import java.util.List;

public class TransformChannel {

    public static VMChannel transformChannel(Channel channelYouTube){
        VMChannel channelFinal = VMChannel.of(
                channelYouTube.getId(),
                channelYouTube.getSnippet().getDescription(),
                channelYouTube.getSnippet().getTitle(),
                channelYouTube.getSnippet().getPublishedAt(),
                parseoVideos(channelYouTube.getVideos()));
        return channelFinal;
    }

    private static List<VMVideo> parseoVideos(List<VideoSnippet> videos) {
        List<VMVideo> listaVideo = new LinkedList<>();
        for(VideoSnippet v:videos){
            VMVideo videoFinal= TransformVideo.transformVideo(v);
            listaVideo.add(videoFinal);
        }
        return listaVideo;
    }
}
