package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

import java.io.FileNotFoundException;

public class Main
{
    public static void main(String[] args)
    {
        // Read in the data from the CSV
        String fileName = "ActivityData.csv";

        List<Activity> activityList = new ArrayList<>();

        try (Scanner sc = new Scanner(new File("ActivityData.csv")))
        {
            while (sc.hasNextLine())
            {
                String line = sc.nextLine();
                String[] tokens = line.split(",");

                String activity_type = tokens[0];
                String date = tokens[1];
                double duration = Double.parseDouble(tokens[2]);
                double distance = Double.parseDouble(tokens[3]);
                int heart_rate = (int) Double.parseDouble(tokens[4]);

                Activity activity = new Activity(activity_type, duration, date, distance, heart_rate);

                activityList.add(activity);

            }
            for (Activity activity : activityList)
            {
                activity.calculateIntensity();
                activity.caloriesBurned();
                System.out.println(activity);
            }

        } catch (FileNotFoundException exception)
        {
            System.out.println("FileNotFoundException caught. The file " + fileName + "may not exist." + exception);
        }
    }
}