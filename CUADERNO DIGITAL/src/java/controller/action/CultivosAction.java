/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.DAOCultivos;
import model.tablas.Cultivos;

/**
 *
 * @author aaron
 */
public class CultivosAction implements IAction{
    
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
        int ejercicio = Integer.parseInt(request.getParameter("ejercicio")) - 2000;
        String propietario = request.getParameter("propietario");

        DAOCultivos daocul = new DAOCultivos();
        String sRet = "";
        try{
            ArrayList<Cultivos> detalles = daocul.listar(propietario, ejercicio);
            sRet = Cultivos.toArrayJSon(detalles);
        }catch(Exception e){
            System.out.println(e.getMessage()+"");
        }
        
        return sRet;
    }
}
