
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;


public class Tendas {
    String nome, provincia, cidade;
    Scanner teclado = new Scanner (System.in);

    public Tendas() {
    }

    public Tendas(String nome, String provincia, String cidade) {
        this.nome = nome;
        this.provincia = provincia;
        this.cidade = cidade;
        
    }

    public String getNome() {
        return nome;
    }

    public void setNombre(String nome) {
        this.nome = nome;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public void datosTendas(){
        System.out.println("Nome da tenda: ");
        this.nome = teclado.nextLine();
        System.out.println("Cidade da tenda: ");
        this.cidade= teclado.nextLine();
        System.out.println("Provincia da tenda: ");
        this.provincia = teclado.nextLine();
    }

    public void insertTenda(Connection con){
        datosTendas();
        try{
            String sql = "INSERT INTO Tendas (nomeTenda, cidade, nomeProvincia) VALUES(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, getNome());
            pstmt.setString(2, getCidade());
            pstmt.setString(3, getProvincia());
            pstmt.executeUpdate();
            System.out.println("Tenda engadida con éxito");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
      
      
    public void consultarTenda(Connection con){
         try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from Tendas");
            while(rs.next()){
                System.out.println("Tenda: \nNome: " + rs.getString("nomeTenda") + ", cidade " + rs.getString("cidade") + ", provincia " + rs.getString("nomeProvincia"));
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
   
     
    public void deleteTenda(Connection con){
        try{
            try{
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery("Select nomeTenda from Tendas");
                while (rs.next()){
                    System.out.println(rs.getString("nomeTenda") + "\n");
                }
            }catch (SQLException e){
                        System.out.println("Non se poden ler as tendas");
                        }
            String sql = "DELETE FROM Tendas WHERE nomeTenda = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            System.out.println("Qué tenda queres eliminar?");
            String nomeBorrar = teclado.nextLine();
            pstmt.setString(1, nomeBorrar);
            pstmt.executeUpdate();
            System.out.println("Tenda borrada con éxito");
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public void listarTendas(Connection con){
         try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from Tendas");
             System.out.println("Listado de tendas: ");
            while(rs.next()){
                System.out.println(rs.getString("nomeTenda"));
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
