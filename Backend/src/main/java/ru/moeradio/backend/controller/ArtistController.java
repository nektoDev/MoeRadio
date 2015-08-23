package ru.moeradio.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.moeradio.backend.model.Artist;
import ru.moeradio.backend.service.ArtistService;

import java.util.List;

/**
 * RESTful service for {@link Artist}
 * Available at "/artist"
 * ru.moeradio.backend.controller
 * Backend
 *
 * @author Tsykin V.A.
 *         tsykin.vyacheslav@otr.ru
 * @date 19.08.15
 * @see Artist
 * @see ArtistService
 */
@RequestMapping("/artist")
@RestController
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    /**
     * Returns all {@link Artist} objects saved in DB
     * Usecase: http://{host}:{port}/api/artist
     *
     * @return list of Artist objects
     * @see Artist
     * @see ArtistService#findAll()
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Artist> list() {
        return artistService.findAll();
    }

    /**
     * Find one {@link Artist} object in DB, by it _id property
     * Usecase: http://{host}:{port}/api/artist/{_id}
     *
     * @param id PathVariable _id property of {@link Artist} object
     * @return founded {@link Artist} object
     * @see Artist
     * @see PathVariable
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Artist get(@PathVariable(value = "id") Long id) {
        return artistService.findOne(id.toString());
    }
}
