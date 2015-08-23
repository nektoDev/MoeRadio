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
 * Service-layer class to work with {@link Album} model class via {@link AlbumRepository}
 * ru.moeradio.backend.service
 * Backend
 *
 * @author Tsykin V.A.
 *         tsykin.vyacheslav@otr.ru
 * @date 23.08.15
 * @see Album
 * @see AlbumRepository
 */
@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private TrackService trackService;

    /**
     * Merge into database input {@link Album} object. If there is one with same title and {@link Artist}
     * this new object will replace old. Else it {@link #create} ones new.
     * If it has non-merged {@link Album#artist} property - artist will merged too.
     *
     * @param album - {@link Album} object to merge into DB
     * @return merged {@link Album} object
     * @see Album
     * @see Artist
     * @see #create(Album)
     */
    public Album merge(Album album) {

        Artist artist = artistService.merge(album.getArtist());
        album.setArtist(artist);

        Album foundedAlbum = this.albumRepository.findOneByTitleAndArtist(album.getTitle(), artist);

        if (foundedAlbum != null) {
            album.setId(foundedAlbum.getId());
        } else {
            return create(album);
        }

        return albumRepository.save(album);
    }

    /**
     * Add {@link Track} reference to {@link Album} object. Get album from track into input params.
     * This method will merge both objects, {@link Album} and {@link Track} before add reference,
     * in case them are in non-saved state
     *
     * @param track {@link Track} object to add into {@link Track#album}
     * @return {@link Album} with added to {@link Album#tracks} {@link Track} object
     * @see Album
     * @see Track
     * @see #merge(Album)
     * @see TrackService#merge(Track)
     */
    public Album addTrack(Track track) {
        track = trackService.merge(track);
        Album album = this.merge(track.getAlbum());

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

    /**
     * Insert into database new {@link Album} object.
     * Create reference to {@link Album} object and add to list of albums to {@link Artist} {@link ArtistService#addAlbum(Album)}
     * !IMPORTANT! MUST BE non-existing object without _id
     * If you not sure, please use {@link #merge(Album)} method
     *
     * @param album - {@link Album} object to create in database
     * @return created object
     * @see Album
     * @see #merge(Album)
     * @see ArtistService#addAlbum(Album)
     */
    public Album create(Album album) {
        album = this.albumRepository.insert(album);

        Artist artist = this.artistService.addAlbum(album);
        album.setArtist(artist);

        return this.albumRepository.save(album);
    }

}
