package ru.moeradio.backend.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * ru.moeradio.backend.model
 * Backend
 * Model class for album
 *
 * @author Tsykin V.A.
 *         tsykin.vyacheslav@otr.ru
 * @date 19.08.15
 */
@Document
public class Album {
    @Id
    private String id;

    private Long albumId;
    private String title;
    private Artist artist;

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", albumId=" + albumId +
                ", title='" + title + '\'' +
                ", artist=" + artist +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        return new EqualsBuilder()
                .append(id, album.id)
                .append(albumId, album.albumId)
                .append(title, album.title)
                .append(artist, album.artist)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(albumId)
                .append(title)
                .append(artist)
                .toHashCode();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }
}
