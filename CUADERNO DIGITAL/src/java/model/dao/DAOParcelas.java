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
import model.tablas.Parcelas;

/**
 *
 * @author aaron
 */
public class DAOParcelas {
    public ArrayList<Parcelas> listar( ) throws Exception {
        ArrayList<Parcelas> parcelas = null;
        Parcelas par;
        Conexion con;
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "select p.parcela_id, p.nombre, p.poligono, p.parcela, p.superficie  "
                + "from parcelas p  "
                + "order by p.propietario_id ,p.poligono, p.parcela";

        con = new Conexion();
        try {
            cn = con.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            parcelas = new ArrayList<>();
            while (rs.next() == true) {
                par = new Parcelas();
                par.setParcela_id(rs.getString("parcela_id"));
                par.setNombre(rs.getString("nombre"));
                par.setPoligono(rs.getString("poligono"));
                par.setParcela(rs.getString("parcela"));
                par.setSuperficie(rs.getString("superficie"));

                parcelas.add(par);
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
        return parcelas;
    }
    
    public String añadir(String nombre, String poligono, String parcela, String superficie) throws Exception {
        String parcelas = "false";
        Parcelas par;
        Conexion con;
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "insert into parcelas (parcela_id, nombre, poligono, parcela, superficie, propietario_id) "
                + "select max(parcela_id)+1, '"+nombre+"', "+poligono+", '"+parcela+"', "+superficie+", 1 " 
                + "from parcelas";

        con = new Conexion();
        try {
            cn = con.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next() == true) {
                parcelas = "true";

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
        return parcelas;
    }
    
    public String eliminar(String parcela_id) throws Exception {
        String parcelas = "false";
        Conexion con;
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "delete from detalles where parcela_id = '"+parcela_id+"'";

        con = new Conexion();
        try {
            cn = con.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            sql = "delete from parcelas where parcela_id = '"+parcela_id+"'";
            rs = st.executeQuery(sql);
            
            
            while (rs.next() == true) {
                parcelas = "true";

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
        return parcelas;
    }
}
