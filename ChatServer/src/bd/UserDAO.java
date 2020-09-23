/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class UserDAO {
    public List<User> selectAll() {
        String query = "SELECT id, username, password FROM public.user ";
        List<User> lista = new ArrayList<User>();
        Connection conn = null; 
        try 
        {
            conn = Bd.connect();
            ResultSet rs = conn.createStatement().executeQuery(query);

            while(rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                lista.add(u);
            }
        } catch (SQLException ex) {
            System.out.println("Error en la seleccion: " + ex.getMessage());
        }
        finally  {
            try{
                    conn.close();
            }catch(Exception ef){
                    System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
            }
        }
        return lista;
    }

    public List<User> selectUsers(List<String> usernames) {
        String SQL = "SELECT id, username, password FROM public.user WHERE username in(?)";
        List<User> list = new ArrayList<User>();
        Connection conn = null;
        String string = new String();
        String sep = new String();
        for(int i =0;i<usernames.size();i++){
            string += sep + "'" + usernames.get(i) + "'";
            sep = ",";
        }
        try 
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, string);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                list.add(u);
            }    
        } catch (SQLException ex) {
            System.out.println("Error en la seleccion: " + ex.getMessage());
        }
        finally  {
            try{
                conn.close();
            }catch(Exception ef){
                System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
            }
        }
        return list;
    }
    
    public boolean login(String username, String password){
        String SQL = "SELECT id, username, password FROM public.user WHERE username = ? and password = ?";
        Connection conn = null;
        try 
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error en el login: " + ex.getMessage());
        }
        finally  {
            try{
                conn.close();
            }catch(Exception ef){
                JOptionPane.showMessageDialog(null,"No se pudo cerrar la conexion a BD: "+ ef.getMessage());
            }
        }
        return false;
    }
    
    public long createUser(User u) throws SQLException {
        if (u.getUsername().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Ingrese un usuario");
            return -1;
        }
        String SQL = "INSERT INTO public.user(id,username,password) "
                + "VALUES(?,?,?)";
 
        long id = 0;
        Connection conn = null;
        try 
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, getNextId());
            pstmt.setString(2, u.getUsername());
            pstmt.setString(3, u.getPassword());
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null,"Usuario creado: " + u.getUsername());
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error en la insercion: " + ex.getMessage());
        }
        finally  {
            try{
                    conn.close();
            }catch(Exception ef){
                JOptionPane.showMessageDialog(null,"No se pudo cerrar la conexion a BD: "+ ef.getMessage());
            }
        }	
        return id; 	
    }
    
    public long updateUser(User u) throws SQLException {

        String SQL = "UPDATE persona SET username = ? , password = ? WHERE id = ? ";
 
        int id = 0;
        Connection conn = null;
        try 
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, u.getId());
            pstmt.setString(2, u.getUsername());
            pstmt.setString(3, u.getPassword());
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la actualizacion: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
        return id;
    }
    
    public long borrar(User u) throws SQLException {
        String SQL = "DELETE FROM persona WHERE id = ? ";
        long id = 0;
        Connection conn = null;
        try 
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, u.getId());
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la eliminación: " + ex.getMessage());
        }
        finally  {
            try{
                    conn.close();
            }catch(Exception ef){
                    System.out.println("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
            }
        }
        return id;
    }
    public int getNextId(){
        String SQL = "SELECT MAX(id) FROM public.user";
        Connection conn = null;
        try 
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                return rs.getInt(1) + 1;
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error en la asignacion de id de usuario: " + ex.getMessage());
        }
        finally  {
            try{
                conn.close();
            }catch(Exception ef){
                JOptionPane.showMessageDialog(null,"No se pudo cerrar la conexion a BD: "+ ef.getMessage());
            }
        }
        return 0;
    }
}
