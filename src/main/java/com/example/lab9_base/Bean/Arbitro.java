package com.example.lab9_base.Bean;

public class Arbitro {
    private String nombre;
    private String pais;
    private int idArbitro;

    // Constructor vacío
    public Arbitro() {}

    // Constructor que acepta un ID
    public Arbitro(int idArbitro) {
        this.idArbitro = idArbitro;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(int idArbitro) {
        this.idArbitro = idArbitro;
    }
}
