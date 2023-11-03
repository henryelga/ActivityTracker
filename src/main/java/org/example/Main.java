package org.example;
import java.util.Scanner;
import java.io.*;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        // Read in the data from the CSV
        String fileName = "ActivityData.csv";

        System.out.printf("%-16s %-12s %-10s %-10s %-10s %n", "Activity Type", "Date", "Duration", "Distance", "BPM"); // temporary line location
        try (Scanner sc = new Scanner(new File("ActivityData.csv")))
        {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tokens = line.split(",");

                String activity_type = tokens[0];
                String date = tokens[1];
                double duration = Double.parseDouble(tokens[2]);
                double distance = Double.parseDouble(tokens[3]);
                int heart_rate = Integer.parseInt(tokens[4]);

                System.out.printf("%-16s %-12s %-10.2f %-10.2f %-10d %n", activity_type, date, duration, distance, heart_rate);
            }

        } catch (FileNotFoundException exception) {
            System.out.println("FileNotFoundException caught. The file " + fileName + "may not exist." + exception);
        }
    }
}