/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import entity.ConnectionHistory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author majogrance
 */
public class ConnectionHistoryDAO {
    public List<ConnectionHistory> selectAll() {
        String query = "SELECT origin, destination, date_time FROM public.connection_history ";
        List<ConnectionHistory> lista = new ArrayList<ConnectionHistory>();
        Connection conn = null; 
        try 
        {
            conn = Bd.connect();
            ResultSet rs = conn.createStatement().executeQuery(query);

            while(rs.next()) {
                ConnectionHistory c = new ConnectionHistory();
                c.setOrigin(rs.getString(1));
                c.setDestination(rs.getString(2));
                c.setDateTime(rs.getTimestamp(3));
                lista.add(c);
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
    
    public long createConnectionHistory(ConnectionHistory c) throws SQLException {
        String SQL = "INSERT INTO public.connection_history(origin,destination,date_time) "
                + "VALUES(?,?,?)";
 
        long id = 0;
        Connection conn = null;
        try 
        {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, c.getOrigin());
            pstmt.setString(2, c.getDestination());
            pstmt.setTimestamp(3, c.getDateTime());
            int affectedRows = pstmt.executeUpdate();
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

}
