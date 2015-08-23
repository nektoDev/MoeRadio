package ru.moeradio.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.moeradio.backend.model.Album;
import ru.moeradio.backend.model.Artist;
import ru.moeradio.backend.model.Track;
import ru.moeradio.backend.repository.AlbumRepository;

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
public class AlbumService {

    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    ArtistService artistService;

    public Album merge(Album album) {
        Album foundedAlbum = this.albumRepository.findOneByTitleAndArtist(album.getTitle(), album.getArtist());

        if (foundedAlbum != null) {
            album.setId(foundedAlbum.getId());
        } else {
            return create(album);
        }

        return albumRepository.save(album);
    }

    public Album addTrack(Track track) {
        Album album = track.getAlbum();
        List<Track> tracks = album.getTracks();
        if (tracks != null) {

            tracks.add(track);
        } else {
            tracks = new ArrayList<>();
            tracks.add(track);
            album.setTracks(tracks);
        }
        return this.merge(album);
    }

    public Album create(Album album) {
        album = this.albumRepository.insert(album);

        Artist artist = this.artistService.addAlbum(album);
        album.setArtist(artist);

        return this.albumRepository.save(album);
    }

}
