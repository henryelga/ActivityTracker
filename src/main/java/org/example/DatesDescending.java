package org.example;

import java.util.Comparator;

public class DatesDescending implements Comparator<Activity>
{
    public int compare(Activity a1, Activity a2)
    {
        return a2.getDate().compareTo(a1.getDate());
    }
}
