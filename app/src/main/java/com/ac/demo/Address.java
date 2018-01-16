package com.ac.demo;

import android.arch.persistence.room.ColumnInfo;

/**
 * Created by anna on 2017/11/29.
 */

public class Address {

    public String street;
    public String state;
    public String city;

    @ColumnInfo(name = "post_code")
    public int postCode;

}
