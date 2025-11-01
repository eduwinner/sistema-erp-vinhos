package graphic;

import database.ConnectionFactory;
import database.dao.UsuarioDAO;
import database.model.UsuarioModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;
import util.HashUtils;

public class LoginWindow extends JFrame {

    private JLabel lblTitulo, lblUsuario, lblSenha;
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnEntrar;

    public LoginWindow() {
        setTitle("Tela de Login"); //título da janela
        setSize(300, 200); // tamanho da janela
        setLocationRelativeTo(null);
        //posiciona a janela no centro da tela
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //ação de fechar a janela e finalizar o programa
        setLayout(null);//desabilita o gerenciador de layout
        componentesCriar();//método para criar os componentes
        setVisible(true);//torna a janela visível           
    }

    private void componentesCriar() {

        lblTitulo = new JLabel("Sistema Vinhos");//inicializa o componente rótulo
        lblTitulo.setBounds(35, 20, 280, 30);//define posição e tamanho do rótulo
        lblTitulo.setFont(new Font(Font.MONOSPACED, Font.BOLD, 26));//define fonte do rótulo
        getContentPane().add(lblTitulo);//adiciona o rótulo ao conteúdo da janela

        lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(10, 70, 80, 30);
        getContentPane().add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(80, 70, 200, 30);
        getContentPane().add(txtUsuario);

        lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(10, 100, 80, 30);
        getContentPane().add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(80, 100, 200, 30);
        getContentPane().add(txtSenha);

        btnEntrar = new JButton(new AbstractAction("ENTRAR") {
            @Override
            public void actionPerformed(ActionEvent e) {

                String usuario = txtUsuario.getText();
                String senha = txtSenha.getText();

                if (usuario.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Campo <Usuário> obrigatório!");
                    txtUsuario.requestFocus();
                    return;
                }

                if (senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Campo <Senha> obrigatório!");
                    txtSenha.requestFocus();
                    return;
                }

                try {
                    Connection conexao = ConnectionFactory.getConnection("localhost", 5454, "controlevinhos", "postgres", "Eduadm@23#");
                    if (conexao != null) {

                        UsuarioModel usuarioModel = new UsuarioModel();
                        usuarioModel.setUsuario(usuario);
                        usuarioModel.setSenha(HashUtils.criarMD5(senha));

                        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
                        if (!usuarioDAO.selectByUsuarioESenha(usuarioModel)) {
                            JOptionPane.showMessageDialog(null, "Usuário ou Senha inválidos!");
                            return;
                        }

                        new MenuPrincipal().setVisible(true);
                        dispose();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Não foi possível conectar no banco de dados: "+ex.getMessage());
                }
            }
        });
        btnEntrar.setBounds(80, 130, 200, 30);
        getContentPane().add(btnEntrar);
    }

    public static void main(String[] args) {
        new LoginWindow();
    }
}