package com.company.randomizer.controllers;

import com.company.randomizer.models.Definition;
import com.company.randomizer.models.Quote;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class DefinitionController {

    private List<Definition> defList;
    private static int idCounter = 1;

    public DefinitionController() {
        defList = new ArrayList<>();

        defList.add(new Definition("Rat", "Any of several long-tailed rodents of the family Muridae, of the genus Rattus and related genera, distinguished from the mouse by being larger.", idCounter++));
        defList.add(new Definition("Sonder", "The realization that each random passerby is living a life as vivid and complex as your own.", idCounter++));
        defList.add(new Definition("Girlboss", "A woman whose success is defined in opposition to the masculine business world in which she swims upstream.", idCounter++));
        defList.add(new Definition("Gaslight", "A type of lamp in which an incandescent mantle is heated by a jet of burning gas.", idCounter++));
        defList.add(new Definition("Gatekeep", "When someone takes it upon themselves to decide who does or does not have access or rights to a community or identity.", idCounter++));
        defList.add(new Definition("Defenestration", "The action of throwing someone out of a window..", idCounter++));
        defList.add(new Definition("Ultracrepidarian", "Someone who has no special knowledge of a subject but who expresses an opinion on it.", idCounter++));
        defList.add(new Definition("Syzygy", "The nearly straight-line configuration of three celestial bodies (such as the sun, moon, and earth during a solar or lunar eclipse) in a gravitational system.", idCounter++));
        defList.add(new Definition("Nibbling", "The child of one's sibling or sibling-in-law; one's nephew or niece.", idCounter++));
        defList.add(new Definition("Clowder", "A group of cats.", idCounter++));


    }

    @RequestMapping(value = "/word", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Definition getWord() {
        Collections.shuffle(defList);
        return defList.get(0);
    }
}
