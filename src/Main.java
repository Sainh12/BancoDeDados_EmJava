import java.sql.SQLException;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Produto p = new Produto();
        p.setDescricao("Ampulheta");
        p.setPreco(90.00);
        try {
            p.cadastrar();
        } catch (SQLException | ClassNotFoundException ex){
            System.out.println("Erro: " + ex.getMessage());
        }
        /*
        Produto p = new Produto();
        try {
            List<Produto> lprod = p.consultarTodos();
            for (Produto prod : lprod) {
                System.out.println("\nID.........: " + prod.getId());
                System.out.println("Descricao..: " + prod.getDescricao());
                System.out.println("Preco......: " + prod.getPreco());
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }*/
    }
}