package com.ubs.opsit.interviews;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Pankaj
 */
public class BerlinClock implements TimeConverter {
    @Override
    public String convertTime(String time) {
        if (Objects.isNull(time)) {
            throw new IllegalArgumentException("Invalid time format");
        }

        LocalTime localTime;
        try {
            localTime = LocalTime.parse(time);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Invalid time format");
        }

        return getLampOnOff(localTime.getSecond()) + "\n"
                + getHours(localTime.getHour()) + "\n"
                + getMinutes(localTime.getMinute());
    }

    /**
     * Every 2 seconds lamp 1st row blinks on/off
     * @param seconds
     * @return
     */
    protected String getLampOnOff(int seconds) {
        // Use the LampSymbol enum to get the respective values
        return (seconds % 2 == 0) ? LampSymbol.YELLOW.getLampValue() : LampSymbol.OFF.getLampValue();
    }

    protected String getHours(int hours) {
        int numberTopHourLamps = hours / 5;
        int numberBottomHourLamps = hours % 5;

        // Use the LampSymbol enum for RED lamps
        String topHourRow = getLampRowStream(4, numberTopHourLamps, LampSymbol.RED).collect(Collectors.joining());
        String bottomHourRow = getLampRowStream(4, numberBottomHourLamps, LampSymbol.RED).collect(Collectors.joining());

        return topHourRow + "\n" + bottomHourRow;
    }

    protected String getMinutes(int minutes) {
        int numberTopMinutesLamps = minutes / 5;
        int numberBottomMinutesLamps = minutes % 5;

        // Using Stream API to generate the first row of lamps (top minutes)
        String topMinutesRow = IntStream.rangeClosed(1, 11)
                .mapToObj(i -> i <= numberTopMinutesLamps ? getMinuteLampColour(i) : LampSymbol.OFF.getLampValue())
                .collect(Collectors.joining());

        // Using Stream API to generate the second row of lamps (bottom minutes)
        String bottomMinutesRow = getLampRowStream(4, numberBottomMinutesLamps, LampSymbol.YELLOW)
                .collect(Collectors.joining());

        return topMinutesRow + "\n" + bottomMinutesRow;
    }

    private Stream<String> getLampRowStream(int totalNumberLamps, int numberLampsOn, LampSymbol lampSymbol) {
        return IntStream.range(0, totalNumberLamps)
                .mapToObj(i -> i < numberLampsOn ? lampSymbol.getLampValue() : LampSymbol.OFF.getLampValue());
    }

    private String getMinuteLampColour(int index) {
        // Use the LampSymbol enum to determine the color of the lamp (Red for multiples of 3)
        return (index % 3 == 0) ? LampSymbol.RED.getLampValue() : LampSymbol.YELLOW.getLampValue();
    }
}
