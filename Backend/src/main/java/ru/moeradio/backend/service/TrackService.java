package ru.moeradio.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.moeradio.backend.model.Album;
import ru.moeradio.backend.model.Track;
import ru.moeradio.backend.repository.TrackRepository;

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
public class TrackService {
    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private ArtistService artistService;
    @Autowired
    private AlbumService albumService;

    public Track merge(Track track) {
        Track foundedTrack = this.trackRepository.findOneByTitleAndAlbum(track.getTitle(), track.getAlbum());
        if (foundedTrack == null) {
            return this.create(track);
        } else {
            track.setId(foundedTrack.getId());
        }

        return this.trackRepository.save(track);
    }

    public List<Track> getAll() {
        return trackRepository.findAll();
    }

    public Track create(Track track) {
        track = trackRepository.insert(track);

        Album album = albumService.addTrack(track);
        track.setAlbum(album);

        return this.merge(track);
    }

}
