package org.example;
import java.util.Scanner;
import java.io.*;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("=========================================");
        System.out.println("=            Mila & Elga's              =");
        System.out.println("=           Activity Tracker            =");
        System.out.println("=========================================");
        System.out.println();
        System.out.println("Select which file to view the activity of");
        System.out.println("1. activity_data_10.csv");
        System.out.println("2. activity_data_50.csv");
        System.out.println("3. activity_data_100.csv");
        System.out.println("4. activity_data_1000.csv");
        System.out.print("Enter option: ");

        String fileName;
        int selectFile = kb.nextInt();
        if(selectFile == 1)
            fileName = "activity_data_10.csv";
        else if(selectFile == 2)
            fileName = "activity_data_50.csv";
        else if(selectFile == 3)
            fileName = "activity_data_100.csv";
        else
            fileName = "activity_data_1000.csv";

        System.out.printf("%-16s %-12s %-10s %-10s %-10s %n", "Activity Type", "Date", "Duration", "Distance", "BPM"); // temporary line location
        File f = new File(fileName);
        try (Scanner sc = new Scanner(f))
        {
            // ignoring first line in csv (header)
            if(sc.hasNextLine())
                    sc.nextLine();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tokens = line.split(",");

                String activity_type = tokens[0];
                String date = tokens[1];
                double duration = Double.parseDouble(tokens[2]);
                double distance = Double.parseDouble(tokens[3]);
                int heart_rate = (int)Double.parseDouble(tokens[4]);

                System.out.printf("%-16s %-12s %-10.2f %-10.2f %-10d %n", activity_type, date, duration, distance, heart_rate);
            }

        } catch (FileNotFoundException exception) {
            System.out.println("FileNotFoundException caught. The file " + fileName + "may not exist." + exception);
        }
    }
}