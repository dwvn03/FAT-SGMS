package utils.date;

import models.Session;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateUtils {
    public static List<List<Session>> sessionListToWeeklySchedule(List<Session> sessions) {
        List<List<Session>> weeklySchedule = Arrays.asList(
            Arrays.asList(null, null, null, null, null, null, null),
            Arrays.asList(null, null, null, null, null, null, null),
            Arrays.asList(null, null, null, null, null, null, null),
            Arrays.asList(null, null, null, null, null, null, null),
            Arrays.asList(null, null, null, null, null, null, null),
            Arrays.asList(null, null, null, null, null, null, null)
        );

        for (Session session : sessions) {
            LocalDate date = session.getDate().toLocalDate();
            LocalTime startTime = session.getTimeSlot().getStart_time().toLocalTime();
            LocalDateTime dateTime = LocalDateTime.of(date, startTime);
            session.setYetToStart(dateTime.isBefore(LocalDateTime.now()));

            int dayOfWeek = date.getDayOfWeek().getValue();
            int timeSlot = session.getTimeSlot().getId();

            weeklySchedule.get(timeSlot - 1).set(dayOfWeek - 1, session);
        }

        return weeklySchedule;
    }

    public static LocalDate getCurrentFirstDayOfWeek(LocalDate date) {
        LocalDate firstDayOfWeek = date.minusDays(date.getDayOfWeek().getValue() - 1);
        return firstDayOfWeek;
    }

    public static LocalDate getCurrentFirstDayOfWeek() {
        LocalDate date = LocalDate.now();
        LocalDate firstDayOfWeek = date.minusDays(date.getDayOfWeek().getValue() - 1);
        return firstDayOfWeek;
    }

    public static List<List<String>> getAllMondaysAndSundayInYear(int year) {
        LocalDate date = LocalDate.of(year, 1, 1);
        int dayOfWeek = date.getDayOfWeek().getValue();
        LocalDate firstMonday = dayOfWeek == 1 ? date : date.plusDays(8 - dayOfWeek);

        LocalDate lastDayOfYear = LocalDate.of(year, 12, 31);
        LocalDate lastMonday = lastDayOfYear.minusDays(lastDayOfYear.getDayOfWeek().getValue() - 1);
        Supplier<Stream<LocalDate>> mondaysSupplier = () -> firstMonday.datesUntil(lastMonday.plusDays(7), Period.ofWeeks(1));

        Stream<LocalDate> allMondays = mondaysSupplier.get();
        Stream<LocalDate> allSundays = mondaysSupplier.get().map((monday) -> monday.plusDays(6));

        return  Arrays.asList(dateStreamToDateStringList(allMondays), dateStreamToDateStringList(allSundays));
    }

    private static List<String> dateStreamToDateStringList(Stream<LocalDate> dateStream) {
        return dateStream.map((monday) -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
            return monday.format(formatter);
        }).collect(Collectors.toList());
    }
}
