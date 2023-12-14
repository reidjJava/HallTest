package me.reidj.halltest.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.reidj.halltest.question.Question;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class User {
    private final String name;
    private final String surname;
    private final String patronymic;
    private final String classroom;
    private final Map<Question, Integer> questions;
    private int seconds;
    private int minutes;
    private int hour;
}
