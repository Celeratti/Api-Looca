/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.celeratti.swing;

import br.com.celeratti.dto.DadosMaquina;
import br.com.celeratti.dto.DadosUsuario;
import br.com.celeratti.model.EspecificacoesHardware;
import br.com.celeratti.services.Services;
import br.com.celeratti.util.Maquina;
import com.github.britooo.looca.api.core.Looca;

import javax.swing.*;

public class TelaLogin extends javax.swing.JFrame {
    /**
     * Creates new form TelaLogin
     */
    public TelaLogin() {
        initComponents();
        maquina = new Maquina();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblIdenficacao = new javax.swing.JLabel();
        txtIdentificacao = new javax.swing.JTextField();
        lblSenha = new javax.swing.JLabel();
        buttonLogar = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Segoe UI Semibold", 3, 48)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(153, 153, 153));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("CELERATTI");

        lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblEmail.setText("Email:");

        txtEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblIdenficacao.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblIdenficacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIdenficacao.setText("Identificação:");

        txtIdentificacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdentificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentificacaoActionPerformed(evt);
            }
        });

        lblSenha.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSenha.setText("Senha:");

        buttonLogar.setBackground(new java.awt.Color(158, 17, 27));
        buttonLogar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonLogar.setForeground(new java.awt.Color(255, 255, 255));
        buttonLogar.setText("LOGAR");
        buttonLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(txtIdentificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(86, 86, 86))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblIdenficacao, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addComponent(lblSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(76, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(163, 163, 163))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblTitulo)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(lblIdenficacao)
                    .addComponent(lblSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIdentificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(buttonLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdentificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentificacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificacaoActionPerformed

            
    
    private void buttonLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogarActionPerformed
        String email = txtEmail.getText();
        String identificacao = txtIdentificacao.getText();
        char[] passwd = txtSenha.getPassword();
        String senha = new String(passwd);
        DadosUsuario dadosUsuario = maquina.getServices().verificarLogin(email,senha);
        if (dadosUsuario == null){
            JOptionPane.showMessageDialog(this,"Email ou senha inválidos");
            txtEmail.setText("");
            txtSenha.setText("");
        }else{
            if (dadosUsuario.email().equals(email) && dadosUsuario.senha().equals(senha)){
                try{
                    DadosMaquina dadosMaquina = maquina.getServices().verificarMaquina(identificacao);
                if (!(dadosMaquina.id() == null)) {
                    if (dadosMaquina.fkEmpresa() == dadosUsuario.fkEmpresa()){
                        if (dadosMaquina.status() == 0){
                            maquina.getServices().inserirEspecs(new EspecificacoesHardware(maquina.getLooca(),
                                    dadosMaquina.id()));
                        }
                        TelaInsercao tela = new TelaInsercao();
                        maquina.setId(dadosMaquina.id());
                        tela.setMaq(maquina);
                        this.dispose();
                        tela.setVisible(true);
                        }else{
                            JOptionPane.showMessageDialog(this,"Você não tem autorização para acessar esta máquina");
                        }

                }else{
                    JOptionPane.showMessageDialog(this,"Máquina inexistente");
                }
                } catch (NullPointerException n){
                    JOptionPane.showMessageDialog(this,"Máquina inexistente!");
                }
            }else{
                JOptionPane.showMessageDialog(this,"Email ou senha inválidos");
                txtEmail.setText("");
                txtSenha.setText("");
            }
        }
    }//GEN-LAST:event_buttonLogarActionPerformed

    
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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLogar;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblIdenficacao;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIdentificacao;
    private javax.swing.JPasswordField txtSenha;
    private Maquina maquina;
    // End of variables declaration//GEN-END:variables
}
