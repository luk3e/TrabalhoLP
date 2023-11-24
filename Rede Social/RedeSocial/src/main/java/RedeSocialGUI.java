import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class RedeSocialGUI extends JFrame {
    private JPanel painelLogin = new JPanel();
    private JPanel painelUsuario = new JPanel();
    private JButton loginButton = new JButton("Login");
    private JButton cadastroButton = new JButton("Cadastre-se");
    private JButton esqueciSenhaButton = new JButton("Esqueci a senha");
    private JLabel mensagemLabel = new JLabel("Rede Social - Login ou Cadastro");
    private Map<String, Usuario> usuarios = new HashMap();
    private Usuario usuarioAtual;

    public RedeSocialGUI() {
        this.setTitle("Rede Social - Interface Gráfica");
        this.setSize(600, 400);
        this.painelLogin.setLayout(new GridLayout(4, 1, 10, 10));
        this.painelUsuario.setLayout(new GridLayout(7, 1, 10, 10));
        this.estiloBotao(this.loginButton);
        this.estiloBotao(this.cadastroButton);
        this.estiloBotao(this.esqueciSenhaButton);

        this.loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = JOptionPane.showInputDialog((Component)null, "Digite seu email:");
                String senha = JOptionPane.showInputDialog((Component)null, "Digite sua senha:");
                if (RedeSocialGUI.this.usuarios.containsKey(email) && ((Usuario)RedeSocialGUI.this.usuarios.get(email)).getSenha().equals(senha)) {
                    RedeSocialGUI.this.usuarioAtual = (Usuario)RedeSocialGUI.this.usuarios.get(email);
                    RedeSocialGUI.this.exibirTelaUsuario();
                } else {
                    JOptionPane.showMessageDialog((Component)null, "Login falhou. Verifique suas credenciais.");
                }

            }
        });
        this.cadastroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = JOptionPane.showInputDialog((Component)null, "Digite seu nome:");
                String email = JOptionPane.showInputDialog((Component)null, "Digite seu email:");
                String senha = JOptionPane.showInputDialog((Component)null, "Digite sua senha:");
                RedeSocialGUI.this.usuarios.put(email, new Usuario(nome, email, senha));
                JOptionPane.showMessageDialog((Component)null, "Cadastro concluído com sucesso!");
                RedeSocialGUI.this.salvarUsuariosEmArquivo();
            }
        });
        this.esqueciSenhaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = JOptionPane.showInputDialog((Component)null, "Digite seu email:");
                if (RedeSocialGUI.this.usuarios.containsKey(email)) {
                    Object var10001 = RedeSocialGUI.this.usuarios.get(email);
                    JOptionPane.showMessageDialog((Component)null, "Sua senha é: " + ((Usuario)var10001).getSenha());
                } else {
                    JOptionPane.showMessageDialog((Component)null, "Email não encontrado.");
                }

            }
        });
        this.painelLogin.add(this.mensagemLabel);
        this.painelLogin.add(this.loginButton);
        this.painelLogin.add(this.cadastroButton);
        this.painelLogin.add(this.esqueciSenhaButton);
        this.getContentPane().add(this.painelLogin);
        this.setLocationRelativeTo((Component)null);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }

    private void exibirTelaUsuario() {
        JButton adicionarAmigoButton = new JButton("Adicionar Amigo");
        JButton removerAmigoButton = new JButton("Remover Amigo");
        JButton enviarMensagemButton = new JButton("Enviar Mensagem");
        JButton verMensagensButton = new JButton("Ver Mensagens");
        JButton consultarAmigosButton = new JButton("Consultar Amigos");
        JButton logoutButton = new JButton("Logout");
        this.estiloBotao(adicionarAmigoButton);
        this.estiloBotao(removerAmigoButton);
        this.estiloBotao(enviarMensagemButton);
        this.estiloBotao(verMensagensButton);
        this.estiloBotao(consultarAmigosButton);
        this.estiloBotao(logoutButton);

        adicionarAmigoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String emailAmigo = JOptionPane.showInputDialog((Component)null, "Digite o email do amigo:");
                Usuario amigo = (Usuario)RedeSocialGUI.this.usuarios.get(emailAmigo);
                if (amigo != null && !amigo.getEmail().equals(RedeSocialGUI.this.usuarioAtual.getEmail())) {
                    RedeSocialGUI.this.usuarioAtual.adicionarAmigo(amigo);
                    JOptionPane.showMessageDialog((Component)null, "Amigo adicionado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog((Component)null, "Amigo não encontrado ou você inseriu seu próprio email.");
                }

            }
        });
        removerAmigoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String emailAmigo = JOptionPane.showInputDialog((Component)null, "Digite o email do amigo:");
                Usuario amigo = (Usuario)RedeSocialGUI.this.usuarios.get(emailAmigo);
                if (amigo != null && RedeSocialGUI.this.usuarioAtual.getAmigos().containsKey(emailAmigo)) {
                    RedeSocialGUI.this.usuarioAtual.removerAmigo(amigo);
                    JOptionPane.showMessageDialog((Component)null, "Amigo removido com sucesso!");
                } else {
                    JOptionPane.showMessageDialog((Component)null, "Amigo não encontrado ou não é seu amigo.");
                }

            }
        });
        enviarMensagemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String emailAmigo = JOptionPane.showInputDialog((Component)null, "Digite o email do amigo:");
                String mensagem = JOptionPane.showInputDialog((Component)null, "Digite a mensagem:");
                Usuario amigo = (Usuario)RedeSocialGUI.this.usuarios.get(emailAmigo);
                if (amigo != null && RedeSocialGUI.this.usuarioAtual.getAmigos().containsKey(emailAmigo)) {
                    RedeSocialGUI.this.usuarioAtual.enviarMensagem(amigo, mensagem);
                    JOptionPane.showMessageDialog((Component)null, "Mensagem enviada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog((Component)null, "Erro: Amigo não encontrado ou não é seu amigo.");
                }

            }
        });
        verMensagensButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RedeSocialGUI.this.exibirMensagensRecebidas();
            }
        });
        consultarAmigosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RedeSocialGUI.this.consultarAmigos();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RedeSocialGUI.this.usuarioAtual = null;
                RedeSocialGUI.this.voltarTelaLogin();
            }
        });
        this.painelUsuario.removeAll();
        this.painelUsuario.add(new JLabel("Bem-vindo, " + this.usuarioAtual.getNome() + "!"));
        this.painelUsuario.add(adicionarAmigoButton);
        this.painelUsuario.add(removerAmigoButton);
        this.painelUsuario.add(enviarMensagemButton);
        this.painelUsuario.add(verMensagensButton);
        this.painelUsuario.add(consultarAmigosButton);
        this.painelUsuario.add(logoutButton);
        this.getContentPane().removeAll();
        this.getContentPane().add(this.painelUsuario);
        this.revalidate();
        this.repaint();
    }

    private void consultarAmigos() {
        StringBuilder listaAmigos = new StringBuilder("Amigos:\n");
        Iterator var2 = this.usuarioAtual.getAmigos().values().iterator();

        while(var2.hasNext()) {
            Usuario amigo = (Usuario)var2.next();
            listaAmigos.append(amigo.getNome()).append("\n");
        }

        JOptionPane.showMessageDialog((Component)null, listaAmigos.toString(), "Lista de Amigos", 1);
    }

    private void exibirMensagensRecebidas() {
        StringBuilder mensagens = new StringBuilder("Mensagens Recebidas:\n");
        Iterator var2 = this.usuarioAtual.getMensagensRecebidas().iterator();

        while(var2.hasNext()) {
            String mensagem = (String)var2.next();
            mensagens.append(mensagem).append("\n");
        }

        JOptionPane.showMessageDialog((Component)null, mensagens.toString());
    }

    private void voltarTelaLogin() {
        this.painelLogin.removeAll();
        this.painelLogin.add(this.mensagemLabel);
        this.painelLogin.add(this.loginButton);
        this.painelLogin.add(this.cadastroButton);
        this.painelLogin.add(this.esqueciSenhaButton);
        this.getContentPane().removeAll();
        this.getContentPane().add(this.painelLogin);
        this.revalidate();
        this.repaint();
    }

    private void estiloBotao(JButton button) {
        button.setPreferredSize(new Dimension(150, 30));
        button.setBackground(new Color(19, 180, 18));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button.setFont(new Font("Arial", 0, 14));
    }

    private void salvarUsuariosEmArquivo() {
        try {
            FileWriter writer = new FileWriter("usuarios.txt");
            Iterator var2 = this.usuarios.values().iterator();

            while(var2.hasNext()) {
                Usuario usuario = (Usuario)var2.next();
                String var10001 = usuario.getNome();
                writer.write(var10001 + "," + usuario.getEmail() + "," + usuario.getSenha() + "\n");
            }

            writer.close();
        } catch (IOException var4) {
            var4.printStackTrace();
            JOptionPane.showMessageDialog((Component)null, "Erro ao salvar dados em arquivo.");
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RedeSocialGUI();
            }
        });
    }
}
