package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        return getWords(text)
                .stream()
                .mapToInt(String::length)
                .sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return (int) getWords(text)
                .stream()
                .count();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return (int) getUniqueWords(text)
                .stream()
                .count();
    }

    @Override
    public List<String> getWords(String text) {
        return Stream
                .of(text.split("\\P{LD}+"))
                .collect(toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return getWords(text)
                .stream()
                .collect(toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return getWords(text)
                .stream()
                .collect(groupingBy(key -> key, summingInt((value) -> 1)));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        if (direction == Direction.ASC) {
            return getWords(text)
                    .stream()
                    .sorted(Comparator.comparingInt(String::length))
                    .collect(toList());
        }
        if (direction == Direction.DESC) {
            return getWords(text)
                    .stream()
                    .sorted(Comparator.comparingInt(String::length).reversed())
                    .collect(toList());
        }
        return new ArrayList<>(getWords(text));
    }
}
