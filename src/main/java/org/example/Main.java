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

                boolean displayMenu = true;
                while (displayMenu)
                {
                    System.out.println("\nSelect viewing option: \n");
                    System.out.println("1. Sort activity");
                    System.out.println("2. View subsets of activity");
                    System.out.println("3. View statistics of overall performance");
                    System.out.println("0. Exit");

                    System.out.print("Enter option: ");
                    int menuSelect = kb.nextInt();
                    kb.nextLine();
                    switch (menuSelect) {
                        case 1:
                            displaySort(activityList);
                            break;
                        case 2:
                            displaySubset(activityList);
                            break;
                        case 3:
                            displayOverall(activityList);
                            break;
                        case 0:
                            displayMenu = false;
                            System.out.println("Exiting...");
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

    public static void displaySort(List<Activity> activityList)
    {
        boolean viewSort = true;
        while (viewSort)
        {
            Scanner kb = new Scanner(System.in);
            System.out.println();
            System.out.println("Sort activity by:");
            System.out.println("----------------------");
            System.out.println("1. Calories Descending");
            System.out.println("2. Dates Ascending");
            System.out.println("3. Dates Descending");
            System.out.println("4. Distance Ascending");
            System.out.println("5. Distance Descending");
            System.out.println("6. Duration Ascending");
            System.out.println("7. Duration Descending");
            System.out.println("0. Exit");
            System.out.println();
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
                case 0:
                    viewSort = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    public static void displaySubset(List<Activity> activityList)
    {
        boolean viewSubset = true;
        while (viewSubset)
        {
            Scanner kb = new Scanner(System.in);
            System.out.println();
            System.out.println("View subset of activity by:");
            System.out.println("-------------------------------------");
            System.out.println("1. Activity Type");
            System.out.println("2. Above a minimum distance (km)");
            System.out.println("3. Type of energy expended");
            System.out.println("4. Above a minimum duration (minutes)");
            System.out.println("0. Exit");

            int subsetChoice = kb.nextInt();
            kb.nextLine();
            switch (subsetChoice)
            {
                case 1:
                    subsetActivity(activityList);
                    break;
                case 2:
                    subsetDistance(activityList);
                    break;
                case 3:
                    subsetIntensity(activityList);
                    break;
                case 4:
                    subsetDuration(activityList);
                    break;
                case 0:
                    viewSubset = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    public static void displayOverall(List<Activity> activityList)
    {
        boolean viewOverall = true;
        while (viewOverall) {
            Scanner kb = new Scanner(System.in);
            System.out.println();
            System.out.println("View overall activity:");
            System.out.println("--------------------------------");
            System.out.println("1. Average distance per activity");
            System.out.println("2. Average calories burned");
            System.out.println("0. Exit");

            int overallChoice = kb.nextInt();
            kb.nextLine();
            switch (overallChoice) {
                case 1:
                    double swimAvg=0, runAvg=0, cycAvg=0;
                    int swimCount=0, runCount=0, cycCount=0;

                    for (Activity activity : activityList)
                    {
                        boolean swimming = activity.getActivity_type().equals("Swimming");
                        boolean running = activity.getActivity_type().equals("Running");
                        boolean cycling = activity.getActivity_type().equals("Cycling");
                        if (swimming) {
                            swimCount++;
                            swimAvg += activity.getDistance();
                        }
                        if (running) {
                            runCount++;
                            runAvg += activity.getDistance();
                        }
                        if (cycling) {
                            cycCount++;
                            cycAvg += activity.getDistance();
                        }
                    }
                    swimAvg = swimAvg/swimCount;
                    runAvg = runAvg/runCount;
                    cycAvg = cycAvg/cycCount;
                    System.out.println();
                    System.out.println("Swimming average: "+swimAvg+"km");
                    System.out.println("Running average: "+runAvg+"km");
                    System.out.println("Cycling average: "+cycAvg+"km");
                    break;

                case 2:
                    double avgCalories = 0;
                    int calCount = 0;
                    for (Activity activity : activityList)
                    {
                        avgCalories += activity.getCalories_burned();
                        calCount++;
                    }
                    avgCalories = avgCalories/calCount;
                    System.out.println();
                    System.out.println("Average calories burned: "+avgCalories);
                    break;

                case 0:
                    viewOverall = false;
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

    public static void subsetActivity(List<Activity> activityList)
    {
        Scanner kb = new Scanner(System.in);
        System.out.println("Choose activity type: ");
        System.out.println("1. " + ActivityENUM.Swimming);
        System.out.println("2. " + ActivityENUM.Running);
        System.out.println("3. " + ActivityENUM.Cycling);
        System.out.print("Enter option: ");
        String activityChoice = kb.nextLine();
        System.out.printf("%-16s %-12s %-10s %-10s %-10s %-16s %-10s %n", "Activity Type", "Date", "Duration", "Distance", "BPM", "Intensity", "Calories Burned");
        System.out.println("-------------------------------------------------------------------------------------------------");
        for (Activity activity : activityList)
        {
            activity.calculateIntensity();
            activity.caloriesBurned();

            switch (activityChoice)
            {
                case "1":
                    if (activity.getActivity_type().equals(ActivityENUM.Swimming.toString()))
                    {
                        System.out.println(activity);
                    }
                    break;
                case "2":
                    if (activity.getActivity_type().equals(ActivityENUM.Running.toString()))
                    {
                        System.out.println(activity);
                    }
                    break;
                case "3":
                    if (activity.getActivity_type().equals(ActivityENUM.Cycling.toString()))
                    {
                        System.out.println(activity);
                    }
                    break;
            }
        }
    }

    public static void subsetDistance(List<Activity> activityList)
    {
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter minimum distance: ");
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
    }

    public static void subsetIntensity(List<Activity> activityList)
    {
        Scanner kb = new Scanner(System.in);
        System.out.println("Choose type of intensity: ");
        System.out.println("1. Very Light");
        System.out.println("2. Light");
        System.out.println("3. Moderate");
        System.out.println("4. Vigorous");
        System.out.println("5. Very Vigorous");
        System.out.print("Enter option: ");
        String energyType = kb.nextLine();
        System.out.printf("%-16s %-12s %-10s %-10s %-10s %-16s %-10s %n", "Activity Type", "Date", "Duration", "Distance", "BPM", "Intensity", "Calories Burned");
        System.out.println("-------------------------------------------------------------------------------------------------");
        for (Activity activity : activityList)
        {
            activity.calculateIntensity();
            activity.caloriesBurned();
            switch (energyType)
            {
                case "1":
                    if (activity.getIntensity().equals("Very Light"))
                        System.out.println(activity);
                    break;
                case "2":
                    if (activity.getIntensity().equals("Light"))
                        System.out.println(activity);
                    break;
                case "3":
                    if (activity.getIntensity().equals("Moderate"))
                        System.out.println(activity);
                    break;
                case "4":
                    if (activity.getIntensity().equals("Vigorous"))
                        System.out.println(activity);
                    break;
                case "5":
                    if (activity.getIntensity().equals("Very Vigorous"))
                        System.out.println(activity);
                    break;

            }
        }
    }

    public static void subsetDuration(List<Activity> activityList)
    {
        Scanner kb = new Scanner(System.in);
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
    }
}