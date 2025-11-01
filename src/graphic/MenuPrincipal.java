package graphic;

import java.awt.*;
import javax.swing.*;

public class MenuPrincipal extends JFrame {

    private final JDesktopPane desktopPane;

    public MenuPrincipal() {
        setTitle("Sistema para Controle de Vinhos");
        setSize(1024, 768);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktopPane = new DesktopComFundo("/VSCode/Vinhos/sistema-erp-vinhos/vinhosfundo.jpg");//
        setContentPane(desktopPane);//define o painel de área de trabalho com fundo personalizado com a imagem de fundo

        JMenuBar menuBar = new JMenuBar();//barra de menu

        JMenu menuSistema = new JMenu("Sistema");//menu sistema e seus itens
            JMenuItem itemUsuario = new JMenuItem("Usuário");//item do menu sistema
        menuSistema.add(itemUsuario);//menuSistema adiciona itemUsuario

        JMenu menuCadastro = new JMenu("Cadastro");//menus e itens de menu

            JMenuItem itemPaises = new JMenuItem("Países");//itens do menu cadastro
            JMenuItem itemRegioes = new JMenuItem("Regiões");
            JMenuItem itemViniculas = new JMenuItem("Viniculas");
            JMenuItem itemUvas = new JMenuItem("Uvas");
            JMenuItem itemVinho = new JMenuItem("Vinhos");

        menuCadastro.add(itemPaises);
        menuCadastro.add(itemRegioes);
        menuCadastro.add(itemViniculas);
        menuCadastro.add(itemUvas);
        menuCadastro.add(itemVinho);

        JMenu menuRelatorio = new JMenu("Relatório");
            JMenuItem itemRelatorioVendas = new JMenuItem("Relatório de Vendas");
        menuRelatorio.add(itemRelatorioVendas);

        menuBar.add(menuSistema);
        menuBar.add(menuCadastro);
        menuBar.add(menuRelatorio);

        setJMenuBar(menuBar);
    }

    static class DesktopComFundo extends JDesktopPane {

        private Image imagemFundo;

        public DesktopComFundo(String caminhoImagem) {
            this.imagemFundo = new ImageIcon(caminhoImagem).getImage();//adiciona a imagem de fundo
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
        }

    }

}
