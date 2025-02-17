package com.ubs.opsit.interviews;

import java.util.Objects;

/**
 * @author Pankaj
 */
public class BerlinClock implements TimeConverter {
    @Override
    public String convertTime(String time) {
        if (time == null || time.isEmpty()) {
            throw new IllegalArgumentException("Invalid time format");
        }

        // Handle invalid time format
        String[] values = time.split(":");
        if (values.length != 3) {
            throw new IllegalArgumentException("Invalid time format");
        }

        try {
            int hours = Integer.parseInt(values[0]);
            int minutes = Integer.parseInt(values[1]);
            int seconds = Integer.parseInt(values[2]);

            // Validate hours, minutes, and seconds range
            if (hours < 0 || hours > 24 || minutes < 0 || minutes > 59 || seconds < 0 || seconds > 59) {
                throw new IllegalArgumentException("Invalid time format");
            }

            // If hours == 24, check if minutes and seconds are 00
            if (hours == 24 && (minutes != 0 || seconds != 0)) {
                throw new IllegalArgumentException("Invalid time format");
            }

            // Construct the Berlin Clock string
            return getLampOnOff(seconds) + " " + getHours(hours) + " " + getMinutes(minutes);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid time format");
        }
    }


    /**
     * Every 2 seconds lamp 1st row blinks on/off
     * @param seconds
     * @return
     */
    protected String getLampOnOff(int seconds) {
        return seconds % 2 == 0 ? LampSymbol.Y.name() : LampSymbol.O.name();
    }

    protected String getHours(int hours) {
        int numberTopHourLamps = hours / 5;
        int numberBottomHourLamps = hours % 5;

        return getLampRow(4, numberTopHourLamps, LampSymbol.R) + " " + getLampRow(4, numberBottomHourLamps, LampSymbol.R);
    }

    protected String getMinutes(int minutes) {
        int numberTopMinutesLamps = minutes / 5;
        int numberBottomMinutesLamps = minutes % 5;

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 11; i++) {
            sb.append(i <= numberTopMinutesLamps ? getMinuteLampColour(i) : LampSymbol.O.name());
        }

        sb.append(" ");

        sb.append(getLampRow(4, numberBottomMinutesLamps, LampSymbol.Y));

        return sb.toString();
    }

    private String getLampRow(int totalNumberLamps, int numberLampsOn, LampSymbol lampSymbol) {
        StringBuilder sb = new StringBuilder(totalNumberLamps);
        for (int i = 0; i < totalNumberLamps; i++) {
            sb.append(i < numberLampsOn ? lampSymbol : LampSymbol.O.name());
        }
        return sb.toString();
    }

    private String getMinuteLampColour(int index) {
        return index % 3 == 0 ? LampSymbol.R.name() : LampSymbol.Y.name();
    }
}
