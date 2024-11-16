package com.example.lab9_base.Bean;

import java.sql.Date;

public class Partido {
    private int idPartido;
    private Seleccion seleccionLocal;
    private Seleccion seleccionVisitante;
    private Arbitro arbitro;
    private Date fecha; // Cambia a java.sql.Date
    private int numeroJornada;

    // Getters y Setters
    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public Seleccion getSeleccionLocal() {
        return seleccionLocal;
    }

    public void setSeleccionLocal(Seleccion seleccionLocal) {
        this.seleccionLocal = seleccionLocal;
    }

    public Seleccion getSeleccionVisitante() {
        return seleccionVisitante;
    }

    public void setSeleccionVisitante(Seleccion seleccionVisitante) {
        this.seleccionVisitante = seleccionVisitante;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) { // Asegúrate de que acepte java.sql.Date
        this.fecha = fecha;
    }

    public int getNumeroJornada() {
        return numeroJornada;
    }

    public void setNumeroJornada(int numeroJornada) {
        this.numeroJornada = numeroJornada;
    }
}
