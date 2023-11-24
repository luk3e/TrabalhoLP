import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

class Usuario {
    private String nome;
    private String email;
    private String senha;
    private String mensagem;
    private Map<String, Usuario> amigos;
    private List<String> mensagensRecebidas;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.mensagem = "";
        this.amigos = new HashMap();
        this.mensagensRecebidas = new ArrayList();
    }

    public String getEmail() {
        return this.email;
    }

    public String getSenha() {
        return this.senha;
    }

    public String getMensagem() {
        return this.mensagem;
    }

    public void adicionarAmigo(Usuario amigo) {
        this.amigos.put(amigo.getEmail(), amigo);
    }

    public void removerAmigo(Usuario amigo) {
        this.amigos.remove(amigo.getEmail());
    }

    public void enviarMensagem(Usuario amigo, String mensagem) {
        if (this.amigos.containsKey(amigo.getEmail())) {
            amigo.receberMensagem("De " + this.nome + ": " + mensagem);
        } else {
            String var10001 = this.nome;
            JOptionPane.showMessageDialog((Component)null, "Erro: " + var10001 + " e " + amigo.getNome() + " não são amigos.");
        }

    }

    public String getNome() {
        return this.nome;
    }

    private void receberMensagem(String mensagem) {
        this.mensagensRecebidas.add(mensagem);
    }

    public List<String> getMensagensRecebidas() {
        return this.mensagensRecebidas;
    }

    public Map<String, Usuario> getAmigos() {
        return this.amigos;
    }
}
