package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.functions.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SleepTrackerApp {
    private static List<SleepAnalysisResult<?>> functions = new ArrayList<>(List.of(
            new AmountOfSessions(),
            new AverageSessionDuration(),
            new MaximumSessionDuration(),
            new MinimumSessionDuration(),
            new SessionsWithPoorSleepQuality(),
            new SleeplessNights(),
            new UserClassification()));
    private static List<SleepingSession> sessions;

    public static void main(String[] args) {
        String filePath = args[0];

        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath));
             BufferedReader fileDictionaryReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            sessions = fileDictionaryReader.lines()
                    .map(SleepingSession::new)
                    .collect(Collectors.toList());

        } catch (java.nio.file.NoSuchFileException e) {
            System.out.println("Файл не найден по пути: " + filePath);
        } catch (IOException e) {
            System.out.println("Произошла ошибка во время чтения файла");
        }

        functions.forEach(function ->
                        System.out.println(function.getDescription() + function.apply(sessions)));
    }
}