import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaLogin extends JFrame {
    private CardLayout cardLayout;
    private JPanel painelPrincipal;
    private List<Usuario> usuarios = new ArrayList<>();

    public SistemaLogin() {
        setTitle("Sistema de Login e Cadastro");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);

        painelPrincipal.add(criarPainelLogin(), "login");
        painelPrincipal.add(criarPainelCadastro(), "cadastro");

        add(painelPrincipal);
        cardLayout.show(painelPrincipal, "login");
        setVisible(true);
    }

   private JPanel criarPainelLogin() {
    JPanel painel = new JPanel() {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(0, 0, new Color(128, 0, 128), getWidth(), getHeight(), Color.GREEN);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    };
    painel.setLayout(null);

    JLabel imagemLabel = new JLabel(new ImageIcon("SistemaLogin/src/unifg-removebg-preview.png")); // ajuste o caminho conforme necessário
    imagemLabel.setBounds(150, 20, 300, 150); // imagem maior
    painel.add(imagemLabel);

    JLabel emailLabel = new JLabel("Email:");
    emailLabel.setForeground(Color.WHITE); // cor branca
    emailLabel.setBounds(150, 200, 80, 25);
    painel.add(emailLabel);

    JTextField emailField = new JTextField();
    emailField.setBounds(230, 200, 200, 25);
    painel.add(emailField);

    JLabel senhaLabel = new JLabel("Senha:");
    senhaLabel.setForeground(Color.WHITE); // cor branca
    senhaLabel.setBounds(150, 250, 80, 25);
    painel.add(senhaLabel);

    JPasswordField senhaField = new JPasswordField();
    senhaField.setBounds(230, 250, 200, 25);
    painel.add(senhaField);

    JButton loginButton = new JButton("Entrar");
    loginButton.setBounds(230, 300, 100, 30);
    painel.add(loginButton);

    loginButton.addActionListener(e -> {
        String email = emailField.getText();
        String senha = new String(senhaField.getPassword());
        for (Usuario u : usuarios) {
            if (u.getIdnumber().equalsIgnoreCase(email) && u.getPassword().equals(senha)) {
                JOptionPane.showMessageDialog(this, "Bem-vindo, " + u.getFullname() + "!");
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Email ou senha inválidos.");
    });

    JButton irCadastroButton = new JButton("Não tem conta? Cadastre-se");
    irCadastroButton.setBounds(200, 350, 200, 25);
    painel.add(irCadastroButton);
    irCadastroButton.addActionListener(e -> cardLayout.show(painelPrincipal, "cadastro"));

    return painel;
}

    

    private JPanel criarPainelCadastro() {
        JPanel painel = new JPanel();
        painel.setBackground(Color.WHITE);
        painel.setLayout(null);

        JLabel imagemLabel = new JLabel(new ImageIcon("SistemaLogin/src/unifg-removebg-preview.png")); // substitua pelo caminho correto
        imagemLabel.setBounds(20, 50, 150, 300);
        painel.add(imagemLabel);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(200, 50, 80, 25);
        painel.add(nomeLabel);

        JTextField nomeField = new JTextField();
        nomeField.setBounds(280, 50, 250, 25);
        nomeField.setBackground(new Color(128, 0, 128));
        nomeField.setForeground(Color.WHITE);
        painel.add(nomeField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(200, 100, 80, 25);
        painel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(280, 100, 250, 25);
        emailField.setBackground(new Color(128, 0, 128));
        emailField.setForeground(Color.WHITE);
        painel.add(emailField);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(200, 150, 80, 25);
        painel.add(senhaLabel);

        JPasswordField senhaField = new JPasswordField();
        senhaField.setBounds(280, 150, 250, 25);
        senhaField.setBackground(new Color(128, 0, 128));
        senhaField.setForeground(Color.WHITE);
        painel.add(senhaField);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(280, 200, 150, 30);
        cadastrarButton.setBackground(Color.BLACK);
        cadastrarButton.setForeground(Color.WHITE);
        painel.add(cadastrarButton);

        cadastrarButton.addActionListener(e -> {
            String nome = nomeField.getText();
            String email = emailField.getText();
            String senha = new String(senhaField.getPassword());

            for (Usuario u : usuarios) {
                if (u.getIdnumber().equalsIgnoreCase(email)) {
                    JOptionPane.showMessageDialog(this, "Email já cadastrado.");
                    return;
                }
            }

            usuarios.add(new Usuario(nome, email, senha));
            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
            cardLayout.show(painelPrincipal, "login");
        });

        JButton voltarLoginButton = new JButton("Já tem conta? Faça login");
        voltarLoginButton.setBounds(280, 250, 200, 25);
        painel.add(voltarLoginButton);
        voltarLoginButton.addActionListener(e -> cardLayout.show(painelPrincipal, "login"));

        return painel;
    }

    public static void main(String[] args) {
        new SistemaLogin();
    }
}
