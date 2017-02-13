package com.shramikvikas.Data;

import android.provider.BaseColumns;

/**
 * Created by Prateek on 14-02-2017.
 */

public final class DatabaseSchema {

    public static abstract class LaborSearchTable implements BaseColumns{
            public final static String ID="labor_id";
        public final static String TABLE_NAME="LaborFetchedData";
        public final static String COLUMN_LABOR_NAME="name";
            public final static String COLUMN_LABOR_RATING="rating";
            public final static String COLUMN_LABOR_GENDER="gender";
            public final static String COLUMN_LABOR_PHONE="phone";
            public final static String COLUMN_LABOR_TYPE="type";
            public final static String COLUMN_LABOR_PRICE="price";
            public final static String COLUMN_LABOR_SKILLS="price";
            public final static String GENDER_MALE="M";
            public final static String GENDER_FEMALE="F";
            public final static String GENDER_OTHER="O";







    }
}
