package com.yucatancorp.petagram.pojo;

/**
 * Created by marcos on 12/01/2017.
 */

public class FotosMascota {

    private String id;
    private String nombreCompleto;
    private String urlFoto;
    private int calif = 0;

    public FotosMascota(String nombreCompleto, String urlFoto, int calif)
    {
        this.nombreCompleto = nombreCompleto;
        this.urlFoto  = urlFoto;
        this.calif = calif;
    }

    public FotosMascota()
    {}

    public void setId(String id){ this.id = id; }

    public String getId(){ return id; }

    public void setNombreCompleto(String nombreCompleto){ this.nombreCompleto = nombreCompleto; }

    public String getNombreCompleto(){ return nombreCompleto; }

    public String geturlFoto() {
        return urlFoto;
    }

    public void seturlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getCalif() {
        return calif;
    }

    public void setCalif(int calif) {
        this.calif = calif;
    }
}
