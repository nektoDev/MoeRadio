package ru.moeradio.backend.model;

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
public class Track {

    private Long id;
    private String name;
    private Album album;
    private int length;
    private Path path;

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", path=" + path +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Track track = (Track) o;

        if (length != track.length) return false;
        if (id != null ? !id.equals(track.id) : track.id != null) return false;
        if (name != null ? !name.equals(track.name) : track.name != null) return false;
        if (album != null ? !album.equals(track.album) : track.album != null) return false;
        return !(path != null ? !path.equals(track.path) : track.path != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (album != null ? album.hashCode() : 0);
        result = 31 * result + length;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
