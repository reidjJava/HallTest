package me.reidj.halltest.factor;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;

@AllArgsConstructor
@Getter
public enum Factors {
    EMOTIONAL_AWARENESS("Эмоциональная осведомлённость", new HashSet<>(Arrays.asList(
            1, 2, 4, 17, 19, 25
    ))),
    MANAGING_YOUR_EMOTIONS("Управление своими эмоциями", new HashSet<>(Arrays.asList(
            3, 7, 8, 10, 18, 30
    ))),
    SELF_MOTIVATION("Самомотивация", new HashSet<>(Arrays.asList(
            5, 6, 13, 14, 16, 22
    ))),
    EMPATHY("Эмпатия", new HashSet<>(Arrays.asList(
            9, 11, 20, 21, 23, 28
    ))),
    PEOPLE_EMOTIONS("Распознавание эмоций других людей", new HashSet<>(Arrays.asList(
            12, 15, 24, 26, 27, 29
    )));

    private final String title;
    private final HashSet<Integer> indexes;
}
