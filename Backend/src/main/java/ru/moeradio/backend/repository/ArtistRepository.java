package ru.moeradio.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.moeradio.backend.model.Artist;

/**
 * ru.moeradio.backend.repository
 * Backend
 *
 * @author Tsykin V.A.
 *         tsykin.vyacheslav@otr.ru
 * @date 20.08.15
 */

public interface ArtistRepository extends MongoRepository<Artist, String> {
    Artist findByTitle(String title);
}
