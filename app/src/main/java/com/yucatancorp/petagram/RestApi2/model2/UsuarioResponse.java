package com.yucatancorp.petagram.RestApi2.model2;

/**
 * Created by marcos on 28/05/2017.
 */

public class UsuarioResponse {

    private String id;
    private String token;
    private String user = "Hola";

    public UsuarioResponse(String id, String token, String user) {
        this.id = id;
        this.token = token;
        this.user = user;
    }

    public UsuarioResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
