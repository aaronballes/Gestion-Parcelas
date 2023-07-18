/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.DAOEjercicios;
import model.tablas.Ejercicios;

/**
 *
 * @author aaron
 */
public class EjerciciosAction implements IAction{
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {  
            case "LISTAR":
                cadDestino = listar(request, response);
                break;
                
                
        }
        return cadDestino;
        
        
    }
    
    private String listar(HttpServletRequest request, HttpServletResponse response){
        DAOEjercicios daoejer = new DAOEjercicios();
        String sRet = "";
        try{
            ArrayList<Ejercicios> detalles = daoejer.listar();
            sRet = Ejercicios.toArrayJSon(detalles);
        }catch(Exception e){
            System.out.println(e.getMessage()+"");
        }
        
        return sRet;
    }
}
