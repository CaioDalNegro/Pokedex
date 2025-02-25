package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DB;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;

public class PokedexUI extends JFrame {

    // Atributos da interface gráfica
    private JTextField txtSearch; // Campo de texto para pesquisa de Pokémon
    private JLabel lblName, lblType, lblImagem; // Labels para exibir nome, tipo e imagem do Pokémon
    private JButton btnSearch; // Botão para realizar a busca
    private JLabel lblNomeTitulo; // Título com o nome do Pokémon
    private JLabel lblDescricao; // Descrição do Pokémon
    private JLabel lblCategory; // Categoria do Pokémon
    private JLabel lblAbilities; // Habilidades do Pokémon

    public PokedexUI() {
        // Configurações iniciais da janela
        setResizable(false); // Não permite redimensionar a janela
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\cris-\\OneDrive\\Área de Trabalho\\PROGRAMAÇÃO\\Nova pasta\\jdbc5\\imagem\\Pokedex.png"));
        setTitle("Pokédex"); // Título da janela
        setSize(800, 500); // Tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ação ao fechar a janela
        getContentPane().setLayout(null); // Layout manual

        // Centralizar a janela na tela
        setLocationRelativeTo(null);

        // Painel superior para pesquisa
        JPanel panel = new JPanel();
        panel.setBackground(new Color(192, 192, 192)); // Cor do fundo
        panel.setBounds(10, 6, 764, 50); // Posicionamento do painel
        getContentPane().add(panel);
        panel.setLayout(null);

        // Botão de busca
        btnSearch = new JButton("Buscar");
        btnSearch.setBounds(186, 11, 80, 23); // Posicionamento e tamanho
        panel.add(btnSearch);

        // Campo de pesquisa de Pokémon
        txtSearch = new JTextField(20);
        txtSearch.setBounds(10, 12, 166, 20); // Posicionamento e tamanho
        panel.add(txtSearch);

        // Painel inferior para informações do Pokémon
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(192, 192, 192));
        panel_1.setBounds(10, 301, 764, 149); // Posicionamento do painel
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        // Label para exibir a descrição do Pokémon
        lblDescricao = new JLabel("Descrição: ");
        lblDescricao.setVerticalAlignment(SwingConstants.TOP);
        lblDescricao.setHorizontalAlignment(SwingConstants.LEFT);
        lblDescricao.setBounds(10, 11, 455, 127); // Ajuste o posicionamento conforme necessário
        panel_1.add(lblDescricao);

        // Painel para exibir a imagem do Pokémon
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(192, 192, 192));
        panel_2.setBounds(10, 67, 175, 223); // Posicionamento do painel
        getContentPane().add(panel_2);
        panel_2.setLayout(null);

        // Label para exibir o nome do Pokémon
        lblNomeTitulo = new JLabel("");
        lblNomeTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblNomeTitulo.setBounds(10, 11, 155, 14); // Posicionamento
        panel_2.add(lblNomeTitulo);

        // Label para exibir a imagem do Pokémon
        lblImagem = new JLabel("");
        lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagem.setBounds(10, 26, 155, 155); // Posicionamento e tamanho
        panel_2.add(lblImagem);

        // Painel para exibir as informações do Pokémon (tipo, categoria, habilidades)
        JPanel panel_3 = new JPanel();
        panel_3.setBackground(new Color(192, 192, 192));
        panel_3.setBounds(195, 67, 287, 223); // Posicionamento do painel
        getContentPane().add(panel_3);
        panel_3.setLayout(null);

        // Labels para exibir tipo, categoria e habilidades
        lblType = new JLabel("Tipo: ");
        lblType.setBounds(10, 36, 249, 14);
        panel_3.add(lblType);

        lblName = new JLabel("Nome: ");
        lblName.setBounds(10, 11, 249, 14);
        panel_3.add(lblName);

        lblCategory = new JLabel("Categoria: ");
        lblCategory.setBounds(10, 61, 249, 14);
        panel_3.add(lblCategory);

        lblAbilities = new JLabel("Habilidades: ");
        lblAbilities.setBounds(10, 86, 249, 14);
        panel_3.add(lblAbilities);

        // Ação do botão de busca
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPokemon(txtSearch.getText().trim()); // Chama a função de busca com o nome do Pokémon
            }
        });

        setVisible(true); // Torna a interface visível
    }

    // Método para buscar informações do Pokémon no banco de dados
    private void buscarPokemon(String nome) {
        Connection conn = DB.getConnection(); // Obtém a conexão com o banco
        String sql = "SELECT nome, tipo, descricao, abilities, category, imagem_url FROM pokemon WHERE nome = ?"; // Consulta SQL para buscar o Pokémon pelo nome

        try (PreparedStatement st = conn.prepareStatement(sql)) { // Prepara a consulta SQL
            st.setString(1, nome); // Define o parâmetro de busca (nome do Pokémon)
            ResultSet rs = st.executeQuery(); // Executa a consulta

            if (rs.next()) { // Se o Pokémon for encontrado
                lblNomeTitulo.setText(rs.getString("nome")); // Exibe o nome
                lblName.setText("Nome: " + rs.getString("nome"));
                lblType.setText("Tipo: " + rs.getString("tipo"));
                lblCategory.setText("Categoria: " + rs.getString("category"));
                lblAbilities.setText("Abilities: " + rs.getString("abilities"));
                lblDescricao.setText("Descrição: " + rs.getString("descricao"));

                // Carregar imagem do Pokémon
                String imagemUrl = rs.getString("imagem_url"); // Obtém a URL da imagem
                System.out.println("URL da imagem: " + imagemUrl);  // Exibe a URL no console para depuração
                try {
                    URL url = new URL(imagemUrl);  // Cria um objeto URL com o link da imagem
                    ImageIcon icon = new ImageIcon(url); // Cria um ícone a partir da URL
                    Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Ajusta o tamanho da imagem
                    lblImagem.setIcon(new ImageIcon(image)); // Exibe a imagem na interface
                } catch (MalformedURLException e) {
                    JOptionPane.showMessageDialog(this, "URL da imagem inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Erro ao carregar a imagem.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pokemon não encontrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }

            DB.closeResultSet(rs); // Fecha o ResultSet
            DB.closeStatement(st); // Fecha o PreparedStatement
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar Pokémon: " + e.getMessage()); // Exibe erro se ocorrer
        }
    }

    // Função principal que cria a interface
    public static void main(String[] args) {
        new PokedexUI(); // Cria uma nova instância da interface
    }
}
