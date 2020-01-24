
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class ProductosTenda {
    String nomeTenda, nomeProducto;
    int stock;
    Scanner teclado = new Scanner (System.in);

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
    
    public void insertProductoTenda(Connection con){
        try{
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery("Select nomeTenda from Tendas");
                while (rs.next()){
                    System.out.println(rs.getString("nomeTenda") + "\n");
                }
            }catch (SQLException e){
                        System.out.println("Non se poden ler as tendas");
                        }
         System.out.println("Indica o nome da tenda na que engadir o producto");
         this.nomeTenda = teclado.nextLine();
         System.out.println("Qué producto queres engadir?");
         this.nomeProducto = teclado.nextLine();
         System.out.println("Stock inicial: ");
         this.stock = teclado.nextInt();
         
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
    
    public void deleteProductoTenda(Connection con){
        Tendas tenda = new Tendas();
        tenda.listarTendas(con);
        System.out.println("De qué tenda queres eliminar o producto?");
        this.nomeTenda = teclado.nextLine();
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select nomeProducto from ProductosTenda where nomeTenda = '" + getNomeTenda() + "';");
            while(rs.next()){
                System.out.println("Os productos desta tenda son: ");
                System.out.println(rs.getString("nomeProducto") + "\n");
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
            System.out.println("Cal é o producto do que desexas eliminar?");
            this.nomeProducto = teclado.nextLine();
            try{
                Statement statement = con.createStatement();
                String sql = "delete from ProductosTenda where nomeTenda = ? and nomeProducto = ?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, getNomeTenda());
                pstmt.setString(2, getNomeProducto());
                pstmt.executeUpdate();
                System.out.println("Producto eliminado");
            }
            catch(SQLException e){
                System.err.println(e.getMessage());
            }
    }
    
    public void consultarProductoTenda(Connection con){
       Tendas tenda = new Tendas();
       tenda.listarTendas(con);
        System.out.println("De qué tenda queres consultar os productos?");
        this.nomeTenda = teclado.nextLine();
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select nomeProducto from ProductosTenda where nomeTenda = '" + getNomeTenda() + "';");
            System.out.println("Productos da tenda: ");
            while(rs.next()){
                System.out.println(rs.getString("nomeProducto"));
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public void consultarStockProducto(Connection con){
        Tendas tenda = new Tendas();
        tenda.listarTendas(con);
        System.out.println("De qué tenda queres consultar o stock de productos?");
        this.nomeTenda = teclado.nextLine();
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select nomeProducto from ProductosTenda where nomeTenda = '" + getNomeTenda() + "';");
            while(rs.next()){
                System.out.println("Os productos desta tenda son: ");
                System.out.println(rs.getString("nomeProducto") + "\n");
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
            System.out.println("Cal é o producto do que desexas saber o stock?");
            this.nomeProducto = teclado.nextLine();
            try{
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery("select stock from ProductosTenda where nomeTenda = '" + getNomeTenda() + "' and nomeProducto = '" + getNomeProducto() + "';");
                while(rs.next()){
                    System.out.println(rs.getString("stock") + " unidades.");
                }
            }
            catch(SQLException e){
                System.err.println(e.getMessage());
            }
        
    }
    
    public void actualizarStockProductoTenda(Connection con){
        Tendas t = new Tendas();
        t.listarTendas(con);
        System.out.println("De qué tenda queres actualizar o stock de productos?");
        this.nomeTenda = teclado.nextLine();
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select nomeProducto from ProductosTenda where nomeTenda = '" + getNomeTenda() + "';");
            while(rs.next()){
                System.out.println("Os productos desta tenda son: ");
                System.out.println(rs.getString("nomeProducto") + "\n");
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
            System.out.println("Cal é o producto do que desexas actualizar o stock?");
            this.nomeProducto = teclado.nextLine();
            System.out.println("Cal é a cantidade a actualizar?");
            this.stock = teclado.nextInt();
            try{
                Statement statement = con.createStatement();
                String sql = "update productosTenda set stock = ? where nomeTenda = ? and nomeProducto = ? ;";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, stock);
                pstmt.setString(2, nomeTenda);
                pstmt.setString(3, nomeProducto);
                pstmt.executeUpdate();
                System.out.println("Stock actualizado.");
            }
            catch(SQLException e){
                System.err.println(e.getMessage());
            }
    }
}
