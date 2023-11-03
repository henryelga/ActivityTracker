package org.example;

import java.util.Comparator;

public class DurationAscending implements Comparator<Activity>
{
    public int compare(Activity a1, Activity a2)
    {
        if (a1.getDuration() < a2.getDuration())
        {
            return -1;
        } else if (a1.getDuration() == a2.getDuration())
        {
            return 0;
        } else
        {
            return 1;
        }
    }
}
