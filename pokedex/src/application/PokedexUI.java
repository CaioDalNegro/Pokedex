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
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

public class PokedexUI extends JFrame {

	// Atributos da interface gráfica
	private JTextField txtSearch; // Campo de texto para pesquisa de Pokémon
	private JLabel lblName, lblType, lblImagem; // Labels para exibir nome, tipo e imagem do Pokémon
	private JButton btnSearch; // Botão para realizar a busca
	private JLabel lblNomeTitulo; // Título com o nome do Pokémon
	private JLabel lblDescricao; // Descrição do Pokémon
	private JLabel lblCategory; // Categoria do Pokémon
	private JLabel lblAbilities; // Habilidades do Pokémon
	private JPanel panel_4;
	private JLabel lblTitulo;
	private JLabel lblImagemTitulo;
	private JLabel lblHabilidade1;
	private JLabel lblHabilidade2;
	private JLabel lblHabilidade3;

	public PokedexUI() {
		getContentPane().setBackground(new Color(220, 10, 45));
		// Configurações iniciais da janela
		setResizable(false); // Não permite redimensionar a janela
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\cris-\\Documents\\Pokedex\\pokedex\\imagem\\icon.png"));
		setTitle("Pokédex"); // Título da janela
		setSize(635, 550); // Tamanho da janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ação ao fechar a janela
		getContentPane().setLayout(null); // Layout manual

		// Centralizar a janela na tela
		setLocationRelativeTo(null);

		JPanel Container = new JPanel();
		Container.setBorder(UIManager.getBorder("EditorPane.border"));
		Container.setBackground(new Color(240, 240, 244));
		Container.setBounds(10, 105, 599, 395);
		getContentPane().add(Container);
		Container.setLayout(null);

		// Painel inferior para informações do Pokémon
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(10, 272, 575, 112);
		Container.add(panel_1);
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setLayout(null);

		// Label para exibir a descrição do Pokémon
		lblDescricao = new JLabel("Descrição: ");
		lblDescricao.setVerticalAlignment(SwingConstants.TOP);
		lblDescricao.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescricao.setBounds(10, 11, 455, 127); // Ajuste o posicionamento conforme necessário
		panel_1.add(lblDescricao);

		// Painel para exibir as informações do Pokémon (tipo, categoria, habilidades)
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_3.setBounds(195, 11, 190, 112);
		Container.add(panel_3);
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setLayout(null);

		// Labels para exibir tipo, categoria e habilidades
		lblType = new JLabel("Tipo: ");
		lblType.setBounds(10, 36, 170, 14);
		panel_3.add(lblType);

		lblName = new JLabel("Nome: ");
		lblName.setBounds(10, 11, 170, 14);
		panel_3.add(lblName);

		lblCategory = new JLabel("Categoria: ");
		lblCategory.setBounds(10, 61, 170, 14);
		panel_3.add(lblCategory);

		// Painel para exibir a imagem do Pokémon
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setBounds(10, 11, 175, 235);
		Container.add(panel_2);
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setLayout(null);

		// Label para exibir o nome do Pokémon
		lblNomeTitulo = new JLabel("");
		lblNomeTitulo.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNomeTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeTitulo.setBounds(10, 202, 155, 14); // Posicionamento
		panel_2.add(lblNomeTitulo);

		// Label para exibir a imagem do Pokémon
		lblImagem = new JLabel("");
		lblImagem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagem.setBounds(10, 36, 155, 155); // Posicionamento e tamanho
		panel_2.add(lblImagem);

		panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.setBounds(195, 134, 190, 112);
		Container.add(panel_4);
		panel_4.setLayout(null);

		lblAbilities = new JLabel("Habilidades:");
		lblAbilities.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAbilities.setVerticalAlignment(SwingConstants.TOP);
		lblAbilities.setHorizontalAlignment(SwingConstants.LEFT);
		lblAbilities.setBounds(10, 5, 170, 14);
		panel_4.add(lblAbilities);
		
		lblHabilidade1 = new JLabel("");
		lblHabilidade1.setBounds(30, 30, 150, 14);
		panel_4.add(lblHabilidade1);
		
		lblHabilidade2 = new JLabel("");
		lblHabilidade2.setBounds(30, 55, 150, 14);
		panel_4.add(lblHabilidade2);
		
		lblHabilidade3 = new JLabel("");
		lblHabilidade3.setBounds(30, 80, 150, 14);
		panel_4.add(lblHabilidade3);

		// Campo de pesquisa de Pokémon
		txtSearch = new JTextField(20);
		txtSearch.setFont(new Font("Verdana", Font.BOLD, 12));
		txtSearch.setBounds(23, 71, 184, 23);
		getContentPane().add(txtSearch);

		// Botão de busca
		btnSearch = new JButton("Buscar");
		btnSearch.setBounds(217, 71, 80, 23);
		getContentPane().add(btnSearch);

		lblTitulo = new JLabel("Pokédex");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Verdana", Font.BOLD, 20));
		lblTitulo.setBounds(65, 28, 105, 32);
		getContentPane().add(lblTitulo);

		lblImagemTitulo = new JLabel("New label");
		lblImagemTitulo.setIcon(new ImageIcon("C:\\Users\\cris-\\Documents\\Pokedex\\pokedex\\imagem\\image 5.png"));
		lblImagemTitulo.setBounds(23, 28, 32, 32);
		getContentPane().add(lblImagemTitulo);

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
		String sql = "SELECT nome, tipo, descricao, imagem_url, habilidade_1, habilidade_2, habilidade_3, category FROM pokemon WHERE nome = ?"; // Consulta SQL para buscar o Pokémon pelo nome

		try (PreparedStatement st = conn.prepareStatement(sql)) { // Prepara a consulta SQL
			st.setString(1, nome); // Define o parâmetro de busca (nome do Pokémon)
			ResultSet rs = st.executeQuery(); // Executa a consulta

			if (rs.next()) { // Se o Pokémon for encontrado
				lblNomeTitulo.setText(rs.getString("nome")); // Exibe o nome
				lblName.setText("Nome: " + rs.getString("nome"));
				lblType.setText("Tipo: " + rs.getString("tipo"));
				lblCategory.setText("Categoria: " + rs.getString("category"));
				lblDescricao.setText("Descrição: " + rs.getString("descricao"));
				
				lblHabilidade1.setText(rs.getString("habilidade_1"));
				lblHabilidade2.setText(rs.getString("habilidade_2"));
				lblHabilidade3.setText(rs.getString("habilidade_3"));

				// Carregar imagem do Pokémon
				String imagemUrl = rs.getString("imagem_url"); // Obtém a URL da imagem
				System.out.println("URL da imagem: " + imagemUrl); // Exibe a URL no console para depuração
				try {
					URL url = new URL(imagemUrl); // Cria um objeto URL com o link da imagem
					ImageIcon icon = new ImageIcon(url); // Cria um ícone a partir da URL
					Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Ajusta o tamanho
																									// da imagem
					lblImagem.setIcon(new ImageIcon(image)); // Exibe a imagem na interface
				} catch (MalformedURLException e) {
					JOptionPane.showMessageDialog(this, "URL da imagem inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(this, "Erro ao carregar a imagem.", "Erro",
							JOptionPane.ERROR_MESSAGE);
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
