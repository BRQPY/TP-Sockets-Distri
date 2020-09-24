/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.json.JSONObject;
import bd.UserDAO;
import entity.User;

public class MainServer extends javax.swing.JFrame {
    int puertoServidorTCP = 1565;
    int puertoServidorUDP = 1566;
    /**
     * Creates new form MainServer
     */
    public MainServer() {
        initComponents();
        System.setProperty("java.net.preferIPv4Stack" , "true");
        try
        {
            s=new ServerSocket(puertoServidorTCP);
            slist=new ArrayList<>();
            nlist=new ArrayList<>();
            unlist=new ArrayList<>();
            h=new HandleClientTCP();
            h.start();
            d=new DatagramSocket(puertoServidorUDP);
            t=new HandleClientUDP();
            t.start();
        }catch(Exception e){}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        txtcmd = new javax.swing.JTextField();
        send = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cid = new javax.swing.JTextField();
        listComands = new javax.swing.JButton();
        sendToAll = new javax.swing.JCheckBox();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        namelist = new javax.swing.JTable();
        jInternalFrame3 = new javax.swing.JInternalFrame();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        username_create = new javax.swing.JTextField();
        password_create = new javax.swing.JTextField();
        createUser = new javax.swing.JButton();
        jInternalFrame4 = new javax.swing.JInternalFrame();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        username_modify = new javax.swing.JTextField();
        password_modify = new javax.swing.JTextField();
        modifyUser = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Servidor de llamadas");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jInternalFrame1.setTitle("Enviar comandos a usuarios");
        jInternalFrame1.setVisible(true);

        txtcmd.setBackground(new java.awt.Color(255, 255, 204));

        send.setText("Enviar");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });

        jLabel1.setText("Enviar a");

        cid.setEditable(false);
        cid.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        listComands.setText("Ver comandos");
        listComands.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listComandsActionPerformed(evt);
            }
        });

        sendToAll.setText("Enviar a todos");

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcmd, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cid, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(sendToAll)
                        .addGap(80, 80, 80)
                        .addComponent(send)
                        .addGap(18, 18, 18)
                        .addComponent(listComands)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtcmd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(send)
                    .addComponent(listComands)
                    .addComponent(sendToAll))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jInternalFrame2.setTitle("Usuarios conectados");
        jInternalFrame2.setVisible(true);

        namelist.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        namelist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuarios", "Estado", "Llamanda a"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        namelist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                namelistMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(namelist);
        if (namelist.getColumnModel().getColumnCount() > 0) {
            namelist.getColumnModel().getColumn(0).setResizable(false);
            namelist.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
        );

        jInternalFrame3.setTitle("Crear usuario");
        jInternalFrame3.setVisible(true);

        jLabel2.setText("Usuario");

        jLabel3.setText("Contraseña");

        username_create.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        password_create.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        createUser.setText("Crear");
        createUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame3Layout = new javax.swing.GroupLayout(jInternalFrame3.getContentPane());
        jInternalFrame3.getContentPane().setLayout(jInternalFrame3Layout);
        jInternalFrame3Layout.setHorizontalGroup(
            jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame3Layout.createSequentialGroup()
                .addGroup(jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jInternalFrame3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(username_create, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jInternalFrame3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(password_create))
                    .addGroup(jInternalFrame3Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(createUser)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jInternalFrame3Layout.setVerticalGroup(
            jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username_create, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password_create, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(createUser)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jInternalFrame4.setTitle("Modificar contraseña de usuario");
        jInternalFrame4.setVisible(true);

        jLabel4.setText("Usuario");

        jLabel5.setText("Nueva contraseña");

        username_modify.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        password_modify.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        modifyUser.setText("Modificar");

        javax.swing.GroupLayout jInternalFrame4Layout = new javax.swing.GroupLayout(jInternalFrame4.getContentPane());
        jInternalFrame4.getContentPane().setLayout(jInternalFrame4Layout);
        jInternalFrame4Layout.setHorizontalGroup(
            jInternalFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jInternalFrame4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(username_modify))
                    .addGroup(jInternalFrame4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jInternalFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jInternalFrame4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(modifyUser))
                            .addComponent(password_modify, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jInternalFrame4Layout.setVerticalGroup(
            jInternalFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username_modify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInternalFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(password_modify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(modifyUser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jInternalFrame1)
                    .addComponent(jInternalFrame2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jInternalFrame3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jInternalFrame4)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jInternalFrame3)
                    .addComponent(jInternalFrame4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jInternalFrame2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public class UserConnected{
        String username;
        String status;
        String calling;
        UserConnected(String username, String status, String calling){
            this.username = username;
            this.status = status;
            this.calling = calling;
        }
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            
            JOptionPane.showMessageDialog(null,"Cerrando el servidor...");
            Socket client_close;
            PrintWriter end;
            for(int x=0;x<slist.size();x++)
            {
                try{
                    client_close=slist.get(x);
                    end=new PrintWriter(client_close.getOutputStream(),true);
                    end.println("f73d5eab4fa29ffd6014aac366cc48de");
                }catch(Exception e){}
            }
            s.close();
            System.exit(0);
            
        } catch (IOException ex) {
            Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void namelistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_namelistMouseClicked
        String data=String.valueOf(((DefaultTableModel)namelist.getModel()).getValueAt(namelist.getSelectedRow(), 0));
        cid.setText(data);
    }//GEN-LAST:event_namelistMouseClicked

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        if(txtcmd.getText().equals("")){
            //no hay texto
            return;
        }
        if((!cid.getText().equals("") || sendToAll.isSelected()))
        {
            ArrayList options = new ArrayList<String>();
            options.add("desconectar");
            options.add("terminar_llamada");
            if(!(options.indexOf(txtcmd.getText())>=0)){
                JOptionPane.showMessageDialog(null,"Comando inválido");
            }
            if (sendToAll.isSelected()){
                //enviar a todos
                try{
                    for(int i=0;i<slist.size();i++)
                    {
                        out=new PrintWriter(slist.get(i).getOutputStream(),true);
                        if(txtcmd.getText().equals("desconectar")){
                            out.println("f73d5eab4fa29ffd6014aac366cc48d1");
                        }else if(txtcmd.getText().equals("terminar_llamada")){
                            json = new JSONObject();
                            json.put("estado", "0");
                            json.put("tipo_operacion", "6");
                            json.put("mensaje", "ok");
                            json.put("dato", "terminar_llamada");
                            out.println(json.toString());
                        }
                    }
                }catch(Exception e){}
            }else{
                //enviar a uno
                if (getPositionOfClient(cid.getText())!=-1){
                    Socket send_to = slist.get(getPositionOfClient(cid.getText()));
                    try{
                        out=new PrintWriter(send_to.getOutputStream(),true);
                        if(txtcmd.getText().equals("desconectar")){
                            out.println("f73d5eab4fa29ffd6014aac366cc48d1");
                        }else if(txtcmd.getText().equals("terminar_llamada")){
                            json = new JSONObject();
                            json.put("estado", "0");
                            json.put("tipo_operacion", "6");
                            json.put("mensaje", "ok");
                            json.put("dato", "terminar_llamada");
                            out.println(json.toString());
                        }
                    }catch(Exception e){}
                    
                }else{
                    
                } 
            }
        }else{
            //usuario no seleccionado
            JOptionPane.showMessageDialog(null,"Seleccione un usuario");
        }
        txtcmd.setText("");
        cid.setText("");
        txtcmd.requestFocus();
    }//GEN-LAST:event_sendActionPerformed

    private void createUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createUserActionPerformed
        User u=new User(username_create.getText(),password_create.getText());
        UserDAO udao=new UserDAO();
        try{
           udao.createUser(u); 
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
    }//GEN-LAST:event_createUserActionPerformed

    private void listComandsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listComandsActionPerformed
        String text = new String();
        text += "desconectar\n";
        text += "terminar_llamada";
        JOptionPane.showMessageDialog(null,text);
    }//GEN-LAST:event_listComandsActionPerformed

    /**
     * @param args the command line arguments
     */
    private ServerSocket s;
    private Socket c;
    private DatagramSocket d;
    private String username;
    private String password;
    private ArrayList<Socket> slist;
    private ArrayList<UserConnected> nlist;
    private ArrayList<String> unlist;
    private BufferedReader brc;
    private PrintWriter out;
    private HandleClientTCP h;
    private HandleClientUDP t;
    private JSONObject json;
    private boolean a; 
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cid;
    private javax.swing.JButton createUser;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JInternalFrame jInternalFrame3;
    private javax.swing.JInternalFrame jInternalFrame4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton listComands;
    private javax.swing.JButton modifyUser;
    private javax.swing.JTable namelist;
    private javax.swing.JTextField password_create;
    private javax.swing.JTextField password_modify;
    private javax.swing.JButton send;
    private javax.swing.JCheckBox sendToAll;
    private javax.swing.JTextField txtcmd;
    private javax.swing.JTextField username_create;
    private javax.swing.JTextField username_modify;
    // End of variables declaration//GEN-END:variables
    
    public void getCallHistoryList(){
    }
    public void getCallHistory(){
    }
    public void getConnectionsHistory(){
    }
    public void loadList(){
        while(namelist.getRowCount()>0)
        {
            ((DefaultTableModel)namelist.getModel()).removeRow(0);
        }
        int row=0;
        for(int x=0;x<nlist.size();x++)
        {
            UserConnected u = nlist.get(x);
            ((DefaultTableModel)namelist.getModel()).addRow(new Object[]{});
            ((DefaultTableModel)namelist.getModel()).setValueAt(u.username, row, 0);
            ((DefaultTableModel)namelist.getModel()).setValueAt(u.status, row, 1);
            ((DefaultTableModel)namelist.getModel()).setValueAt(u.calling, row, 2);
            row++;
        }
    }
    public int getPositionOfClient(String username){
        for(int i=0;i<unlist.size();i++){
            if(unlist.get(i).equals(username)){
                return i;
            }
        }
        return -1;
    }
    class HandleClientTCP extends Thread
    {
        private int tid;
        public void run()
        {
            while(true)
            {
                try {
                    c=s.accept();
                    brc=new BufferedReader(new InputStreamReader(c.getInputStream()));
                    JSONObject json = new JSONObject(brc.readLine());
                    username=json.getString("username").trim();
                    password=json.getString("password");
                    loginUser(username,password);
                    sendMessageToClient(c, "0", "0", "ok", "");
                    slist.add(c);
                    unlist.add(username);
                    nlist.add(new UserConnected(username, "Libre",""));
                    loadList();
                    ProcessClient t=new ProcessClient(tid, username, c, password);
                    t.start();
                    tid++;
                } catch (Exception e) {
                    sendMessageToClient(c, "1", "0", "1", e.getMessage());
                }

            }
        }
        class ProcessClient extends Thread
        {
            private int id;
            private String username;
            private String password;
            private Socket c;
            private BufferedReader brc;
            public ProcessClient(int id,String username,Socket c, String password)
            {
                this.id=id;
                this.username=username;
                this.c=c;
                this.password=password;
            }
            public void run()
            {
                String msg;
                try{
                    brc=new BufferedReader(new InputStreamReader(c.getInputStream()));
                    loadList();
                    while((msg=brc.readLine())!=null)
                    {
                        json=new JSONObject(msg);
                        if (json.get("tipo_operacion").equals("2")){
                            //iniciar llamada
                            JSONObject dato = new JSONObject(json.get("dato").toString());
                            try{
                                initCall(c,dato.get("from_user").toString(),dato.get("to_user").toString());
                            }catch(Exception e){
                                sendMessageToClient(c,"4","2","",e.getMessage());
                            }
                        } else if (json.get("tipo_operacion").equals("3")) {
                            //conversar
                            Socket to_socket = new Socket();
                            String to_user = new String();
                            if (getPositionOfClient(username)!=-1){
                                to_user = nlist.get(getPositionOfClient(username)).calling;
                                if (getPositionOfClient(to_user)!=-1){
                                    to_socket = slist.get(getPositionOfClient(to_user));
                                    sendMessageUserToUser(c,to_socket,username,to_user,"["+username+"]: "+json.get("dato"));
                                }else{
                                    sendMessageToClient(c,"99","3","no existe llamada","no se encuentra en una llamada");
                                }
                            }
                        } else if (json.get("tipo_operacion").equals("4")) {
                            //terminar llamada
                            Socket to_socket = new Socket();
                            String to_user = new String();
                            if (getPositionOfClient(username)!=-1){
                                to_user = nlist.get(getPositionOfClient(username)).calling;
                                if (getPositionOfClient(to_user)!=-1){
                                    to_socket = slist.get(getPositionOfClient(to_user));
                                    if(json.get("estado").equals("0")){ 
                                        endCall(c,to_socket,username,to_user,"0");  
                                    }else{
                                        endCall(c,to_socket,username,to_user,"3");  
                                    }
                                }else{
                                    if(!json.get("estado").equals("3")){
                                        sendMessageToClient(c,"99","3","no existe llamada","no se encuentra en una llamada");
                                    }
                                }
                            }
                        } else if (json.get("tipo_operacion").equals("5")) {
                            //contestar llamada
                            Socket to_socket = new Socket();
                            String to_user = new String();
                            if (getPositionOfClient(username)!=-1){
                                to_user = nlist.get(getPositionOfClient(username)).calling;
                                if (getPositionOfClient(to_user)!=-1){
                                    to_socket = slist.get(getPositionOfClient(to_user));
                                }
                            }
                            if (json.get("estado").equals("0")){
                                //llamada contestada
                                answerCall(username,to_user,c,to_socket);
                            }else if(json.get("estado").equals("1")){
                                //llamada rechazada
                                endCall(c,to_socket,username,to_user,"1");  
                            }else if(json.get("estado").equals("2")){
                                //llamada no contestada
                                endCall(c,to_socket,username,to_user,"2");
                            }
                        } 
                    }
                    System.out.println("poronga");
                    removeClient(getPositionOfClient(username));
                    loadList();
                }catch(Exception e){sendMessageToClient(c,"4","2","",e.getMessage());}
            }
        }
        public void removeClient(int i){
            unlist.remove(i);
            nlist.remove(i);
            slist.remove(i);
        }
        public void answerCall(String from_user,String to_user,Socket from_socket,Socket to_socket){
            try{
                nlist.get(getPositionOfClient(from_user)).status = "Ocupado";
                nlist.get(getPositionOfClient(to_user)).status = "Ocupado";
                loadList();
                JSONObject dato = new JSONObject();
                dato.put("mensaje", "Llamada iniciada con "+to_user);
                sendMessageToClient(from_socket,"0","5","ok",dato.toString());
                dato = new JSONObject();
                dato.put("mensaje","Llamada iniciada con "+from_user);
                sendMessageToClient(to_socket,"0","5","ok",dato.toString());
            }catch(Exception e){}
        }
        public void endCall(Socket from_socket,Socket to_socket,String from_user,String to_user,String code){
            JSONObject dato;
            try{
                nlist.get(getPositionOfClient(from_user)).status="Libre";
                nlist.get(getPositionOfClient(from_user)).calling="";
                nlist.get(getPositionOfClient(to_user)).status="Libre";
                nlist.get(getPositionOfClient(to_user)).calling="";
                if(code.equals("0")){
                    dato = new JSONObject();
                    dato.put("mensaje","Llamada finalizada");
                    sendMessageToClient(to_socket,"0","4","ok",dato.toString());
                    sendMessageToClient(from_socket,"0","4","ok",dato.toString());
                    
                }else if(code.equals("1")){
                    dato = new JSONObject();
                    dato.put("mensaje","Llamada rechazada");
                    sendMessageToClient(to_socket,"1","4","rechazado",dato.toString());
                }else if(code.equals("2")){
                    dato = new JSONObject();
                    dato.put("mensaje","Llamada no contestada");
                    sendMessageToClient(to_socket,"2","4","no contestado",dato.toString());
                }else if(code.equals("3")){
                    dato = new JSONObject();
                    dato.put("mensaje","Llamada finalizada por el servidor");
                    sendMessageToClient(to_socket,"3","4","ok",dato.toString());
                    sendMessageToClient(from_socket,"3","4","ok",dato.toString());
                }
                loadList();
            }catch(Exception e){sendMessageToClient(to_socket,"3","4","error",e.getMessage());}
        }
        public void sendMessageToClient(Socket c, String estado, String tipo_operacion, String mensaje, String dato){
            try{
                json = new JSONObject();
                json.put("estado", estado);
                json.put("tipo_operacion", tipo_operacion);
                json.put("mensaje", mensaje);
                json.put("dato", dato);
                out=new PrintWriter(c.getOutputStream(),true);
                out.println(json.toString());
            }catch(Exception e){}
        }
        public void loginUser(String username, String password) throws Exception{
            UserDAO udao = new UserDAO();
            if (udao.login(username, password)){
                return;
            }
            throw new Exception("El usuario o la contraseña no son válidos");
        }
        public void initCall(Socket c, String from_user, String to_user) throws Exception{
            Socket to_socket = new Socket();
            for(int i=0;i<unlist.size();i++){
                if(unlist.get(i).equals(from_user)){
                    if (!nlist.get(i).status.equals("Libre")){
                        throw new Exception("Usted ya se encuentra en una llamada");
                    }else{
                        nlist.get(i).status = "Llamando";
                        nlist.get(i).calling = to_user;
                    }
                }
                if(unlist.get(i).equals(to_user)){
                    if (!nlist.get(i).status.equals("Libre")){
                        throw new Exception("Usuario "+to_user+" ocupado");
                    }else{
                         nlist.get(i).status = "Llamada entrante";
                         nlist.get(i).calling = from_user;
                         to_socket = slist.get(i);
                    }
                }
            }
            JSONObject dato = new JSONObject();
            dato.put("tipo","emisor");;
            dato.put("mensaje","Llamando a "+to_user+"...");
            sendMessageToClient(c,"0","2","ok",dato.toString());
            dato = new JSONObject();
            dato.put("tipo","receptor");
            dato.put("mensaje","Llamada entrante de "+from_user+"...");
            sendMessageToClient(to_socket,"0","2","ok",dato.toString());
            loadList();
        }
        public void sendMessageUserToUser(Socket from_s,Socket to_s,String from_user,String to_user,String message){
            try{
                String calling = nlist.get(getPositionOfClient(from_user)).calling;
                json = new JSONObject();
                json.put("estado", "0");
                json.put("tipo_operacion", "3");
                json.put("mensaje", "ok");
                json.put("dato", message);
                out=new PrintWriter(from_s.getOutputStream(),true);
                out.println(json.toString());
                out=new PrintWriter(to_s.getOutputStream(),true);
                out.println(json.toString());
            }catch(Exception e){}
        }
    }
    
    class HandleClientUDP extends Thread
    {
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        public void run(){
            while (true) {
                try{
                    receiveData = new byte[1024];
                    DatagramPacket receivePacket=new DatagramPacket(receiveData, receiveData.length);
                    d.receive(receivePacket);
                    String datoRecibido = new String(receivePacket.getData());
                    json=new JSONObject(datoRecibido);
                    if (json.get("tipo_operacion").equals("1")){
                        InetAddress IPAddress = receivePacket.getAddress();
                        int port = receivePacket.getPort();
                        json = new JSONObject();
                        System.out.println(unlist);
                        sendDatagramToUser(IPAddress, port,"0","1", unlist.toString());
                    }
                }catch(Exception e){JOptionPane.showMessageDialog(null,e.getMessage());}      
            }
        }
        public void sendDatagramToUser(InetAddress IPAddress, int port, String estado, String tipo_operacion, String dato){
            try{
                json = new JSONObject();
                json.put("estado", estado);
                json.put("tipo_operacion",tipo_operacion);
                json.put("mensaje","ok");
                json.put("dato",dato);
                sendData = json.toString().getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);
                d.send(sendPacket);
            }catch(Exception e){}
        }
    }
}
