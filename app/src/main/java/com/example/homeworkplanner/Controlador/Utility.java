package com.example.homeworkplanner.Controlador;

public class Utility {
    //Constantes campos tabla usuario
    public static final String TABLE_NAME = "Mis_tareas";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "titulo";
    public static final String COLUMN_DESCRIPTION = "descripcion";
    public static final String COLUMN_LOCATION = "ubicacion";
    public static final String COLUMN_DATE = "fecha_estimada";
    public static final String COLUMN_TIME = "hora";
    public static final String COLUMN_PRIORITY = "prioridad";

    public static final String CREATE_TABLE_TAREAS = "CREATE TABLE " + TABLE_NAME +
            " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TITLE + " TEXT, " +
            COLUMN_DESCRIPTION + " TEXT, " +
            COLUMN_LOCATION + " TEXT, " +
            COLUMN_DATE + " DATE, " +
            COLUMN_TIME + " TEXT, " +
            COLUMN_PRIORITY + " TEXT" + ");";
}
