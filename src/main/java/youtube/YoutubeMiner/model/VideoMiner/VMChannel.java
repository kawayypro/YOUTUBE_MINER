package youtube.YoutubeMiner.model.VideoMiner;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Channel")
public class VMChannel {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    @NotEmpty(message = "Channel name cannot be empty")
    private String name;

    @JsonProperty("description")
    @Column(columnDefinition="TEXT")
    private String description;

    @JsonProperty("createdTime")
    @NotEmpty(message = "Channel creation time cannot be empty")
    private String createdTime;

    @JsonProperty("videos")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "channelId")
    @NotNull(message = "Channel videos cannot be null")
    private List<VMVideo> videos;

    public VMChannel() {
        this.videos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public List<VMVideo> getVideos() {
        return videos;
    }

    public void setVideos(List<VMVideo> videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", videos=" + videos +
                '}';
    }

    private VMChannel(String id, String name, String description, String created_time, List<VMVideo> videos) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdTime = created_time;
        this.videos = videos;
    }

    public static VMChannel of(String id, String name, String description, String created_time, List<VMVideo> VMVideos){
        return new VMChannel(id,name,description,created_time,VMVideos);
    }
}
