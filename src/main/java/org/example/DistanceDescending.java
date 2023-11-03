package org.example;

import java.util.Comparator;

public class DistanceDescending implements Comparator<Activity>
{
    public int compare(Activity a1, Activity a2)
    {
        if (a1.getDistance() < a2.getDistance())
        {
            return 1;
        } else if (a1.getDistance() == a2.getDistance())
        {
            return 0;
        } else
        {
            return -1;
        }
    }
}
