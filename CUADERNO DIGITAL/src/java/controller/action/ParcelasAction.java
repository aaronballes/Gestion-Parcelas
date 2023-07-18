/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.DAOParcelas;
import model.tablas.Parcelas;

/**
 *
 * @author aaron
 */
public class ParcelasAction implements IAction{
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {  
            case "LISTAR":
                cadDestino = listar(request, response);
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
        DAOParcelas daopar = new DAOParcelas();
        String sRet = "";
        try{
            ArrayList<Parcelas> parcelas = daopar.listar();
            sRet = Parcelas.toArrayJSon(parcelas);
        }catch(Exception e){
            System.out.println(e.getMessage()+"");
        }
        
        return sRet;
    }
    
    private String añadir(HttpServletRequest request, HttpServletResponse response){
        String nombre  = request.getParameter("nombre");
        String poligono  = request.getParameter("poligono");
        String parcela  = request.getParameter("parcela");
        String superficie  = request.getParameter("parcela");

        
        DAOParcelas daopar = new DAOParcelas();
        String sRet = "";
        
        try{
            sRet = daopar.añadir(nombre, poligono, parcela, superficie);
            
            

        }catch(Exception e){
            System.out.println(e.getMessage()+"");
        }
        
        return sRet;
    }
    
    private String eliminar(HttpServletRequest request, HttpServletResponse response){
        String parcela_id = request.getParameter("parcela_id");
        
        DAOParcelas daopar = new DAOParcelas();
        String sRet = "";
        
        try{
            sRet = daopar.eliminar(parcela_id);
            
            

        }catch(Exception e){
            System.out.println(e.getMessage()+"");
        }
        
        return sRet;
    }
    
}
