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
public class Cultivos {
    int cultivo_id;
    String cultivo;
    double hectareas;
    double porcentaje;
    
    public Cultivos() {
    }
    

    public int getCultivo_id() {
        return cultivo_id;
    }

    public void setCultivo_id(int cultivo_id) {
        this.cultivo_id = cultivo_id;
    }

    public String getCultivo() {
        return cultivo;
    }

    public void setCultivo(String cultivo) {
        this.cultivo = cultivo;
    }

    public double getHectareas() {
        return hectareas;
    }

    public void setHectareas(double hectareas) {
        this.hectareas = hectareas;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public static String toArrayJSon(ArrayList<Cultivos> cultivos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(cultivos);

        return resp;
    }
    
}
