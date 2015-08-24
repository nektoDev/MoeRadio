package ru.moeradio.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.moeradio.backend.model.Album;
import ru.moeradio.backend.model.Artist;
import ru.moeradio.backend.repository.ArtistRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Service-layer class to work with {@link Artist} model via {@link ArtistRepository}
 * ru.moeradio.backend.service
 * Backend
 *
 * @author Tsykin V.A.
 *         tsykin.vyacheslav@otr.ru
 * @date 23.08.15
 * @see Artist
 * @see ArtistRepository
 */
@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    /**
     * Merge into database input {@link Artist} object. If there is one with same title
     * this new object will replace old. Else it {@link #create} ones new.
     *
     * @param artist - artist to merge into DB.
     * @return merged artist object
     * @see Artist
     * @see #create(Artist)
     */
    public Artist merge(Artist artist) {

        Artist foundedArtist = artistRepository.findByTitle(artist.getTitle());

        if (foundedArtist != null) {
            artist.setId(foundedArtist.getId());
        } else {
            return this.create(artist);
        }

        return artistRepository.save(artist);
    }

    /**
     * Add {@link Album} to {@link Artist} object.
     * This method will @{link #merge(Artist)} {@link Artist}
     *
     * @param album {@link Album} object to add into {@link Artist#albums}
     * @param artist {@link Artist} to add album
     * @return {@link Artist} object with added {@link Artist#albums}
     * @see Artist
     * @see Album
     * @see #merge(Artist)
     */
    public Artist addAlbum(Artist artist, Album album) {

        if (artist.getAlbums() == null) {
            artist.setAlbums(new ArrayList<>());
        }

        artist.getAlbums().add(album);

        return this.merge(artist);
    }

    /**
     * Save new {@link Artist} object into database.
     * !IMPORTANT! MUST BE non-existing object without _id
     * If you not sure, please use {@link #merge(Artist)} method
     *
     * @param artist - {@link Artist} object to insert into Db
     * @return created object
     * @see Artist
     * @see #merge(Artist)
     */
    public Artist create(Artist artist) {
        artist = artistRepository.insert(artist);
        return artist;
    }

    /**
     * Return all saved into DB {@link Artist} objects
     *
     * @return List of objects
     * @see List
     * @see Artist
     */
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    /**
     * Return saved in database {@link Artist} object by it _id.
     *
     * @param id _id property of saved object
     * @return founded {@link Artist} object or null {@link Artist} with this id not exists
     * @see Artist
     */
    public Artist findOne(String id) {
        return artistRepository.findOne(id);
    }
}
