package com.ac.demo;

import android.arch.persistence.room.ColumnInfo;

/**
 * Created by anna on 2017/11/29.
 */

public class NameTuple {

    @ColumnInfo(name="first_name")
    public String firstName;

    @ColumnInfo(name="last_name")
    public String lastName;
}
