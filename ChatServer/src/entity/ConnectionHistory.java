/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.sql.Timestamp;
/**
 *
 * @author majogrance
 */
public class ConnectionHistory {
    String origin;
    String destination;
    Timestamp date_time;
    
    public ConnectionHistory(){
    }
    public ConnectionHistory(String origin, String destination, Timestamp date_time){
        this.origin=origin; 
        this.destination=destination; 
        this.date_time=date_time;
    }
    public void setOrigin(String origin){
       this.origin=origin; 
    }
    public void setDestination(String destination){
       this.destination=destination; 
    }
    public void setDateTime(Timestamp date_time){
       this.date_time=date_time; 
    }
    public String getOrigin(){
        return this.origin;
    }
    public String getDestination(){
        return this.destination;
    }
    public Timestamp getDateTime(){
        return this.date_time;
    }
}
