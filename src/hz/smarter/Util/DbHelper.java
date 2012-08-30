package hz.smarter.Util;

import hz.smarter.Model.History;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

	final static String DBNAME = "smartb.db";
	final static int VERSION = 1;

	public DbHelper(Context context) {
		super(context, DBNAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table sb (phone int(11) , _on int(2) ,on_time varchar(20) ,off int(2) ,off_time varchar(20) ,hour int(2) ,minute int(2))");
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

	public void insert(SQLiteDatabase db, History history) {
		ContentValues cv = new ContentValues();
		cv.put("phone", history.getPHONE());
		cv.put("_on", history.isON() ? 1 : -1);
		cv.put("on_time", history.ONTIME);
		cv.put("off", history.isOFF() ? 1 + "" : -1 + "");
		cv.put("off_time", history.OFFTIME);
		cv.put("hour", history.getHOUR());
		cv.put("minute", history.getMINUTE());
		db.insert("sb", null, cv);
		db.close();

	}

	public List<History> getAllHistory(SQLiteDatabase db) {
		Cursor cs = db.rawQuery("select * from sb", null);
		System.out.println("count" + cs.getCount());
		if (cs.moveToFirst()) {
			List<History> list = new ArrayList<History>();
			History history;
			while (!cs.isAfterLast()) {
				history = new History();
				history.setON(cs.getInt(cs.getColumnIndex("_on")) == 1 ? true
						: false);
				history.setONTIME(cs.getString(cs.getColumnIndex("on_time")));
				history.setOFF(cs.getInt(cs.getColumnIndex("off")) == 1 ? true
						: false);
				history.setOFFTIME(cs.getString(cs.getColumnIndex("off_time")));
				history.setPHONE(cs.getInt(cs.getColumnIndex("phone")));
				history.setHOUR(cs.getInt(cs.getColumnIndex("hour")));
				history.setMINUTE(cs.getInt(cs.getColumnIndex("minute")));
				cs.moveToNext();
				list.add(history);
			}
			cs.close();
			db.close();
			return list;
		}
		return null;
	}

	public Cursor getAllHistoryCursor(SQLiteDatabase db) {
		Cursor cs = db.rawQuery("select * from sb", null);
		if (cs.getCount() != -1) {
			return cs;
		} else {
			return null;
		}
	}

	public History getLastHistory(SQLiteDatabase db) {
		Cursor cs = db.rawQuery("select * from sb", null);
		if (cs.isLast()) {
			cs.moveToLast();
			History history = new History();
			history.setON(cs.getInt(cs.getColumnIndex("_on")) == 1 ? true
					: false);
			history.setONTIME(cs.getString(cs.getColumnIndex("on_time")));
			history.setOFF(cs.getInt(cs.getColumnIndex("off")) == 1 ? true
					: false);
			history.setOFFTIME(cs.getString(cs.getColumnIndex("off_time")));
			history.setPHONE(cs.getInt(cs.getColumnIndex("phone")));
			history.setHOUR(cs.getInt(cs.getColumnIndex("hour")));
			history.setMINUTE(cs.getInt(cs.getColumnIndex("minute")));
			return history;
		}
		cs.close();
		db.close();
		return null;
	}
}
