package com.company.randomizer.controllers;

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
public class QuoteController {

    private List<Quote> quoteList;
    private static int idCounter = 1;

    public QuoteController() {
        quoteList = new ArrayList<>();

        quoteList.add(new Quote("The ability to speak does not make you intelligent", "Qui-Gon Jinn", idCounter++));
        quoteList.add(new Quote("All that is gold does not glitter, Not all those who wander are lost", "J. R. R. Tolkien", idCounter++));
        quoteList.add(new Quote("We know what we are, but not yet what we may be", "Shakespeare", idCounter++));
        quoteList.add(new Quote("I DECLARE BANKRUPTCY", "Micheal Scott", idCounter++));
        quoteList.add(new Quote("But we don't do things because they are easy hm? We do them because they are profitable.", "Tom Nook", idCounter++));
        quoteList.add(new Quote("Build a man a fire, and he'll be warm for a day. Set a man on fire, and he'll be warm for the rest of his life.", "Terry Pratchett", idCounter++));
        quoteList.add(new Quote("Go bears!", "Oski", idCounter++));
        quoteList.add(new Quote("That's it, dishonor! Dishonor on your whole family! Dishonor on you, dishonor on your cow!", "Mushu, Mulan", idCounter++));
        quoteList.add(new Quote("Dude you're embarrassing me in front of the wizards", "Tony Stark", idCounter++));
        quoteList.add(new Quote("There's always a bigger fish", "Qui-Gon Jinn", idCounter++));
    }


        @RequestMapping(value = "/quote", method = RequestMethod.GET)
        @ResponseStatus(value = HttpStatus.OK)
        public Quote getQuote() {
            Collections.shuffle(quoteList);
            return quoteList.get(0);
        }
    }

