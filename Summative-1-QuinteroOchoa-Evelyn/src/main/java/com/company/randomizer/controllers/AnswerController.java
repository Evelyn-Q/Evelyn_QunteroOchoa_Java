package com.company.randomizer.controllers;

import com.company.randomizer.models.Answer;
import com.company.randomizer.models.Quote;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class AnswerController {

    private List<String> answerList;
    private static int idCounter = 1;

    public AnswerController() {
        answerList = new ArrayList<String>();

        answerList.add("Yes!");
        answerList.add("No!");
        answerList.add("Unfortunately, not.");
        answerList.add("Three, take it or leave it");
        answerList.add("It is unclear");
        answerList.add("Negative, did not pass the vibe check");
        answerList.add("Maybe, Perhaps, Sometimes");
    }

    @RequestMapping(value = "/magic", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Answer answerQuestion(@RequestBody String question) {

        Answer answer = new Answer();
        answer.setId(idCounter++);
        answer.setQuestion(question);
        Collections.shuffle(answerList);
        answer.setAnswer(answerList.get(0));

        return answer;
    }
}
