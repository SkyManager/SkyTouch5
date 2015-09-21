package com.skyled.skytouch5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataB extends SQLiteOpenHelper  {

	private static final String DATABASE_NAME = "sdb.db";
	private static final int DATABASE_VERSION = 1;
	public static final String T_PRESETS = "presets";
	public static final String T_ALARMS = "alarms";
	public static final String T_EFFECTS = "effects";
	public static final String T_SHEDULE = "shedule";
	public static final String T_CHANALS = "chanals";
	public static final String T_ROOMS = "rooms";

	// ________________________________________PRESET TABLE   ITEMS___________________________
	public static final String P_ID = "_id";
	public static final String P_STATUS = "P_status";
	public static final String P_NAME = "P_filename";
	public static final String P_TIMING ="p_timing";
	public static final String P_BR ="p_brightness";
	public static final String P_CH1 = "p_chanal1";
	public static final String P_CH2 = "p_chanal2";
	public static final String P_CH3 = "p_chanal3";
	public static final String P_CH4 = "p_chanal4";
	public static final String P_CH5 = "p_chanal5";
	public static final String P_CHBR1 = "p_chanalbr1";
	public static final String P_CHBR2 = "p_chanalbr2";
	public static final String P_CHBR3 = "p_chanalbr3";
	public static final String P_CHBR4 = "p_chanalbr4";
	public static final String P_CHBR5 = "p_chanalbr5";
	public static final String P_BG_ACTYVITY = "p_bg_activity";
	public static final String P_BG_ACTIVEDAYS = "p_bg_activedays";
	public static final String P_BG_ACTIVESENSORS = "p_bg_activesensors";
	public static final String P_BG_SENSORTIME = "p_bg_sensortime";
	public static final String P_BG_LIGHTESTATUS = "p_bg_lightstatus";
	
	// ________________________________________SHEDULE TABLE   ITEMS___________________________

	public static final String S_ID = "_id";
	public static final String S_DAY1 = "mon";
	public static final String S_DAY2 = "tue";
	public static final String S_DAY3 = "wen";
	public static final String S_DAY4 = "thu";
	public static final String S_DAY5 = "fri";
	public static final String S_DAY6 = "sat";
	public static final String S_DAY7 = "san";
	
	
	// ________________________________________Chanal TABLE   ITEMS___________________________
	
	public static final String CH_ID = "_id";
	public static final String CH_NAME = "ch_name";
	public static final String CH_MODE = "ch_mode";
	public static final String CH_BRIGHT = "ch_bright";
	public static final String CH_COLOR = "ch_color";
	public static final String CH_SATUR = "ch_satur";
	public static final String CH_STATUS = "ch_status";
	public static final String CH_PARENT = "ch_parent";
	
	// ________________________________________Rooms TABLE   ITEMS___________________________
	
	public static final String R_ID = "_id";
	public static final String R_NAME = "r_name";
	public static final String R_ICON = "r_icon";
	
	
	private static final String SQL_CREATE_PRESET = "CREATE TABLE "
			+ T_PRESETS + "("+ P_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
			P_STATUS + " VARCHAR(255),"+
			P_NAME   + " VARCHAR(255),"+
			P_TIMING + " VARCHAR(255),"+
			P_BR + " VARCHAR(255),"+
			P_BG_ACTYVITY + " VARCHAR(255),"+
			P_BG_ACTIVEDAYS + " VARCHAR(255),"+
			P_BG_ACTIVESENSORS + " VARCHAR(255),"+
			P_BG_SENSORTIME + " VARCHAR(255),"+
			P_BG_LIGHTESTATUS + " VARCHAR(255),"+
			P_CHBR1+ " VARCHAR(255)," +
			P_CHBR2+ " VARCHAR(255)," +
			P_CHBR3+ " VARCHAR(255)," +
			P_CHBR4+ " VARCHAR(255)," +
			P_CHBR5+ " VARCHAR(255)," +
			P_CH1+ " VARCHAR(255)," +
			P_CH2 + " VARCHAR(255),"+
			P_CH3+ " VARCHAR(255)," + 
			P_CH4+ " VARCHAR(255),"+
			P_CH5+ " VARCHAR(255));";
	
	private static final String SQL_CREATE_SHEDULE = "CREATE TABLE "
			+ T_SHEDULE + "("+ S_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
			S_DAY1 + " VARCHAR(255),"+
			S_DAY2+ " VARCHAR(255),"+
			S_DAY3+ " VARCHAR(255)," +
			S_DAY4+ " VARCHAR(255),"+
			S_DAY5+ " VARCHAR(255)," + 
			S_DAY6+ " VARCHAR(255),"+
			S_DAY7+ " VARCHAR(255));";
	
	private static final String SQL_CREATE_CHANALS = "CREATE TABLE "
			+ T_CHANALS + "("+ CH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
			CH_NAME + " VARCHAR(255),"+
			CH_STATUS+ " VARCHAR(255),"+
			CH_MODE+ " VARCHAR(255)," + 
			CH_BRIGHT+ " VARCHAR(255),"+
			CH_PARENT+ " VARCHAR(255),"+
			CH_SATUR+ " VARCHAR(255),"+
			CH_COLOR+ " VARCHAR(255));";
	
	private static final String SQL_CREATE_ROOMS = "CREATE TABLE "
			+ T_ROOMS + "("+ R_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
			R_NAME + " VARCHAR(255),"+
			R_ICON+ " VARCHAR(255),";

	private static final String SQL_DELETE_PRESET = "DROP TABLE IF EXISTS "
			+ T_PRESETS;
	
	private static final String SQL_DELETE_SHEDULE = "DROP TABLE IF EXISTS "
			+ T_SHEDULE;
	
	private static final String SQL_DELETE_CHANALS = "DROP TABLE IF EXISTS "
			+ T_CHANALS;
	
	private static final String SQL_DELETE_ROOMS = "DROP TABLE IF EXISTS "
			+ T_ROOMS;

	public DataB(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}




	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_PRESET);
		db.execSQL(SQL_CREATE_SHEDULE);
		db.execSQL(SQL_CREATE_CHANALS);
		db.execSQL(SQL_CREATE_ROOMS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w("LOG_TAG", "Обновление базы данных с версии " + oldVersion
				+ " до версии " + newVersion
				+ ", которое удалит все старые данные");
		// Удаляем предыдущую таблицу при апгрейде
		db.execSQL(SQL_DELETE_PRESET);
		db.execSQL(SQL_DELETE_SHEDULE);
		db.execSQL(SQL_DELETE_CHANALS);
		db.execSQL(SQL_CREATE_ROOMS);
		// Создаём новый экземпляр таблицы
		onCreate(db);
	}

}