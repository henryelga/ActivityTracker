package org.example;

import java.util.Collections;

public class Activity
{
    private String activity_type;
    private double duration; // in minutes
    private String date; // format: dd/mm/yyyy
    private double distance; // in kilometers
    private int heart_rate; // in beats per minute

    private String intensity = "";

    private double calories_burned;

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

    public double getDistance()
    {
        return distance;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    public int getHeart_rate()
    {
        return heart_rate;
    }

    public void setHeart_rate(int heart_rate)
    {
        this.heart_rate = heart_rate;
    }

    public void calculateIntensity()
    {
        double kilometer_per_hour = distance / (duration / 60);

        if (activity_type.equals("Swimming"))
        {
            if (kilometer_per_hour <= 0.5)
            {
                intensity = "Very Light";
            } else if (kilometer_per_hour <= 1.25)
            {
                intensity = "Light";
            } else if (kilometer_per_hour <= 2)
            {
                intensity = "Moderate";
            } else if (kilometer_per_hour <= 2.75)
            {
                intensity = "Vigorous";
            } else
            {
                intensity = "Very Vigorous";
            }
        } else if (activity_type.equals("Running"))
        {
            if (kilometer_per_hour <= 4)
            {
                intensity = "Very Light";
            } else if (kilometer_per_hour > 4 && kilometer_per_hour <= 8)
            {
                intensity = "Light";
            } else if (kilometer_per_hour > 8 && kilometer_per_hour <= 12)
            {
                intensity = "Moderate";
            } else if (kilometer_per_hour > 12 && kilometer_per_hour <= 16)
            {
                intensity = "Vigorous";
            } else
            {
                intensity = "Very Vigorous";
            }
        } else if (activity_type.equals("Cycling"))
        {
            if (kilometer_per_hour <= 8)
            {
                intensity = "Very Light";
            } else if (kilometer_per_hour > 8 && kilometer_per_hour <= 16)
            {
                intensity = "Light";
            } else if (kilometer_per_hour >= 17 && kilometer_per_hour <= 25)
            {
                intensity = "Moderate";
            } else if (kilometer_per_hour >= 25 && kilometer_per_hour <= 33)
            {
                intensity = "Vigorous";
            } else
            {
                intensity = "Very Vigorous";
            }
        }
    }

    public void caloriesBurned()
    {
        if (activity_type.equals("Swimming"))
        {
            if (intensity.equals("Very Light"))
            {
                calories_burned = 5 * duration;
            } else if (intensity.equals("Light"))
            {
                calories_burned = 6.3 * duration;
            } else if (intensity.equals("Moderate"))
            {
                calories_burned = 7.6 * duration;
            } else if (intensity.equals("Vigorous"))
            {
                calories_burned = 8.9 * duration;
            } else if (intensity.equals("Very Vigorous"))
            {
                calories_burned = 10.2 * duration;
            }
        } else if (activity_type.equals("Running"))
        {
            if (intensity.equals("Very Light"))
            {
                calories_burned = 4.1 * duration;
            } else if (intensity.equals("Light"))
            {
                calories_burned = 7.2 * duration;
            } else if (intensity.equals("Moderate"))
            {
                calories_burned = 10 * duration;
            } else if (intensity.equals("Vigorous"))
            {
                calories_burned = 15.4 * duration;
            } else if (intensity.equals("Very Vigorous"))
            {
                calories_burned = 20.8 * duration;
            }
        } else if (activity_type.equals("Cycling"))
        {
            if (intensity.equals("Very Light"))
            {
                calories_burned = 2 * duration;
            } else if (intensity.equals("Light"))
            {
                calories_burned = 5 * duration;
            } else if (intensity.equals("Moderate"))
            {
                calories_burned = 7 * duration;
            } else if (intensity.equals("Vigorous"))
            {
                calories_burned = 13 * duration;
            } else if (intensity.equals("Very Vigorous"))
            {
                calories_burned = 15 * duration;
            }
        }
    }

    public String getIntensity()
    {
        return intensity;
    }

    public double getCalories_burned()
    {
        return calories_burned;
    }

    @Override
    public String toString()
    {
        return String.format("%-16s %-12s %-10.1f %-10.1f %-10d %-16s %-10.1f",
                activity_type, date, duration, distance, heart_rate, intensity, calories_burned);
    }

//    need to implement enum

    /*
    TO DO:
STEP 1
Allow the user to upload a csv file
Read in the data from a csv file
Write menu to allow user to view:
- calories burned
- date
- activity duration
- type of activity
- distance

STEP 2
Allow user to view subset of their data (?)
Find specific activity using binary search
View statistics of overall performance
    */
}
