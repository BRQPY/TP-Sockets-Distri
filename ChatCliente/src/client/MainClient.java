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
import javax.swing.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.*;

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
                r=new ReadThread();
                r.start();
                setTitle("Usuario: " + username); 
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
                ((DefaultTableModel)namelist.getModel()).setValueAt(nlist.get(x).trim(), row, 0);
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
        callClient = new javax.swing.JButton();
        call_window = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        display = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        txtmsg = new javax.swing.JTextField();
        send = new javax.swing.JButton();
        endCall = new javax.swing.JButton();

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

        callClient.setText("Llamar");
        callClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                callClientActionPerformed(evt);
            }
        });
        jPanel2.add(callClient, java.awt.BorderLayout.LINE_END);

        call_window.setVisible(true);

        display.setEditable(false);
        display.setColumns(20);
        display.setRows(20);
        jScrollPane1.setViewportView(display);

        call_window.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        txtmsg.setBackground(new java.awt.Color(255, 255, 204));
        txtmsg.setColumns(55);
        jPanel1.add(txtmsg);

        send.setText("Enviar");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });
        jPanel1.add(send);

        endCall.setText("Finalizar llamada");
        endCall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endCallActionPerformed(evt);
            }
        });
        jPanel1.add(endCall);

        call_window.getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(call_window, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try
        {
            json = new JSONObject();
            json.put("tipo_operacion","4");
            json.put("estado","0");
            json.put("mensaje","ok");
            json.put("dato", "{}");
            out.println(json.toString());
            c.close();
            System.exit(0);
        }catch(Exception e){}
    }//GEN-LAST:event_formWindowClosing

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        if(!txtmsg.getText().equals(""))
        {
            try{
                json = new JSONObject();
                json.put("tipo_operacion","3");
                json.put("estado","0");
                json.put("mensaje","ok");
                json.put("dato", txtmsg.getText());
                out.println(json.toString());
                txtmsg.setText("");
                txtmsg.requestFocus();
            }catch(Exception e){
                
            }
        }
    }//GEN-LAST:event_sendActionPerformed

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
            loadList();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }//GEN-LAST:event_getUserConnectionsActionPerformed

    private void callClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_callClientActionPerformed
        if (namelist.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,"Seleccione un usuario para llamar");
        }else{
            String call_to = String.valueOf(((DefaultTableModel)namelist.getModel()).getValueAt(namelist.getSelectedRow(), 0));
            try{
                json = new JSONObject();
                json.put("tipo_operacion","2");
                json.put("estado","0");
                json.put("mensaje","ok");
                JSONObject dato = new JSONObject();
                dato.put("from_user", username);
                dato.put("to_user",call_to);
                json.put("dato", dato.toString());
                out.println(json.toString());
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
    }//GEN-LAST:event_callClientActionPerformed

    private void endCallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endCallActionPerformed
        try{
            json = new JSONObject();
            json.put("tipo_operacion","4");
            json.put("estado","0");
            json.put("mensaje","ok");
            json.put("dato", "{}");
            out.println(json.toString());
         }catch(Exception e){}
    }//GEN-LAST:event_endCallActionPerformed

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton callClient;
    private javax.swing.JInternalFrame call_window;
    private javax.swing.JTextArea display;
    private javax.swing.JButton endCall;
    private javax.swing.JButton getUserConnections;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable namelist;
    private javax.swing.JButton send;
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
                while((msg=brc.readLine())!=null)
                {
                    if(msg.equals("f73d5eab4fa29ffd6014aac366cc48de")){
                        JOptionPane.showMessageDialog(null,"El servidor ha finalizado la conexión\nSaliendo...");
                        System.exit(0);
                    }
                    try{
                        JSONObject json = new JSONObject(msg);
                        if(json.get("tipo_operacion").equals("2")){
                            //iniciar llamada
                            if (json.get("estado").equals("0")){
                                JSONObject dato = new JSONObject(json.get("dato").toString());
                                if(dato.get("tipo").equals("emisor")){
                                    display.append(dato.get("mensaje").toString()+"\n");
                                }else{
                                    final JOptionPane show_msg = new JOptionPane(dato.get("mensaje").toString(), JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
                                    final JDialog dlg = show_msg.createDialog("Llamada entrante");
                                    dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                                    dlg.addComponentListener(new ComponentAdapter() {
                                        @Override
                                        public void componentShown(ComponentEvent e) {
                                            super.componentShown(e);
                                            final Timer t = new Timer(15000,new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    dlg.setVisible(false);
                                                }
                                            });
                                            t.start();
                                        }
                                    });
                                    dlg.setVisible(true);
                                    String rsp = show_msg.getValue().toString();
                                    if (rsp.equals("0")){
                                        sendMessageToServer(c,"0","5","ok","{}");
                                    }else if (rsp.equals("1")){
                                        sendMessageToServer(c,"1","5","rechazado","{}");
                                    }else{
                                        sendMessageToServer(c,"2","5","no atendido","{}");
                                    }
                                }
                            }else{
                                JOptionPane.showMessageDialog(null,json.get("dato"));
                            }
                        }else if(json.get("tipo_operacion").equals("3")){
                            //conversar
                            if(!json.get("estado").equals("99")){
                                display.append(json.get("dato") + "\n");
                            }else{
                                JOptionPane.showMessageDialog(null,json.get("dato"));
                            }
                        }else if(json.get("tipo_operacion").equals("4")){
                            //terminar llamada
                            if(!json.get("estado").equals("99")){
                                JSONObject dato = new JSONObject(json.get("dato").toString());
                                dato = new JSONObject(json.get("dato").toString());
                                JOptionPane.showMessageDialog(null,dato.get("mensaje"));
                                display.append(dato.get("mensaje") + "\n");
                            }else{
                                JOptionPane.showMessageDialog(null,json.get("dato"));
                            }
                        }else if(json.get("tipo_operacion").equals("5")){
                            display.setText("");
                            JSONObject dato = new JSONObject(json.get("dato").toString());
                            display.append(dato.get("mensaje") + "\n");
                        }
                    }catch(Exception e){System.out.println(e);}
                }
            }catch(Exception e){System.out.println(e);}
        }
        public void sendMessageToServer(Socket c, String estado, String tipo_operacion, String mensaje, String dato){
            try{ 
                json = new JSONObject();
                json.put("estado", estado);
                json.put("tipo_operacion",tipo_operacion);
                json.put("mensaje", mensaje);
                json.put("dato", dato);
                out=new PrintWriter(c.getOutputStream(),true);//auto flush
                out.println(json.toString());
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Error al enviar mensaje al servidor: "+e.getMessage());
            }
       }
    }
}
