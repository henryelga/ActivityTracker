package org.example;
import java.util.Scanner;
import java.io.*;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        String fileName = "ActivityData.csv";

        try (Scanner sc = new Scanner(new File("ActivityData.csv")))
        {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tokens = line.split(",");

                String activity_type = tokens[0];
                double duration = Double.parseDouble(tokens[1]);
                String date = tokens[2];
                double distance = Double.parseDouble(tokens[3]);
                int heart_rate = Integer.parseInt(tokens[4]);

                System.out.printf("%-20s %5.2f %10s %5.2f %3d %n", activity_type, duration, date, distance, heart_rate);
            }

        } catch (FileNotFoundException exception) {
            System.out.println("FileNotFoundException caught. The file " + fileName + "may not exist." + exception);
        }
    }
}