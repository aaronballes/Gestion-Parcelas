package model;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexion {
    private String cadenaConexion = "jdbc:oracle:thin:@localhost:1521/XE";
    
    public Connection conectar() throws Exception{
        Connection cn=null;
        
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            cn = DriverManager.getConnection(cadenaConexion, "c_digital", "1234");
        }catch (Exception e){
            System.out.println(""+e.getMessage());
            throw e;
        }finally{            
        }
        return cn;
    }
}


