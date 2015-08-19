package ru.moeradio.backend.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.moeradio.backend.model.Artist;

import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Artist> list() {
        List<Artist> result = new ArrayList<>();

        return result;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Artist get(@PathVariable(value = "id") Long id) {
        return new Artist();
    }


}
