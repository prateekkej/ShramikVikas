package com.shramikvikas.Data;

import android.content.Context;
import com.shramikvikas.Data.DatabaseSchema.LaborSearchTable;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseOpenHelper extends SQLiteOpenHelper {
   static final String DATABASE_NAME="LaborData.db";
    static final int DATABASE_VER=1;
    String CREATE_TABLE_CMD="CREATE TABLE " + LaborSearchTable.TABLE_NAME + "(" + LaborSearchTable._ID +"INTEGER,"
            + LaborSearchTable.COLUMN_LABOR_NAME + "TEXT," + LaborSearchTable.COLUMN_LABOR_GENDER + "TEXT,"
            + LaborSearchTable.COLUMN_LABOR_PHONE + "TEXT," + LaborSearchTable.COLUMN_LABOR_RATING +"TEXT,"
            + LaborSearchTable.COLUMN_LABOR_TYPE + "TEXT," + LaborSearchTable.COLUMN_LABOR_SKILLS + "TEXT,"
            + LaborSearchTable.COLUMN_LABOR_PRICE + "INTEGER )";

    public DataBaseOpenHelper(Context context) {
        super(context,DATABASE_NAME, null,DATABASE_VER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseSchema.LaborSearchTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
sqLiteDatabase.execSQL(CREATE_TABLE_CMD);
    }
}
