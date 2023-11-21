package com.example.homeworkplanner;

public class Tarea {
    private String titulo;
    private String descripcion;
    private String fecha;
    private String ubicacion;
    private String horaEstimada;
    private String prioridad;


    public Tarea(String titulo, String descripcion, String fecha, String ubicacion, String horaEstimada, String prioridad) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.horaEstimada = horaEstimada;
        this.prioridad = prioridad;
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

    public String getPrioridad() { return prioridad; }

    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }
}
