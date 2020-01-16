
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Productos {
    String nome, descripcion, prezo;

    public Productos() {
    }

    public Productos(String nome, String descripcion, String prezo) {
        this.nome = nome;
        this.descripcion = descripcion;
        this.prezo = prezo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrezo() {
        return prezo;
    }

    public void setPrezo(String prezo) {
        this.prezo = prezo;
    }
    
    public Tendas datosProductos(){
        Scanner teclado = new Scanner (System.in);
        System.out.println("Nome do producto: ");
        this.nome = teclado.nextLine();
        System.out.println("Descripción");
        this.descripcion = teclado.nextLine();
        System.out.println("Prezo: ");
        this.prezo = teclado.nextLine();
        return new Tendas();
    }
     
    public void insertProducto(Connection con, String nome, String descripcion, String prezo){
        try{
            String sql = "INSERT INTO Productos (nome, descripcion, prezo) VALUES(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, nome);
            pstmt.setString(2, descripcion);
            pstmt.setString(3, prezo);
            pstmt.executeUpdate();
            System.out.println("Producto engadido con éxito");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
      
    public void consultarProductoFranquicia(Connection con){
         try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from Productos");
            while(rs.next()){
                System.out.println("Producto: \nNome: " + rs.getString("nome") + ", descripcion " + rs.getString("descripcion") + ", prezo" + rs.getString("prezo"));
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public void consultarProductoTenda(Connection con, String tendaConsulta){
        try{
            String sql = "select nomeProducto from ProductosTenda where nomeTenda = (?)";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, tendaConsulta);
            while(rs.next()){
                System.out.println("Producto: \nNome: " + rs.getString("nomeProducto"));
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
   
     
    public void deleteProducto(Connection con, String productoBorrar){
        try{
            String sql = "DELETE FROM Productos WHERE nome = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, productoBorrar);
            pstmt.executeUpdate();
            System.out.println("Producto borrado con éxito");
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public String productoSeleccionado(){
        Scanner teclado = new Scanner (System.in);
        System.out.println("Escribe o nome do producto que desexas borrar");
        return teclado.nextLine();
    }
}
