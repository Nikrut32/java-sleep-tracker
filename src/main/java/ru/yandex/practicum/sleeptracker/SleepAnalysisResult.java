package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.functions.*;

import java.util.List;
import java.util.function.Function;

public class SleepAnalysisResult {
    public static String print(Function<List<SleepingSession>, ?> function) {
        return switch (function.getClass().getSimpleName()) {
            case "AmountOfSessions" -> "Количество сессий: ";
            case "AverageSessionDuration" -> "Средняя продолжительность сессии (в минутах): ";
            case "MaximumSessionDuration" -> "Максимальная продолжительность сессии (в минутах: ";
            case "MinimumSessionDuration" -> "Минимальная продолжительность сессии (в минутах): ";
            case "SessionsWithPoorSleepQuality" -> "Количество сессий с плохим качеством сна: ";
            case "SleeplessNights" -> "Количество бессонных ночей: ";
            case "UserClassification" -> "Классификация пользователя: ";
            default -> "Передан неизвестный класс";
        };
    }
}
