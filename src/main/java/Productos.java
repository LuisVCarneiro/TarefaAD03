
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Productos {
    String nome, descripcion;
    double prezo;
    Scanner teclado = new Scanner (System.in);

    public Productos() {
    }

    public Productos(String nome, String descripcion, double prezo) {
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

    public double getPrezo() {
        return prezo;
    }

    public void setPrezo(double prezo) {
        this.prezo = prezo;
    }
    
    public void datosProductos(){
        System.out.println("Nome do producto: ");
        this.nome = teclado.nextLine();
        System.out.println("Descripción");
        this.descripcion = teclado.nextLine();
        System.out.println("Prezo: ");
        this.prezo = teclado.nextDouble();
    }
    
    public void insertProducto(Connection con){
        datosProductos();
        try{
            String sql = "INSERT INTO Productos (nomeProducto, descripcion, prezo) VALUES(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, getNome());
            pstmt.setString(2, getDescripcion());
            pstmt.setDouble(3, getPrezo());
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
                System.out.println("Producto: \nNome: " + rs.getString("nomeProducto") + ", descripcion: " + rs.getString("descripcion") + ", prezo: " + rs.getString("prezo"));
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
     
    public void deleteProducto(Connection con){
          try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from Productos");
            while(rs.next()){
                System.out.println("Producto: \nNome: " + rs.getString("nomeProducto"));
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        try{
            String sql = "DELETE FROM Productos WHERE nomeProducto = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            System.out.println("Que producto queres borrar da franquicia?");
            String productoBorrar = teclado.nextLine();
            pstmt.setString(1, productoBorrar);
            pstmt.executeUpdate();
            System.out.println("Producto borrado con éxito");
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
