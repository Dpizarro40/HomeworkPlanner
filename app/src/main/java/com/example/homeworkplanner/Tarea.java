package com.example.homeworkplanner;

public class Tarea {
    private String titulo;
    private String descripcion;
    private String fecha;
    private String ubicacion;
    private String horaEstimada;


    public Tarea(String titulo, String descripcion, String fecha, String ubicacion, String horaEstimada) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.horaEstimada = horaEstimada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getHoraEstimada() {
        return horaEstimada;
    }

    public void setHoraEstimada(String horaEstimada) {
        this.horaEstimada = horaEstimada;
    }
}
