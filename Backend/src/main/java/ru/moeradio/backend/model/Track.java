package ru.moeradio.backend.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.nio.file.Path;

/**
 * ru.moeradio.backend.model
 * Backend
 * Model class for track
 *
 * @author Tsykin V.A.
 *         tsykin.vyacheslav@otr.ru
 * @date 19.08.15
 */
@Document
public class Track {
    @Id
    private String id;

    private String trackId;
    private String title;
    private Album album;
    private int length;
    private Path path;

    @Override
    public String toString() {
        return "Track{" +
                "id='" + id + '\'' +
                ", trackId='" + trackId + '\'' +
                ", title='" + title + '\'' +
                ", album=" + album +
                ", length=" + length +
                ", path=" + path +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Track track = (Track) o;

        return new EqualsBuilder()
                .append(length, track.length)
                .append(id, track.id)
                .append(trackId, track.trackId)
                .append(title, track.title)
                .append(album, track.album)
                .append(path, track.path)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(trackId)
                .append(title)
                .append(album)
                .append(length)
                .append(path)
                .toHashCode();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }
}
