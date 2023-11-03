package org.example;

import java.util.Comparator;

public class DatesAscending implements Comparator<Activity>
{
    public int compare(Activity a1, Activity a2)
    {
        return a1.getDate().compareTo(a2.getDate());
    }
}
