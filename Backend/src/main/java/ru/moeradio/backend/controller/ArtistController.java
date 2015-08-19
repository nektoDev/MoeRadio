package ru.moeradio.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.moeradio.backend.model.Artist;
import ru.moeradio.backend.repository.ArtistRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ru.moeradio.backend.controller
 * Backend
 *
 * @author Tsykin V.A.
 *         tsykin.vyacheslav@otr.ru
 * @date 19.08.15
 */
@RequestMapping("/artist")
@RestController
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    private AtomicLong counter = new AtomicLong(0l);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Artist> list() {
        return artistRepository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Artist get(@PathVariable(value = "id") Long id) {
        return artistRepository.findOne(id.toString());
    }


}
