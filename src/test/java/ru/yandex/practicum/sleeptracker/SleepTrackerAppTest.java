package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class SleepTrackerAppTest {
    private static List<SleepingSession> sessions;

    @BeforeAll
    static void loadSessionsTest() {
        List<String> sessionsString = new ArrayList<>(List.of("01.10.25 23:15;02.10.25 07:30;GOOD",
                "02.10.25 23:50;03.10.25 06:40;NORMAL",
                "03.10.25 14:10;03.10.25 15:00;NORMAL",
                "03.10.25 23:40;04.10.25 08:00;BAD",
                "08.10.25 23:50;09.10.25 07:10;BAD"));
        sessions = sessionsString.stream()
                .map(SleepingSession::new)
                .collect(Collectors.toList());
    }

    @Test
    void amountOfSessionsTest() {
        AmountOfSessions amountOfSessions = new AmountOfSessions();
        Assertions.assertEquals(5, amountOfSessions.apply(sessions));
    }

    @Test
    void averageSessionDurationTest() {
        AverageSessionDuration averageSessionDuration = new AverageSessionDuration();
        Assertions.assertEquals(379, averageSessionDuration.apply(sessions));
    }

    @Test
    void maximumSessionDurationTest() {
        MaximumSessionDuration maximumSessionDuration = new MaximumSessionDuration();
        Assertions.assertEquals(500, maximumSessionDuration.apply(sessions));
    }

    @Test
    void minimumSessionDurationTest() {
        MinimumSessionDuration minimumSessionDuration = new MinimumSessionDuration();
        Assertions.assertEquals(50, minimumSessionDuration.apply(sessions));
    }

    @Test
    void sessionsWithPoorSleepQualityTest() {
        SessionsWithPoorSleepQuality sessionsWithPoorSleepQuality = new SessionsWithPoorSleepQuality();
        Assertions.assertEquals(2, sessionsWithPoorSleepQuality.apply(sessions));
    }

    @Test
    void sleeplessNightsOutsideThePeriodTest() {
        List<String> sessionsString = new ArrayList<>(List.of("02.10.25 18:50;02.10.25 21:30;NORMAL",
                "03.10.25 06:30;03.10.25 11:45;NORMAL"));
        List<SleepingSession> sessionsTest = sessionsString.stream()
                .map(SleepingSession::new)
                .collect(Collectors.toList());

        SleeplessNights sleeplessNights = new SleeplessNights();
        Assertions.assertEquals(2, sleeplessNights.apply(sessionsTest));
    }

    @Test
    void sleeplessNightsWithinThePeriodTest() {
        List<String> sessionsString = new ArrayList<>(List.of("06.10.25 00:30;06.10.25 05:50;GOOD"));
        List<SleepingSession> sessionsTest = sessionsString.stream()
                .map(SleepingSession::new)
                .collect(Collectors.toList());

        SleeplessNights sleeplessNights = new SleeplessNights();
        Assertions.assertEquals(0, sleeplessNights.apply(sessionsTest));
    }

    @Test
    void sleeplessNightsBorderBeforeAndAfterTest() {
        List<String> sessionsString = new ArrayList<>(List.of("01.10.25 22:10;02.10.25 00:00;NORMAL",
                "02.10.25 06:00;02.10.25 09:45;GOOD"));
        List<SleepingSession> sessionsTest = sessionsString.stream()
                .map(SleepingSession::new)
                .collect(Collectors.toList());

        SleeplessNights sleeplessNights = new SleeplessNights();
        Assertions.assertEquals(2, sleeplessNights.apply(sessionsTest));
    }

    @Test
    void sleeplessNightsInsideTheBordersTest() {
        List<String> sessionsString = new ArrayList<>(List.of("04.10.25 00:00;04.10.25 06:00;NORMAL"));
        List<SleepingSession> sessionsTest = sessionsString.stream()
                .map(SleepingSession::new)
                .collect(Collectors.toList());

        SleeplessNights sleeplessNights = new SleeplessNights();
        Assertions.assertEquals(0, sleeplessNights.apply(sessionsTest));
    }

    @Test
    void userClassificationOwlTest() {
        List<String> sessionsString = new ArrayList<>(List.of("04.10.25 23:10;05.10.25 10:10;NORMAL",
                "05.10.25 21:56;06.10.25 06:15;NORMAL",
                "07.10.25 23:45;08.10.25 09:30;GOOD",
                "11.10.25 23:10;12.10.25 07:00;BAD"));
        List<SleepingSession> sessionsTest = sessionsString.stream()
                .map(SleepingSession::new)
                .collect(Collectors.toList());

        UserClassification userClassification = new UserClassification();
        Assertions.assertEquals("Сова", userClassification.apply(sessionsTest));
    }

    @Test
    void userClassificationLarkTest() {
        List<String> sessionsString = new ArrayList<>(List.of("04.10.25 23:10;05.10.25 10:10;NORMAL",
                "05.10.25 21:56;06.10.25 06:15;NORMAL",
                "07.10.25 20:14;08.10.25 05:32;GOOD",
                "11.10.25 23:10;12.10.25 07:00;BAD"));
        List<SleepingSession> sessionsTest = sessionsString.stream()
                .map(SleepingSession::new)
                .collect(Collectors.toList());

        UserClassification userClassification = new UserClassification();
        Assertions.assertEquals("Жаворонок", userClassification.apply(sessionsTest));
    }

    @Test
    void userClassificationPigeonTest() {
        List<String> sessionsString = new ArrayList<>(List.of("01.10.25 23:15;02.10.25 07:30;GOOD",
                "04.10.25 23:10;05.10.25 10:10;NORMAL",
                "07.10.25 20:14;08.10.25 05:32;GOOD",
                "11.10.25 23:10;12.10.25 07:00;BAD"));
        List<SleepingSession> sessionsTest = sessionsString.stream()
                .map(SleepingSession::new)
                .collect(Collectors.toList());

        UserClassification userClassification = new UserClassification();
        Assertions.assertEquals("Голубь", userClassification.apply(sessionsTest));
    }

    @Test
    void userClassificationPigeonIfEqualOwlAndLarkTest() {
        List<String> sessionsString = new ArrayList<>(List.of("01.10.25 23:15;02.10.25 11:15;GOOD",
                "04.10.25 23:10;05.10.25 10:10;NORMAL",
                "07.10.25 19:45;08.10.25 06:11;GOOD",
                "11.10.25 20:54;12.10.25 06:47;BAD"));
        List<SleepingSession> sessionsTest = sessionsString.stream()
                .map(SleepingSession::new)
                .collect(Collectors.toList());

        UserClassification userClassification = new UserClassification();
        Assertions.assertEquals("Голубь", userClassification.apply(sessionsTest));
    }
}