package ru.moeradio.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.moeradio.backend.model.Track;

/**
 * ru.moeradio.backend.repository
 * Backend
 *
 * @author Tsykin V.A.
 *         tsykin.vyacheslav@otr.ru
 * @date 23.08.15
 */
public interface TrackRepository extends MongoRepository<Track, String> {
}
