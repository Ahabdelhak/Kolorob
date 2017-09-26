package demo.kolorob.kolorobdemoversion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.model.CityCorporation;


/**
 * Created by shamima.yasmin on 8/23/2017.
 */

public class CityCorporationTable extends BaseDBTable <CityCorporation> {


    private static final String TABLE_NAME = DatabaseHelper.CITYCORPORATIONS;
    private static final String KEY_ID = "id"; // 0 -integer
    private static final String KEY_CC_EN = "cc_en";
    private static final String KEY_CC_BN = "cc_bn";
    private static final String KEY_CC_KEYWORD = "cc_keyword";


    public CityCorporationTable(Context context) {
        tContext = context;
        createTable();
    }

    public CityCorporationTable() {

    }

    public void createTable() {
        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_ID + " INTEGER PRIMARY KEY, " // 0 - int
                + KEY_CC_EN + " TEXT, "              // 1 - text
                + KEY_CC_BN + " TEXT, "
                + KEY_CC_KEYWORD + " TEXT "
                + " )";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(CityCorporation cityCorporation){
        return insertItem(
                cityCorporation.getId(),
                cityCorporation.getCityCorporation_name(),
                cityCorporation.getCityCorporation_bn(),
                cityCorporation.getCityCorporation_keyword()
        );
    }

    public long insertItem(int id, String cc_name, String cc_bn, String cc_keyword) {
        if (isFieldExist(id)) {
            return updateItem(id, cc_name, cc_bn, cc_keyword);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_CC_EN, cc_name);
        rowValue.put(KEY_CC_BN, cc_bn);
        rowValue.put(KEY_CC_KEYWORD, cc_keyword);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }


    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME);
    }

    private long updateItem(int id, String cc_en, String cc_bn, String cc_keyword) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_CC_EN, cc_en);
        rowValue.put(KEY_CC_BN, cc_bn);
        rowValue.put(KEY_CC_KEYWORD, cc_keyword);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_ID + " = ?",
                new String[]{id + ""});
        closeDB();
        return ret;
    }

    public ArrayList <CityCorporation> getAllData() {
        return super.getAllData(TABLE_NAME);
    }

    public CityCorporation getNodeInfo(int node) {

        SQLiteDatabase db = openDB();
        CityCorporation cityCorporation = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + node , null);

        if (cursor.moveToFirst()) {
            do {
                cityCorporation = new CityCorporation(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return cityCorporation;
    }


    public CityCorporation cursorToModel(Cursor cursor) {
        int id = cursor.getInt(0);
        String cc_en = cursor.getString(1);
        String cc_bn = cursor.getString(2);
        String cc_keyword = cursor.getString(3);

        return new CityCorporation(id, cc_en, cc_bn, cc_keyword);
    }

    public void dropTable() {
        super.dropTable(TABLE_NAME);
    }
}
