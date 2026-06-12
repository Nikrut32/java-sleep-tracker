package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.functions.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SleepTrackerApp {
    private List<Function<List<SleepingSession>, ?>> functions = new ArrayList<>(List.of(
            new AmountOfSessions(),
            new AverageSessionDuration(),
            new MaximumSessionDuration(),
            new MinimumSessionDuration(),
            new SessionsWithPoorSleepQuality(),
            new SleeplessNights(),
            new UserClassification()));
    private static List<SleepingSession> sessions;

    public static void main(String[] args) {
        try (InputStream inputStream = SleepTrackerApp.class.getResourceAsStream("/sleep_log.txt");
             BufferedReader fileDictionaryReade = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            sessions = fileDictionaryReade.lines()
                    .map(SleepingSession::new)
                    .collect(Collectors.toList());

        } catch (NullPointerException e) {
            System.out.println("Файл sleep_log.txt не найден в папке resources");
        } catch (IOException e) {
            System.out.println("Произошла ошибка во время чтения файла");
        }

        AmountOfSessions amountOfSessions = new AmountOfSessions();
        AverageSessionDuration averageSessionDuration = new AverageSessionDuration();
        MaximumSessionDuration maximumSessionDuration = new MaximumSessionDuration();
        MinimumSessionDuration minimumSessionDuration = new MinimumSessionDuration();
        System.out.println(amountOfSessions.apply(sessions) + "\n"
                + averageSessionDuration.apply(sessions) + "\n"
                + maximumSessionDuration.apply(sessions) + "\n"
                + minimumSessionDuration.apply(sessions));
    }
}