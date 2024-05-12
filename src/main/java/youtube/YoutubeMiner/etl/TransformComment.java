package youtube.YoutubeMiner.etl;

import youtube.YoutubeMiner.model.VideoMiner.VMComment;
import youtube.YoutubeMiner.model.VideoMiner.VMUser;
import youtube.YoutubeMiner.model.youtube.comment.Comment;

import java.util.HashMap;
import java.util.Map;

public class TransformComment {

    private static Long num = 0L;
    private static Map<Long, String> map = new HashMap<>();

    public static VMComment transformComment(Comment commentYouTube){
        VMComment commentFinal= new VMComment();
        commentFinal.setId(commentYouTube.getCommentSnippet().getTopLevelComment().getId());

        String authorId = commentYouTube.getCommentSnippet().getTopLevelComment().getSnippet().getAuthorChannelId().getValue();

        VMUser autorFinal= new VMUser();
        autorFinal.setId(null);
        autorFinal.setName(commentYouTube.getCommentSnippet().getTopLevelComment().getSnippet().getAuthorDisplayName());
        autorFinal.setUser_link(commentYouTube.getCommentSnippet().getTopLevelComment().getSnippet().getAuthorChannelUrl());
        autorFinal.setPicture_link(commentYouTube.getCommentSnippet().getTopLevelComment().getSnippet().getAuthorProfileImageUrl());
        commentFinal.setAuthor(autorFinal);

        commentFinal.setText(commentYouTube.getCommentSnippet().getTopLevelComment().getSnippet().getTextOriginal());
        commentFinal.setCreatedOn(commentYouTube.getCommentSnippet().getTopLevelComment().getSnippet().getPublishedAt());
        return commentFinal;

    }
}
