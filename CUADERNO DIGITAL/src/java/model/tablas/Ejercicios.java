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
public class Ejercicios {
    String ejercicio_id;
    String ejercicio;

    public Ejercicios() {
    }

    public String getEjercicio_id() {
        return ejercicio_id;
    }

    public void setEjercicio_id(String ejercicio_id) {
        this.ejercicio_id = ejercicio_id;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }
    
    public static String toArrayJSon(ArrayList<Ejercicios> ejercicios) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(ejercicios);

        return resp;
    }
}
