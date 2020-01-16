
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class ProductosTenda {
    String nomeTenda, nomeProducto;
    int stock;

    public ProductosTenda() {
    }

    public ProductosTenda(String nomeTenda, String nomeProducto, int stock) {
        this.nomeTenda = nomeTenda;
        this.nomeProducto = nomeProducto;
        this.stock = stock;
    }
    
    public String getNomeTenda(){
        return nomeTenda;
    }
    
    public void setNomeTenda(String nomeTenda){
        this.nomeTenda = nomeTenda;
    }

    public String getNomeProducto() {
        return nomeProducto;
    }

    public void setNomeProducto(String nomeProducto) {
        this.nomeProducto = nomeProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public void datosProductosTenda(){
        Scanner teclado = new Scanner (System.in);
        System.out.println("Nome da tenda na que engadir o producto: ");
        this.nomeTenda = teclado.nextLine();
        System.out.println("Nome do producto: ");
        this.nomeProducto = teclado.nextLine();
        System.out.println("Stock do producto: ");
        this.stock = teclado.nextInt();
    }
    
    public void insertProductoTenda(Connection con, String nomeTenda, String nomeProducto, int stock){
        try{
            String sql = "INSERT INTO ProductosTenda (nomeTenda, nomeProducto, stock) VALUES(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, nomeTenda);
            pstmt.setString(2, nomeProducto);
            pstmt.setInt(3, stock);
            pstmt.executeUpdate();
            System.out.println("Producto engadido");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteProductoTenda(Connection con, String productoTendaBorrar){
        try{
            String sql = "DELETE FROM ProductosTenda WHERE nomeProducto = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, productoTendaBorrar);
            pstmt.executeUpdate();
            System.out.println("Producto borrado con éxito");
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public String productoTendaSeleccionado(){
        Scanner teclado = new Scanner (System.in);
        System.out.println("Escribe o nome do producto que desexas borrar");
        return teclado.nextLine();
    }
    
    public void consultarProductoTenda(Connection con){
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select nomeProducto from ProductosTenda");
            while(rs.next()){
                System.out.println("Producto = " + rs.getString("nomeProducto"));
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public void consultarStrockProducto(Connection con){
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select stock from ProductosTenda WHERE nomeProducto = (?)");
            while(rs.next()){
                System.out.println("Stock producto = " + rs.getString("stock"));
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

}
