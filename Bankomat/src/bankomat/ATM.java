package bankomat;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Integer.parseInt;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asija
 */
public class ATM extends javax.swing.JFrame {

    /**
     * Creates new form ATM
     */
      
    private static String serverName="localhost";
    private static int serverPort=8100;
    private static Socket socket = null;
    private static ObjectOutputStream oos = null;
    private static ObjectInputStream ois=null;
      Korisnik k = new Korisnik();
      
    public  void connect() {
    try{
        socket=new Socket(serverName, serverPort);
        System.out.println("Konektovani ste na server "+socket.getRemoteSocketAddress());
        oos=new ObjectOutputStream(socket.getOutputStream());
        ois=new ObjectInputStream(socket.getInputStream());
        this.prikazii();
    }
    catch(Exception e){System.out.print("Greska:"+e);}
    }
    void disconnect(){
    try{
        oos.close();
        socket.close();
        }
    catch(Exception e){System.out.print("Greska:"+e);}
    } 
    
    public void prikazii() throws IOException, ClassNotFoundException
    {   
        String brRacuna = "8880";
        System.out.println("poceli smo");
        oos.writeObject(brRacuna);
        k.setIme((String)ois.readObject());
        k.setPrezime((String)ois.readObject());
        k.setBrRacuna((String)ois.readObject());
        k.setStanje((Integer)ois.readObject());
        this.lblIme.setText(k.getIme());
        this.lblPrezime.setText(k.getPrezime());
        this.lblAcc.setText(k.getBrRacuna());
        this.lblStanjeRacuna.setText(String.valueOf(k.getStanje()));
      
    }
    public void isplatiPrikaz() throws IOException 
        {
            this.pnlIsplata.setVisible(true);
            String poruka="isplata";
            try { 
                 oos.writeObject(poruka); 
                 oos.flush();
                } 
           catch (IOException ex) { Logger.getLogger(ATM.class.getName()).log(Level.SEVERE, null, ex); }
        }
    public void uplatiPrikaz() throws IOException 
        {
            this.pnlUplata.setVisible(true);
            String poruka="uplata";
            try { 
                 oos.writeObject(poruka);
                 oos.flush();
                }
           catch (IOException ex) {Logger.getLogger(ATM.class.getName()).log(Level.SEVERE, null, ex); }
       }
    public void isplatiSacuvaj() throws IOException 
    {
        String up=this.txtIsplata.getText();
        int isplata=(Integer.parseInt(up));
        try {   oos.writeObject(isplata); oos.flush();} 
        catch (IOException ex) {Logger.getLogger(ATM.class.getName()).log(Level.SEVERE, null, ex); }
        System.out.println("poslao sam "+isplata);
        try {
               int novoStanje=(int) ois.readObject();
               this.txtIsplata.setText("");
               this.lblStanjeRacuna.setText(String.valueOf(novoStanje));
               String p=(String) ois.readObject();
               this.lblPoruka.setText(p);
               this.pnlIsplata.setVisible(false);
            } 
        catch (IOException ex) { Logger.getLogger(ATM.class.getName()).log(Level.SEVERE, null, ex);}
        catch (ClassNotFoundException ex) {Logger.getLogger(ATM.class.getName()).log(Level.SEVERE, null, ex); } 
    }
     public void uplatiSacuvaj() throws IOException 
    {
        String up=this.txtUplata.getText();
        int uplata=(Integer.parseInt(up));
        try {   oos.writeObject(uplata); oos.flush();} 
        catch (IOException ex) {Logger.getLogger(ATM.class.getName()).log(Level.SEVERE, null, ex); }
        System.out.println("poslao sam "+uplata);
        try {
               int novoStanje=(int) ois.readObject();
               this.txtUplata.setText("");
               this.lblStanjeRacuna.setText(String.valueOf(novoStanje));
               String p=(String) ois.readObject();
               this.lblPoruka.setText(p);
               this.pnlUplata.setVisible(false);
            } 
        catch (IOException ex) { Logger.getLogger(ATM.class.getName()).log(Level.SEVERE, null, ex);}
        catch (ClassNotFoundException ex) {Logger.getLogger(ATM.class.getName()).log(Level.SEVERE, null, ex); } 
    }
    public ATM() throws IOException, ClassNotFoundException {
        initComponents();
        this.pnlIsplata.setVisible(false);
        this.pnlUplata.setVisible(false);
       this.connect();
        this.setVisible(true);
        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnIsplataJedan = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        lblKorisnik = new javax.swing.JLabel();
        lblStanje = new javax.swing.JLabel();
        lbBrojKartice = new javax.swing.JLabel();
        txtStanje = new javax.swing.JLabel();
        lblIme = new javax.swing.JLabel();
        lblPrezime = new javax.swing.JLabel();
        lblBrRacuna = new javax.swing.JLabel();
        lblStanjeRac = new javax.swing.JLabel();
        lblStanjeRacuna = new javax.swing.JLabel();
        lblAcc = new javax.swing.JLabel();
        pnlUplata = new javax.swing.JPanel();
        txtUplata = new javax.swing.JTextField();
        btnUplatiSacuvaj = new javax.swing.JButton();
        btnUplataJedan = new javax.swing.JButton();
        pnlIsplata = new javax.swing.JPanel();
        txtIsplata = new javax.swing.JTextField();
        btnIsplatiSacuvaj = new javax.swing.JButton();
        lblPoruka = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnIsplataJedan.setText("Isplata novca");
        btnIsplataJedan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIsplataJedanActionPerformed(evt);
            }
        });
        jPanel2.add(btnIsplataJedan, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 110, 40));

        btnLogout.setText("Odjava");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel2.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 80, 40));

        lblKorisnik.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblKorisnik.setText("Korisnik:");
        jPanel2.add(lblKorisnik, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        lblStanje.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblStanje.setText("Stanje na racunu:");
        jPanel2.add(lblStanje, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        lbBrojKartice.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbBrojKartice.setText("Broj Kartice");
        jPanel2.add(lbBrojKartice, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        txtStanje.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(txtStanje, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, -1, -1));

        lblIme.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(lblIme, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, -1));

        lblPrezime.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(lblPrezime, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, -1, -1));

        lblBrRacuna.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(lblBrRacuna, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, -1, -1));

        lblStanjeRac.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(lblStanjeRac, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, -1, -1));

        lblStanjeRacuna.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(lblStanjeRacuna, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, -1, -1));

        lblAcc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(lblAcc, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, -1, -1));

        pnlUplata.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUplata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUplataActionPerformed(evt);
            }
        });
        pnlUplata.add(txtUplata, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 120, 30));

        btnUplatiSacuvaj.setText("Sacuvaj");
        btnUplatiSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUplatiSacuvajActionPerformed(evt);
            }
        });
        pnlUplata.add(btnUplatiSacuvaj, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jPanel2.add(pnlUplata, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, 180, 130));

        btnUplataJedan.setText("Uplata novca");
        btnUplataJedan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUplataJedanActionPerformed(evt);
            }
        });
        jPanel2.add(btnUplataJedan, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 120, 40));

        pnlIsplata.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlIsplata.add(txtIsplata, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 13, 120, 30));

        btnIsplatiSacuvaj.setText("Sacuvaj");
        btnIsplatiSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIsplatiSacuvajActionPerformed(evt);
            }
        });
        pnlIsplata.add(btnIsplatiSacuvaj, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 61, -1, -1));

        jPanel2.add(pnlIsplata, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 180, 130));

        lblPoruka.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(lblPoruka, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIsplataJedanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIsplataJedanActionPerformed
        try { this.isplatiPrikaz(); }
        catch (IOException ex) { Logger.getLogger(ATM.class.getName()).log(Level.SEVERE, null, ex);}    
    }//GEN-LAST:event_btnIsplataJedanActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
       disconnect();
       this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnIsplatiSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIsplatiSacuvajActionPerformed
        try {this.isplatiSacuvaj();}
        catch (IOException ex) {Logger.getLogger(ATM.class.getName()).log(Level.SEVERE, null, ex); }
    }//GEN-LAST:event_btnIsplatiSacuvajActionPerformed

    private void txtUplataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUplataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUplataActionPerformed

    private void btnUplatiSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUplatiSacuvajActionPerformed
        try {
            this.uplatiSacuvaj();
        } catch (IOException ex) {
            Logger.getLogger(ATM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUplatiSacuvajActionPerformed

    private void btnUplataJedanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUplataJedanActionPerformed
        try {uplatiPrikaz(); }
        catch (IOException ex) { Logger.getLogger(ATM.class.getName()).log(Level.SEVERE, null, ex); }
    }//GEN-LAST:event_btnUplataJedanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ATM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ATM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ATM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ATM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ATM().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(ATM.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ATM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIsplataJedan;
    private javax.swing.JButton btnIsplatiSacuvaj;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnUplataJedan;
    private javax.swing.JButton btnUplatiSacuvaj;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbBrojKartice;
    private javax.swing.JLabel lblAcc;
    private javax.swing.JLabel lblBrRacuna;
    private javax.swing.JLabel lblIme;
    private javax.swing.JLabel lblKorisnik;
    private javax.swing.JLabel lblPoruka;
    private javax.swing.JLabel lblPrezime;
    private javax.swing.JLabel lblStanje;
    private javax.swing.JLabel lblStanjeRac;
    private javax.swing.JLabel lblStanjeRacuna;
    private javax.swing.JPanel pnlIsplata;
    private javax.swing.JPanel pnlUplata;
    private javax.swing.JTextField txtIsplata;
    private javax.swing.JLabel txtStanje;
    private javax.swing.JTextField txtUplata;
    // End of variables declaration//GEN-END:variables
}
