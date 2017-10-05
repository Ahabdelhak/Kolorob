package demo.kolorob.kolorobdemoversion.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.model.CityCorporation;
import demo.kolorob.kolorobdemoversion.model.CommonModel;
import demo.kolorob.kolorobdemoversion.model.Ward;

/**
 * Created by shamima.yasmin on 8/23/2017.
 */

public class WardTable extends BaseDBTable <Ward>{


    private static final String TABLE_NAME = DatabaseHelper.WARDS;

    private static final String KEY_ID = "id"; // 0 -integer
    private static final String KEY_WARD_EN = "ward_en";
    private static final String KEY_WARD_BN = "ward_bn";
    private static final String KEY_WARD_KEYWORD = "ward_keyword";
    private static final String KEY_WARD_CCID = "cc_id";


    public WardTable(Context context) {
        tContext = context;
        createTable();
    }

    public WardTable() {

    }

    public void createTable() {
        SQLiteDatabase db = openDB();
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "( "
                + KEY_ID + " INTEGER PRIMARY KEY, " // 0 - int
                + KEY_WARD_EN + " TEXT, "              // 1 - text
                + KEY_WARD_BN + " TEXT, "
                + KEY_WARD_KEYWORD + " TEXT, "
                + KEY_WARD_CCID + " INTEGER "
                + " )";
        db.execSQL(CREATE_TABLE_SQL);
        closeDB();
    }


    public long insertItem(Ward ward){
        return insertItem(
                ward.getId(),
                ward.getWard_name(),
                ward.getWard_bn(),
                ward.getWard_keyword(),
                ward.getCc_id()
        );
    }

    public long insertItem(int id, String ward_name, String ward_bn, String ward_keyword, int cc_id) {
        if (isFieldExist(id)) {
            return updateItem(id, ward_name, ward_bn, ward_keyword, cc_id);
        }
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_WARD_EN, ward_name);
        rowValue.put(KEY_WARD_BN, ward_bn);
        rowValue.put(KEY_WARD_KEYWORD, ward_keyword);
        rowValue.put(KEY_WARD_CCID, cc_id);


        SQLiteDatabase db = openDB();
        long ret = db.insert(TABLE_NAME, null, rowValue);
        closeDB();
        return ret;
    }


    public boolean isFieldExist(int id) {
        return super.isFieldExist(id, TABLE_NAME, KEY_ID);
    }

    private long updateItem(int id, String ward_en, String ward_bn, String ward_keyword, int cc_id) {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_ID, id);
        rowValue.put(KEY_WARD_EN, ward_en);
        rowValue.put(KEY_WARD_BN, ward_bn);
        rowValue.put(KEY_WARD_KEYWORD, ward_keyword);
        rowValue.put(KEY_WARD_CCID, cc_id);


        SQLiteDatabase db = openDB();
        long ret = db.update(TABLE_NAME, rowValue, KEY_ID + " = ?",
                new String[]{id + ""});
        closeDB();
        return ret;
    }

    public ArrayList <Ward> getDataFromForeignKey(int id){
        return super.getDataListFromId(id, TABLE_NAME, KEY_WARD_CCID);
    }

    public Ward getDataFromId(int id){
        return super.getDataFromId(id, TABLE_NAME, KEY_ID);
    }

    public Ward cursorToModel(Cursor cursor) {
        int id = cursor.getInt(0);
        String ward_en = cursor.getString(1);
        String ward_bn = cursor.getString(2);
        String ward_keyword = cursor.getString(3);
        int cc_id = cursor.getInt(4);
        return new Ward(id, ward_en, ward_bn, ward_keyword, cc_id);
    }

    public Ward cursorToModel(Cursor cursor, CommonModel commonModel){
        return null;
    }

    public Ward getNodeInfo(int node) {

        SQLiteDatabase db = openDB();
        Ward ward = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + node , null);

        if (cursor.moveToFirst()) {
            do {
                ward = new Ward(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();
        return ward;
    }

    public void dropTable() {
       super.dropTable(TABLE_NAME);
    }
}
