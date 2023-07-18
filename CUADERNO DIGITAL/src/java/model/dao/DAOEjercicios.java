/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Conexion;
import model.tablas.Ejercicios;

/**
 *
 * @author aaron
 */
public class DAOEjercicios {
    public ArrayList<Ejercicios> listar( ) throws Exception {
        ArrayList<Ejercicios> ejercicios = null;
        Ejercicios ejer;
        Conexion con;
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "select distinct e.ejercicio, d.ejercicio_id  "
                + "from ejercicios e inner join detalles d on d.ejercicio_id = e.ejercicio_id "
                + "order by e.ejercicio";

        con = new Conexion();
        try {
            cn = con.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            ejercicios = new ArrayList<>();
            while (rs.next() == true) {
                ejer = new Ejercicios();
                ejer.setEjercicio_id(rs.getString("ejercicio_id"));
                ejer.setEjercicio(rs.getString("ejercicio"));

                ejercicios.add(ejer);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            rs = null;
            if (st != null && st.isClosed() == false) {
                st.close();
            }
            st = null;
            if (cn != null && cn.isClosed() == false) {
                cn.close();
            }
            cn = null;
        }
        return ejercicios;
    }
}
