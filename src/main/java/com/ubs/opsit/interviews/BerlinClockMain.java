package com.ubs.opsit.interviews;

public class BerlinClockMain {

    public static void main(String[] args) {
        BerlinClock clock = new BerlinClock();
        //clock.convertTime("");
        //clock.convertTime("23.59.59");
        String result = clock.convertTime("23:59:59");
        System.out.println(result);

    }
}
