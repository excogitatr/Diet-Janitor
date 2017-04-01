package com.venky97vp.android.dietjanitor;

import java.util.Calendar;

/**
 * Created by venky on 02-04-2017.
 */

public class AgeCalculation {
    public static int ageCalc(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        return age;
    }
}
