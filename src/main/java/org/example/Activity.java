package org.example;

public class Activity
{
    private String activity_type;
    private double duration; // in minutes
    private String date; // format: dd/mm/yyyy
    private double distance; // in kilometers
    private int heart_rate; // in beats per minute

    public Activity(String activity_type, double duration, String date, double distance, int heart_rate)
    {
        this.activity_type = activity_type;
        this.duration = duration;
        this.date = date;
        this.distance = distance;
        this.heart_rate = heart_rate;
    }

    public String getActivity_type()
    {
        return activity_type;
    }

    public void setActivity_type(String activity_type)
    {
        this.activity_type = activity_type;
    }

    public double getDuration()
    {
        return duration;
    }

    public void setDuration(double duration)
    {
        this.duration = duration;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(int heart_rate) {
        this.heart_rate = heart_rate;
    }

    /*
    TODO
     - energy expended based on the average kilometres per hour
     - calories burned based on energy expended, type of activity and duration
     -
     - create class to make arrayList of activities
     - view subset of activities  based on:
        - activity type
        - above minimum distance
        - type of energy exceeded
        - above minimum duration
     -
     - make separate comparator classes:
        - Calories burned (Descending)
        - Date (Ascending/Descending)
        - Activity Duration (Ascending/Descending)
        - Type of Activity
        - Distance (Ascending/Descending
      -
      - Make enum class for activities
        - running, swimming, cycling
      - Find specific activity based on their Natural ordering
        using the binary search function of the Collections class
      -
      - View statistics on their overall performance
            - Average distance per activity
            - Average calories burned
      -
      - Additional Fields to calculate:
        - Intensity
        - Calories Burned
      -
      - You are required to use a combination of natural ordering
      - separate Comparator class(es), an anonymous inner class, and a number of lambdas
    */
}
