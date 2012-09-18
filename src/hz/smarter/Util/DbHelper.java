package hz.smarter.Util;

import hz.smarter.Model.History;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

	final static String DBNAME = "smartb.db";
	final String TABLENAME = "sb";
	final static int VERSION = 1;

	public DbHelper(Context context) {
		super(context, DBNAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table sb(id int(2),open_device int(2),open1 int(2),open2 int(2),open3 int(2),open4 int(2),close_device int(2),close1 int(2),close2 int(2),close3 int(2),close4 int(2),phone varchar(15),hour int(2) ,minute int(2)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized void close() {
		// TODO Auto-generated method stub
		super.close();
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
	}

	public void insertHISTORY(SQLiteDatabase db) {
		Cursor cs = db.rawQuery("select * from sb", null);
		if (cs.getCount() > 0) {
			db.execSQL("delete * from sb");
		}
		ContentValues cv = new ContentValues();
		cv.put("open_device", History.OPENDEVICE ? 1 : -1);
		cv.put("open1", History.OPEN1 ? 1 : -1);
		cv.put("open2", History.OPEN2 ? 1 : -1);
		cv.put("open3", History.OPEN3 ? 1 : -1);
		cv.put("open4", History.OPEN4 ? 1 : -1);
		cv.put("close_device", History.CLOSEDEVICE ? 1 : -1);
		cv.put("close1", History.CLOSE1 ? 1 : -1);
		cv.put("close2", History.CLOSE2 ? 1 : -1);
		cv.put("close3", History.CLOSE3 ? 1 : -1);
		cv.put("close4", History.CLOSE4 ? 1 : -1);
		cv.put("phone", History.PHONE);
		cv.put("hour", History.HOUR);
		cv.put("minute", History.MINUTE);
		db.insert(TABLENAME, null, cv);
		db.close();

	}

	public void updateIsOPENDEVICE(SQLiteDatabase db) {
		updateAboutBoolean(db, History.OPENDEVICE, "open_device");
	}

	public void updateIsOPEN1(SQLiteDatabase db) {
		updateAboutBoolean(db, History.OPEN1, "open1");
	}

	public void updateIsOPEN2(SQLiteDatabase db) {
		updateAboutBoolean(db, History.OPEN2, "open2");
	}

	public void updateIsOPEN3(SQLiteDatabase db) {
		updateAboutBoolean(db, History.OPEN3, "open3");
	}

	public void updateIsOPEN4(SQLiteDatabase db) {
		updateAboutBoolean(db, History.OPEN4, "open4");
	}

	public void updateIsCLOSEDEVICE(SQLiteDatabase db) {
		updateAboutBoolean(db, History.CLOSEDEVICE, "CLOSE_device");
	}

	public void updateIsCLOSE1(SQLiteDatabase db) {
		updateAboutBoolean(db, History.CLOSE1, "CLOSE1");
	}

	public void updateIsCLOSE2(SQLiteDatabase db) {
		updateAboutBoolean(db, History.CLOSE2, "CLOSE2");
	}

	public void updateIsCLOSE3(SQLiteDatabase db) {
		updateAboutBoolean(db, History.CLOSE3, "CLOSE3");
	}

	public void updateIsCLOSE4(SQLiteDatabase db) {
		updateAboutBoolean(db, History.CLOSE4, "CLOSE4");
	}

	public void updatePHONE(SQLiteDatabase db) {
		Cursor cs = db.rawQuery("select * from sb", null);
		cs.moveToFirst();
		ContentValues cv = new ContentValues();
		cv.put("phone", History.PHONE);
		db.update(TABLENAME, cv, "id=?", new String[] { cs.getPosition() + "" });
	}

	public void updateHOUR(SQLiteDatabase db) {
		Cursor cs = db.rawQuery("select * from sb", null);
		cs.moveToFirst();
		ContentValues cv = new ContentValues();
		cv.put("hour", History.HOUR);
		db.update(TABLENAME, cv, "id=?", new String[] { cs.getPosition() + "" });
	}

	public void updateMINUTE(SQLiteDatabase db) {
		Cursor cs = db.rawQuery("select * from sb", null);
		cs.moveToFirst();
		ContentValues cv = new ContentValues();
		cv.put("minute", History.MINUTE);
		db.update(TABLENAME, cv, "id=?", new String[] { cs.getPosition() + "" });
	}

	public void updateAboutBoolean(SQLiteDatabase db, boolean yesOrNo,
			String filed) {
		Cursor cs = db.rawQuery("select * from sb", null);
		cs.moveToFirst();
		ContentValues cv = new ContentValues();
		cv.put(filed, yesOrNo ? 1 : -1);
		db.update(TABLENAME, cv, "id=?", new String[] { cs.getPosition() + "" });
	}

}
