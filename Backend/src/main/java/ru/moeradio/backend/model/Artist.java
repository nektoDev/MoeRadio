package ru.moeradio.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * ru.moeradio.backend.model
 * Backend
 * Model for Artists.
 *
 * @author Tsykin V.A.
 *         tsykin.vyacheslav@otr.ru
 * @date 19.08.15
 */

@Document(collection = "artist")
public class Artist {
    @Id
    private String id;

    private Long artistId;
    private String title;

    @Override
    public String toString() {
        return "Artist{" +
                "id='" + id + '\'' +
                ", artistId=" + artistId +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        if (id != null ? !id.equals(artist.id) : artist.id != null) return false;
        if (artistId != null ? !artistId.equals(artist.artistId) : artist.artistId != null) return false;
        return !(title != null ? !title.equals(artist.title) : artist.title != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (artistId != null ? artistId.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
