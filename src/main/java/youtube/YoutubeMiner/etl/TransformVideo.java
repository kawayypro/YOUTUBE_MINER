package youtube.YoutubeMiner.etl;

import youtube.YoutubeMiner.model.VideoMiner.VMCaption;
import youtube.YoutubeMiner.model.VideoMiner.VMComment;
import youtube.YoutubeMiner.model.VideoMiner.VMVideo;
import youtube.YoutubeMiner.model.youtube.videoSnippet.VideoSnippet;
import youtube.YoutubeMiner.model.youtube.videoSnippet.VideoSnippetDetails;

import java.util.LinkedList;
import java.util.List;

public class TransformVideo {

    public static VMVideo transformVideo(VideoSnippet video){
        return VMVideo.of(
                video.getId().getVideoId(),
                video.getSnippet().getTitle(),
                video.getSnippet().getDescription(),
                video.getSnippet().getPublishedAt(),
                parseoCaption(video),
                parseoComment(video)
        );

    }

    private static List<VMCaption> parseoCaption(VideoSnippet video) {

        List<VMCaption> listaCaption= new LinkedList<>();

        for(int i=0; i< video.getCaptions().size(); i++){
            VMCaption captionFinal= TransformCaption.transformCaption(video.getCaptions().get(i));
            listaCaption.add(captionFinal);
        }
        return listaCaption;
    }

    private static List<VMComment> parseoComment(VideoSnippet video){
        List<VMComment> listaComment= new LinkedList<>();
        for(int i=0; i<video.getComments().size(); i++){
            VMComment commentFinal = TransformComment.transformComment(video.getComments().get(i));
            listaComment.add(commentFinal);
        }
        return listaComment;
    }
}
