package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.*;

import java.io.FileNotFoundException;

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
        System.out.println("Select which file to view the activity of: ");
        System.out.println();
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

                boolean continueSorting = true;
                while (continueSorting)
                {
                    System.out.println("\nSelect sorting option: \n");
                    System.out.println("1. Calories Descending");
                    System.out.println("2. Dates Ascending");
                    System.out.println("3. Dates Descending");
                    System.out.println("4. Distance Ascending");
                    System.out.println("5. Distance Descending");
                    System.out.println("6. Duration Ascending");
                    System.out.println("7. Duration Descending");
                    System.out.println("8. View Subsets");
                    System.out.println("9. Exit");

                    System.out.print("Enter option: ");
                    int option = kb.nextInt();
                    kb.nextLine();
                    switch (option)
                    {
                        case 1:
                            displayCaloriesBurnedDescending(activityList);
                            break;
                        case 2:
                            displayDatesAscending(activityList);
                            break;
                        case 3:
                            displayDatesDescending(activityList);
                            break;
                        case 4:
                            displayDistanceAscending(activityList);
                            break;
                        case 5:
                            displayDistanceDescending(activityList);
                            break;
                        case 6:
                            displayDurationAscending(activityList);
                            break;
                        case 7:
                            displayDurationDescending(activityList);
                            break;
                        case 8:
                            displaySubset(activityList);
                        case 9:
                            continueSorting = false;
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                            break;
                    }
                }
            }
        } catch (FileNotFoundException exception)
        {
            System.out.println("FileNotFoundException caught. The file " + fileName + "may not exist." + exception);
        }

        displaySubset(activityList);

    }

    public static void display(List<Activity> activityList)
    {
        System.out.printf("%-16s %-12s %-10s %-10s %-10s %-16s %-10s %n", "Activity Type", "Date", "Duration", "Distance", "BPM", "Intensity", "Calories Burned");
        System.out.println("-------------------------------------------------------------------------------------------------");
        for (Activity activity : activityList)
        {
            activity.calculateIntensity();
            activity.caloriesBurned();
            System.out.println(activity);
        }
    }

    public static void displaySubset(List<Activity> activityList)
    {
        boolean continueSorting = true;
        while (continueSorting)
        {
            Scanner kb = new Scanner(System.in);
            System.out.println();
            System.out.println("View subset of activity by: ");
            System.out.println();
            System.out.println("1. Activity Type");
            System.out.println("2. Above a minimum distance");
            System.out.println("3. Type of energy expended");
            System.out.println("4. Above a minimum duration");
            System.out.println("5. Exit Sorting");

            int subsetChoice = kb.nextInt();
            kb.nextLine();
            switch (subsetChoice)
            {
                case 1:
                    System.out.print("Enter activity type: ");
                    String activityChoice = kb.nextLine();
                    System.out.printf("%-16s %-12s %-10s %-10s %-10s %-16s %-10s %n", "Activity Type", "Date", "Duration", "Distance", "BPM", "Intensity", "Calories Burned");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    for (Activity activity : activityList)
                    {
                        activity.calculateIntensity();
                        activity.caloriesBurned();
                        if (activity.getActivity_type().equals(activityChoice))
                            System.out.println(activity);
                    }
                    break;
                case 2:
                    System.out.println("Enter minimum distance: ");
                    double minDistance = kb.nextDouble();
                    System.out.printf("%-16s %-12s %-10s %-10s %-10s %-16s %-10s %n", "Activity Type", "Date", "Duration", "Distance", "BPM", "Intensity", "Calories Burned");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    for (Activity activity : activityList)
                    {
                        activity.calculateIntensity();
                        activity.caloriesBurned();
                        if (activity.getDistance() >= minDistance)
                            System.out.println(activity);
                    }
                    break;
                case 3:
                    System.out.println("Enter type of energy: ");
                    String energyType = kb.nextLine();
                    energyType.toLowerCase();
                    System.out.printf("%-16s %-12s %-10s %-10s %-10s %-16s %-10s %n", "Activity Type", "Date", "Duration", "Distance", "BPM", "Intensity", "Calories Burned");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    for (Activity activity : activityList)
                    {
                        activity.calculateIntensity();
                        activity.caloriesBurned();
                        if (activity.getActivity_type().toLowerCase().equals(energyType))
                            System.out.println(activity);
                    }
                    break;
                case 4:
                    System.out.println("Enter minimum duration: ");
                    int minDuration = kb.nextInt();
                    System.out.printf("%-16s %-12s %-10s %-10s %-10s %-16s %-10s %n", "Activity Type", "Date", "Duration", "Distance", "BPM", "Intensity", "Calories Burned");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    for (Activity activity : activityList)
                    {
                        activity.calculateIntensity();
                        activity.caloriesBurned();
                        if (activity.getDuration() >= minDuration)
                            System.out.println(activity);
                    }
                    break;
                case 5:
                    continueSorting = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    public static void displayCaloriesBurnedDescending(List<Activity> activityList)
    {
        CaloriesBurnedDescending caloriesBurnedDescending = new CaloriesBurnedDescending();
        Collections.sort(activityList, caloriesBurnedDescending);

        display(activityList);
    }

    public static void displayDatesAscending(List<Activity> activityList)
    {
        DatesAscending datesAscending = new DatesAscending();
        Collections.sort(activityList, datesAscending);

        display(activityList);
    }

    public static void displayDatesDescending(List<Activity> activityList)
    {
        DatesDescending datesDescending = new DatesDescending();
        Collections.sort(activityList, datesDescending);

        display(activityList);
    }

    public static void displayDistanceAscending(List<Activity> activityList)
    {
        DistanceAscending distanceAscending = new DistanceAscending();
        Collections.sort(activityList, distanceAscending);

        display(activityList);
    }

    public static void displayDistanceDescending(List<Activity> activityList)
    {
        DistanceDescending distanceDescending = new DistanceDescending();
        Collections.sort(activityList, distanceDescending);

        display(activityList);
    }

    public static void displayDurationAscending(List<Activity> activityList)
    {
        DurationAscending durationAscending = new DurationAscending();
        Collections.sort(activityList, durationAscending);

        display(activityList);
    }

    public static void displayDurationDescending(List<Activity> activityList)
    {
        DurationDescending durationDescending = new DurationDescending();
        Collections.sort(activityList, durationDescending);

        display(activityList);
    }
}