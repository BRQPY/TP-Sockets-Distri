/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JOptionPane;
import java.net.*;
import org.json.JSONObject;
import org.json.JSONArray;
import javax.swing.table.DefaultTableModel;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainClient extends javax.swing.JFrame {
    int puertoServidorTCP = 1565;
    int puertoServidorUDP = 1566;
    /**
     * Creates new form MainClient
     */
    public MainClient() {
        serverIP="127.0.0.1";
        String login_reply;
        initComponents();
        System.setProperty("java.net.preferIPv4Stack" , "true");
        try
        {
            nlist=new ArrayList<>();
            c=new Socket();
            c.connect(new InetSocketAddress(serverIP, puertoServidorTCP),7000);
            while(true){
                username=JOptionPane.showInputDialog(this,"Ingrese su usuario","Usuario",JOptionPane.QUESTION_MESSAGE);
                if (username == null) {
                    JOptionPane.showMessageDialog(null,"Operación cancelada");
                    System.exit(0);
                }
                if (username.isEmpty() != true) {
                    break;
                }
            }
            password=JOptionPane.showInputDialog(this,"Ingrese su contraseña","Usuario",JOptionPane.QUESTION_MESSAGE);
            if (password == null) {
                JOptionPane.showMessageDialog(null,"Operación cancelada");
                System.exit(0);
            }
            brc=new BufferedReader(new InputStreamReader(c.getInputStream()));
            out=new PrintWriter(c.getOutputStream(),true);//auto flush
            json = new JSONObject();
            json.put("tipo_operacion","0");
            json.put("estado","0");
            json.put("mensaje","ok");
            json.put("username",username);
            json.put("password",password);
            out.println(json.toString());
            login_reply=brc.readLine();
            json = new JSONObject(login_reply);
            if(json.get("estado").equals("0")){
               //r=new ReadThread();         
                //r.start();
                setTitle("Usuario: " + username);
                String estado = "0";
                while(estado.equals("0")){
                    String orden=brc.readLine();//espera orden
                    JSONObject ordenJson = new JSONObject(orden);
                    /*
                    if(){
                            ir a funcion para recibir llamada implica aceptar o retornar
                    if orden es realizar llamada
                            ir a funcion de realizar llamada
                      */              
                }
                            
            }else{
                JOptionPane.showMessageDialog(null,json.get("mensaje"));
                System.exit(0);
            }
            clientSocket = new DatagramSocket();
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"No se pudo conectar al servidor: "+e.toString());
            System.exit(0);
        }
    }
        
    public void initCall(){
            String fromUser;
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            r=new ReadThread();
            r.start();
            try{
                while ((fromUser = stdIn.readLine()) != null) {
                    System.out.println("Cliente: " + fromUser);
                    //escribimos al servidor
                    JSONObject salida = new JSONObject();
                    salida.put("estado", "0");
                    salida.put("tipo_operacion", "2");
                    salida.put("destino", "User2");
                    salida.put("mensaje", fromUser);
                    out=new PrintWriter(c.getOutputStream(),true);
                    out.println(salida.toString());
                }
            }catch(Exception e){System.out.println(e);}
    }
        
    
    public void loadList()
    {
        while(namelist.getRowCount()>0)
        {
            ((DefaultTableModel)namelist.getModel()).removeRow(0);
        }
        int row=0;
        for(int x=0;x<nlist.size();x++)
        {
            if(!nlist.get(x).trim().equals("") && !nlist.get(x).trim().equals(username))
            {
                ((DefaultTableModel)namelist.getModel()).addRow(new Object[]{});
                ((DefaultTableModel)namelist.getModel()).setValueAt(nlist.get(x), row, 0);
                 row++;
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        namelist = new javax.swing.JTable();
        getUserConnections = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        call_window = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        display = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        txtmsg = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Client Window[]");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setAlignmentX(3.0F);
        jPanel2.setAlignmentY(3.0F);
        jPanel2.setLayout(new java.awt.BorderLayout());

        namelist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuarios conectados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(namelist);
        if (namelist.getColumnModel().getColumnCount() > 0) {
            namelist.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel2.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        getUserConnections.setText("Ver usuarios conectados");
        getUserConnections.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getUserConnectionsActionPerformed(evt);
            }
        });
        jPanel2.add(getUserConnections, java.awt.BorderLayout.PAGE_START);

        jButton2.setText("Llamar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, java.awt.BorderLayout.LINE_END);

        call_window.setVisible(true);

        display.setEditable(false);
        display.setColumns(20);
        display.setRows(20);
        jScrollPane1.setViewportView(display);

        call_window.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        txtmsg.setBackground(new java.awt.Color(255, 255, 204));
        txtmsg.setColumns(45);
        jPanel1.add(txtmsg);

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton4.setText("Contestar llamada");
        jPanel1.add(jButton4);

        jButton3.setText("Finalizar llamada");
        jPanel1.add(jButton3);

        call_window.getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(call_window, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try
        {
            c.close();
            System.exit(0);
        }catch(Exception e){}
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(!txtmsg.getText().equals(""))
        {
            out.println(txtmsg.getText());
            txtmsg.setText("");
            txtmsg.requestFocus();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void getUserConnectionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getUserConnectionsActionPerformed
        byte[] receiveData = new byte[1024];
        sendDatagramToServer(serverIP,puertoServidorUDP,"0","1","ok");
        try{
            DatagramPacket receivePacket=new DatagramPacket(receiveData, receiveData.length);
            clientSocket.setSoTimeout(10000);
            clientSocket.receive(receivePacket);
            String respuesta = new String(receivePacket.getData());
            json=new JSONObject(respuesta);
            nlist= Arrays.asList(json.getString("dato").substring(1,json.getString("dato").length()-1).split(","));
            System.out.println("LLega1");
            loadList();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }//GEN-LAST:event_getUserConnectionsActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame call_window;
    private javax.swing.JTextArea display;
    private javax.swing.JButton getUserConnections;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable namelist;
    private javax.swing.JTextField txtmsg;
    // End of variables declaration//GEN-END:variables
    private Socket c;
    private DatagramSocket clientSocket;
    private BufferedReader brc;
    private String serverIP,username,password;
    private JSONObject json;
    private ReadThread r;
    private PrintWriter out;
    private List<String> nlist;
    
    public void sendDatagramToServer(String serverIP,int serverPort,String estado,String tipo_operacion,String mensaje){
        try{
            byte[] sendData = new byte[1024];
            json = new JSONObject();
            json.put("estado", estado);
            json.put("tipo_operacion",tipo_operacion);
            json.put("mensaje", mensaje);
            json.put("dato", "");
            String datoPaquete = json.toString();
            sendData = datoPaquete.getBytes();
            InetAddress IPAddress = InetAddress.getByName(serverIP);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, serverPort);
            clientSocket.send(sendPacket);
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
    }
    class ReadThread extends Thread
    {
       public void run()
       {
           String msg;
           try
           {
                System.out.println("LLega porfa");
                while((msg=brc.readLine())!=null)
                {
                    if(msg.equals("f73d5eab4fa29ffd6014aac366cc48de")){
                        JOptionPane.showMessageDialog(null,"El servidor ha finalizado la conexión\nSaliendo...");
                        System.exit(0);

                    }
                    //if termino la llamada
                        //retornar con el valor del estado nuevo
                    try{
                        JSONObject json = new JSONObject(msg);
                        System.out.println(json.toString());
                    }catch(Exception e){System.out.println(e);}
                }
            }catch(Exception e){System.out.println(e);}
        }
    }
}
