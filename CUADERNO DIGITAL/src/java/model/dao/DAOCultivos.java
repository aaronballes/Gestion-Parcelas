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
import model.tablas.Cultivos;
import java.text.DecimalFormat;

/**
 *
 * @author aaron
 */
public class DAOCultivos {

    public ArrayList<Cultivos> listar(String propietario, int ejercicio) throws Exception {
        ArrayList<Cultivos> cultivos = null;
        Cultivos cul;
        Conexion con;
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "select c.cultivo, sum(p.superficie) as superficie_cultivo  "
                + "from detalles d  "
                + "inner join cultivos c on d.cultivo_id = c.cultivo_id "
                + "inner join parcelas p on d.parcela_id = p.parcela_id "
                + "where d.ejercicio_id = " + ejercicio + " and p.propietario_id = " + propietario + " "
                + "group by c.cultivo";

        con = new Conexion();
        try {
            cn = con.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            cultivos = new ArrayList<>();
            while (rs.next() == true) {
                cul = new Cultivos();
                cul.setCultivo(rs.getString("cultivo"));
                cul.setHectareas(rs.getDouble("superficie_cultivo"));
                double porcentaje = (cul.getHectareas() * 100) / hectareasTotales(propietario, ejercicio);
                double porcentajeRedondeado = Math.round(porcentaje * 100.0) / 100.0;
                cul.setPorcentaje(porcentajeRedondeado);

                cultivos.add(cul);
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
        return cultivos;
    }

    public double hectareasTotales(String propietario, int ejercicio) throws Exception {
        double hectareas = 0;
        Cultivos cul;
        Conexion con;
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "select sum(p.superficie) as total_superficie  "
                + "from detalles d  "
                + "inner join cultivos c on d.cultivo_id = c.cultivo_id "
                + "inner join parcelas p on d.parcela_id = p.parcela_id "
                + "where d.ejercicio_id = " + ejercicio + " and p.propietario_id = " + propietario;

        con = new Conexion();
        try {
            cn = con.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next() == true) {
                hectareas = rs.getDouble("total_superficie");
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
        return hectareas;
    }

}
