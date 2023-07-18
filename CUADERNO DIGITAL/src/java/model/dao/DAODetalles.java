/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.Conexion;
import model.tablas.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aaron
 */
public class DAODetalles {

    public ArrayList<Detalles> listar(String ejercicio) throws Exception {
        ArrayList<Detalles> detalles = null;
        Detalles det;
        Conexion con;
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "select p.parcela_id, p.nombre, p.poligono, p.parcela, p.superficie, c.cultivo  "
                + "from detalles d  "
                + " inner join parcelas p on d.parcela_id = p.parcela_id "
                + " inner join cultivos c on c.cultivo_id = d.cultivo_id "
                + " inner join ejercicios e on e.ejercicio_id = d.ejercicio_id "
                + "where ejercicio = '" + ejercicio
                + "' order by p.propietario_id, p.poligono, p.parcela";

        con = new Conexion();
        try {
            cn = con.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            detalles = new ArrayList<>();
            while (rs.next() == true) {
                det = new Detalles();
                det.setParcela_id(rs.getString("parcela_id"));
                det.setNombre(rs.getString("nombre"));
                det.setPoligono(rs.getString("poligono"));
                det.setParcela(rs.getString("parcela"));
                det.setSuperficie(rs.getString("superficie"));
                det.setCultivo(rs.getString("cultivo"));

                detalles.add(det);
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
        return detalles;
    }

    public String cambiar(String parcela_id, String cultivo, int ejercicio) throws Exception {
        String detalles = "false";
        Detalles det;
        Conexion con;
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "select cultivo_id "
                + "from cultivos   "
                + "where cultivo = '" + cultivo +"'";

        con = new Conexion();
        try {
            cn = con.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next() == true) {
                String cultivo_id = rs.getString("cultivo_id");

                sql = "UPDATE detalles SET cultivo_id = '"
                        + cultivo_id
                        + "' WHERE parcela_id = '" + parcela_id+"' and ejercicio_id = '"+ejercicio+"'";

                rs = st.executeQuery(sql);

                sql = "SELECT cultivo_id "
                        + " FROM detalles "
                        + " WHERE(ejercicio_id = '" + (ejercicio - 2) + "' OR ejercicio_id = '" + (ejercicio - 1) + "' OR ejercicio_id = '" + (ejercicio) + "') "
                        + " AND parcela_id = '" + parcela_id + "' "
                        + " GROUP BY cultivo_id "
                        + " HAVING COUNT(DISTINCT cultivo_id) = 1 ";

                rs = st.executeQuery(sql);

                int rowCount = 0;
                
                while (rs.next() == true){
                    rowCount++;
                }
                
                if(rowCount == 1){
                    detalles = "iguales";
                }else{
                    detalles = "true";
                }

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
        return detalles;
    }
    
    public String añadir(int ejercicio) throws Exception {
        String detalles = "false";
        Detalles det;
        Conexion con;
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "insert into detalles (ejercicio_id, cultivo_id, parcela_id ) "
                + "select "+ejercicio+", 0, parcela_id "
                + "from parcelas";

        con = new Conexion();
        try {
            cn = con.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next() == true) {
                detalles = "true";

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
        return detalles;
    }
    
    public String eliminar(int ejercicio) throws Exception {
        String detalles = "false";
        Detalles det;
        Conexion con;
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "delete from detalles where ejercicio_id = '"+ejercicio+"'";

        con = new Conexion();
        try {
            cn = con.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next() == true) {
                detalles = "true";

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
        return detalles;
    }
}
