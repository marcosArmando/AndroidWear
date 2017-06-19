package com.yucatancorp.petagram.pojo;

/**
 * Created by marcos on 09/12/2016.
 */

public class Mascota {

    private int id;
    private int foto;
    private int hueso;
    private String nombre;
    private int ranking;

    public Mascota(int foto, int hueso, String nombre, int ranking) {
        this.foto = foto;
        this.hueso = hueso;
        this.nombre = nombre;
        this.ranking = ranking;
    }

    public Mascota() {

    }

    public int getFoto() { return foto; }

    public void setFoto(int foto) { this.foto = foto;  }

    public int getHueso() { return hueso; }

    public void setHueso(int hueso) { this.hueso = hueso; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRanking() { return ranking; }

    public void setRanking(int ranking) {this.ranking = ranking; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
