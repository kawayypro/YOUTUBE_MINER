package youtube.YoutubeMiner.etl;

import youtube.YoutubeMiner.model.VideoMiner.VMCaption;
import youtube.YoutubeMiner.model.youtube.caption.Caption;

public class TransformCaption {

    public static VMCaption transformCaption(Caption captionYouTube){
        VMCaption captionFinal= new VMCaption();
        captionFinal.setId(captionYouTube.getId());
        captionFinal.setName(captionYouTube.getSnippet().getName());
        captionFinal.setLanguage(captionYouTube.getSnippet().getLanguage());
        return captionFinal;

    }
}
