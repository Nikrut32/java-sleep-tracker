package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.functions.*;

import java.util.List;
import java.util.function.Function;


public interface SleepAnalysisResult<T> extends Function<List<SleepingSession>, T> {
    String getDescription();
}
