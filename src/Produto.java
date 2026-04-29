import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Produto {

    private int id;
    private String descricao;
    private double preco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void cadastrar() throws SQLException, ClassNotFoundException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement("insert into produtos(descricao, preco) values (?,?)");
        comando.setString (1, descricao);
        comando.setDouble(2, preco);
        comando.execute();
        con.close();
    }

    public void excluir () throws SQLException, ClassNotFoundException{
        Connection con= getConexao();
        PreparedStatement comando = con.prepareStatement("delete from produtos where id=?");
        comando.setInt(1,id);
        con.close();
    }
    public void update() throws SQLException, ClassNotFoundException{
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement("update produtos set descricao=?, preco=? where id=?");
        comando.setString(1, descricao);
        comando.setDouble(2, preco);
        comando.setInt(3, id);
        con.close();
    }
    public Produto consultarById() throws SQLException, ClassNotFoundException{
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement("select * from produtos where id = ?");
        comando.setInt(1,id);
        ResultSet resultado = comando.executeQuery();
        Produto prod = new Produto();

        if (resultado.next()){
            prod.setId(resultado.getInt("id"));
            prod.setDescricao (resultado.getString("descricao"));
            prod.setPreco(resultado.getDouble("preco"));
        }
        con.close();
        return prod;
    }
    public List<Produto> consultarTodos() throws SQLException, ClassNotFoundException, NullPointerException{
        Connection con = getConexao();
        String SQL = "select id as codigo, descricao as Descri, preco from produtos";
        PreparedStatement comando = con.prepareStatement(SQL);
        ResultSet resultado = comando.executeQuery();
        List<Produto> listaprodutos= new ArrayList<>();
        while (resultado.next()){
            Produto prod = new Produto();
            prod.setId(resultado.getInt("codigo"));
            prod.setDescricao(resultado.getString("Descri"));
            prod.setPreco(resultado.getDouble("preco"));
            listaprodutos.add(prod);
        }
        con.close();
        return listaprodutos;
    }

    public Connection getConexao(){
        try {
            Class.forName("java.sql.Driver");
            return DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/aula_ioo", "root", "");
        } catch (ClassNotFoundException | SQLException| NullPointerException ex) {
            System.out.println(ex);
            return null;
        }
    }
}
