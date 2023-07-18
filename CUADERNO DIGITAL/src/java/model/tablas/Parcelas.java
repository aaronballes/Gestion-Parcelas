/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tablas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

/**
 *
 * @author aaron
 */
public class Parcelas {
    String parcela_id;
    String nombre;
    String poligono;
    String parcela;
    String superficie;

    public Parcelas() {
    }

    public String getParcela_id() {
        return parcela_id;
    }

    public void setParcela_id(String parcela_id) {
        this.parcela_id = parcela_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPoligono() {
        return poligono;
    }

    public void setPoligono(String poligono) {
        this.poligono = poligono;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }
    
    public static String toArrayJSon(ArrayList<Parcelas> parcelas) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(parcelas);

        return resp;
    }
}
