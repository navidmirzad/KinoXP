package com.example.kinoxp.comparator;

import com.example.kinoxp.model.Show;

import java.util.Comparator;

public class ShowComparator implements Comparator<Show> {

    @Override
    public int compare(Show s1, Show s2) {
        int comparison = s1.getDate().compareTo(s2.getDate());
        if (comparison != 0) {
            return comparison;
        }
        return s1.getTime().compareTo(s2.getTime());
    }

}
