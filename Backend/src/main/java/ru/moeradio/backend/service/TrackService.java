package ru.moeradio.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.moeradio.backend.model.Album;
import ru.moeradio.backend.model.Track;
import ru.moeradio.backend.repository.TrackRepository;

import java.util.List;

/**
 * Service-layer class to work with {@link Track} model
 * ru.moeradio.backend.service
 * Backend
 *
 * @author Tsykin V.A.
 *         tsykin.vyacheslav@otr.ru
 * @date 23.08.15
 * @see Track
 * @see TrackRepository
 */
@Service
public class TrackService {
    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private AlbumService albumService;

    /**
     * Merge into database input {@link Track} object. If there is one with same title and {@link Album}
     * this new object will replace old. Else it {@link #create} ones new.
     * If it has non-merged {@link Track#album} property - album will merged too.
     *
     * @param track - track to merge into DB.
     * @return merged track object
     * @see Track
     * @see Album
     * @see #create(Track)
     */
    public Track merge(Track track) {
        Album album = track.getAlbum();
        track.setAlbum(albumService.merge(album));

        Track foundedTrack = this.trackRepository.findOneByTitleAndAlbum(track.getTitle(), track.getAlbum());
        if (foundedTrack == null) {
            return this.create(track);
        } else {
            track.setId(foundedTrack.getId());
        }

        return this.trackRepository.save(track);
    }

    /**
     * Return all saved into DB {@link Track} objects
     *
     * @return List of objects
     * @see List
     * @see Track
     */
    public List<Track> getAll() {
        return trackRepository.findAll();
    }

    /**
     * Insert into database new {@link Track} object.
     * Create reference to {@link Album} object and add to list of tracks to albums {@link AlbumService#addTrack(Track)}
     * !IMPORTANT! MUST BE non-existing object without _id
     * If you not sure, please use {@link #merge(Track)} method
     *
     * @param track - {@link Track} object to create in database
     * @return created object
     * @see Track
     * @see #merge(Track)
     * @see AlbumService#addTrack(Track)
     */
    public Track create(Track track) {
        track = trackRepository.insert(track);

        Album album = albumService.addTrack(track);
        track.setAlbum(album);

        return this.merge(track);
    }

}
