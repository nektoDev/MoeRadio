package ru.moeradio.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.moeradio.backend.model.Album;
import ru.moeradio.backend.model.Artist;
import ru.moeradio.backend.repository.ArtistRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * ru.moeradio.backend.service
 * Backend
 *
 * @author Tsykin V.A.
 *         tsykin.vyacheslav@otr.ru
 * @date 23.08.15
 */
@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    public Artist merge(Artist artist) {

        Artist foundedArtist = artistRepository.findByTitle(artist.getTitle());

        if (foundedArtist != null) {
            artist.setId(foundedArtist.getId());
        } else {
            return this.create(artist);
        }

        return artistRepository.save(artist);
    }

    public Artist addAlbum(Album album) {
        Artist artist = album.getArtist();

        if (artist.getAlbums() == null) {
            artist.setAlbums(new ArrayList<>());
            artist.getAlbums().add(album);
        } else {
            artist.getAlbums().add(album);
        }

        return this.merge(artist);
    }

    public Artist create(Artist artist) {
        artist = artistRepository.insert(artist);
        return artist;
    }

    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    public Artist findOne(String id) {
        return artistRepository.findOne(id);
    }
}
