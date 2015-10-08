package com.example.mikerooijackers.mydatelib;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by mikerooijackers on 05-10-15.
 */
public class MyDate {
    public String date() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateInstance();
        return "date: " + dateFormat.format(date);
    }
}
