package ru.moeradio.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.moeradio.backend.model.Album;
import ru.moeradio.backend.model.Artist;

/**
 * ru.moeradio.backend.repository
 * Backend
 *
 * @author Tsykin V.A.
 *         tsykin.vyacheslav@otr.ru
 * @date 23.08.15
 */
public interface AlbumRepository extends MongoRepository<Album, String> {
    Album findOneByTitleAndArtist(String title, Artist artist);
}
