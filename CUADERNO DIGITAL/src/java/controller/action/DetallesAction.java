/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action;


import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.tablas.*;
import model.dao.*;

/**
 *
 * @author aaron
 */
public class DetallesAction implements IAction{
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {  
            case "LISTAR":
                cadDestino = listar(request, response);
                break;
                
            case "CAMBIAR":
                 cadDestino = cambiar(request, response);
                 break;
                 
            case "NUEVO":
                 cadDestino = añadir(request, response);
                 break;
                 
            case "ELIMINAR":
                cadDestino = eliminar(request, response);
                 break;
        }
        return cadDestino;
        
        
    }
    
    private String listar(HttpServletRequest request, HttpServletResponse response){
        String ejercicio = request.getParameter("ejercicio");
        DAODetalles daodet = new DAODetalles();
        String sRet = "";
        try{
            ArrayList<Detalles> detalles = daodet.listar(ejercicio);
            sRet = Detalles.toArrayJSon(detalles);
        }catch(Exception e){
            System.out.println(e.getMessage()+"");
        }
        
        return sRet;
    }
    
    
    private String cambiar(HttpServletRequest request, HttpServletResponse response){
        String parcela_id = request.getParameter("parcela_id");
        String cultivo = request.getParameter("cultivo");
        int ejercicio = Integer.parseInt(request.getParameter("ejercicio")) - 2000;
        
        DAODetalles daodet = new DAODetalles();
        String sRet = "";
        
        try{
            sRet = daodet.cambiar(parcela_id, cultivo, ejercicio);
            
            

        }catch(Exception e){
            System.out.println(e.getMessage()+"");
        }
        
        return sRet;
    }
    
    private String añadir(HttpServletRequest request, HttpServletResponse response){
        int ejercicio = Integer.parseInt(request.getParameter("ejercicio")) - 2000;
        
        DAODetalles daodet = new DAODetalles();
        String sRet = "";
        
        try{
            sRet = daodet.añadir(ejercicio);
            
            

        }catch(Exception e){
            System.out.println(e.getMessage()+"");
        }
        
        return sRet;
    }
    
    private String eliminar(HttpServletRequest request, HttpServletResponse response){
        int ejercicio = Integer.parseInt(request.getParameter("ejercicio")) - 2000;
        
        DAODetalles daodet = new DAODetalles();
        String sRet = "";
        
        try{
            sRet = daodet.eliminar(ejercicio);
            
            

        }catch(Exception e){
            System.out.println(e.getMessage()+"");
        }
        
        return sRet;
    }
}
