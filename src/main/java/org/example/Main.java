package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.*;

import java.io.FileNotFoundException;

//public class Main
//{
//    public static void main(String[] args)
//    {
//        // Read in the data from the CSV
//        String fileName = "ActivityData.csv";
public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
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
        System.out.print("\nEnter option: ");

        String fileName;
        int selectFile = kb.nextInt();
        if (selectFile == 1)
            fileName = "activity_data_10.csv";
        else if (selectFile == 2)
            fileName = "activity_data_50.csv";
        else if (selectFile == 3)
            fileName = "activity_data_100.csv";
        else
            fileName = "activity_data_1000.csv";

        List<Activity> activityList = new ArrayList<>();

        System.out.printf("%-16s %-12s %-10s %-10s %-10s %-16s %-10s %n", "Activity Type", "Date", "Duration", "Distance", "BPM", "Intensity", "Calories Burned"); // temporary line location
        System.out.println("------------------------------------------------------------------------------------------------------");
        File f = new File(fileName);
        try (Scanner sc = new Scanner(f))
        {
            while (sc.hasNextLine())
            {
                // ignoring first line in csv (header)
                if (sc.hasNextLine())
                    sc.nextLine();

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

                display(activityList);

//                Display activities sorted as calories descending

                CaloriesBurnedDescending caloriesBurnedDescending = new CaloriesBurnedDescending();
                Collections.sort(activityList, caloriesBurnedDescending);

                System.out.println("Calories Descending:");
                display(activityList);

//                Display activities sorted as dates ascending

                DatesAscending datesAscending = new DatesAscending();
                Collections.sort(activityList, datesAscending);

                System.out.println("Dates Ascending:");
                display(activityList);

//                Display activities sorted as dates descending

                DatesDescending datesDescending = new DatesDescending();
                Collections.sort(activityList, datesDescending);

                System.out.println("Dates Descending:");
                display(activityList);

            }
        } catch (FileNotFoundException exception)
        {
            System.out.println("FileNotFoundException caught. The file " + fileName + "may not exist." + exception);
        }


    }

    public static void display(List<Activity> activityList)
    {
        for (Activity activity : activityList)
        {
            activity.calculateIntensity();
            activity.caloriesBurned();
            System.out.println(activity);
        }
    }
}