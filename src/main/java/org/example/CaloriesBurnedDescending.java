package org.example;

import java.util.Comparator;

public class CaloriesBurnedDescending implements Comparator<Activity>
{
    public int compare(Activity a1, Activity a2)
    {
        if (a1.getCalories_burned() < a2.getCalories_burned())
        {
            return 1;
        } else if (a1.getCalories_burned() == a2.getCalories_burned())
        {
            return 0;
        } else
        {
            return -1;
        }
    }
}
