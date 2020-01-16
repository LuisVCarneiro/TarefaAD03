
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;


public class Tendas {
    String nome, provincia, cidade, nomeEmpregados;

    public Tendas() {
    }

    public Tendas(String nome, String provincia, String cidade, String nomeEmpregados) {
        this.nome = nome;
        this.provincia = provincia;
        this.cidade = cidade;
        this.nomeEmpregados = nomeEmpregados;
        
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

    public String getNomeEmpregados() {
        return nomeEmpregados;
    }

    public void setEmpregados(String empregados) {
        this.nomeEmpregados = nomeEmpregados;
    }
    
    public Tendas datosTendas(){
        Scanner teclado = new Scanner (System.in);
        System.out.println("Nome da tenda: ");
        this.nome = teclado.nextLine();
        System.out.println("Cidade da tenda: ");
        this.cidade= teclado.nextLine();
        System.out.println("Provincia da tenda: ");
        this.provincia = teclado.nextLine();
        System.out.println("Empregado da tenda: ");
        this.nomeEmpregados = teclado.nextLine();
        return new Tendas();
    }
     
    public void insertTenda(Connection con, String nome, String cidade, String provincia, String nomeEmpregado){
        try{
            String sql = "INSERT INTO Tendas (nome, cidade, provincia, nomeEmpregado) VALUES(?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, nome);
            pstmt.setString(2, cidade);
            pstmt.setString(3, provincia);
            pstmt.setString(4, nomeEmpregado);
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
                System.out.println("Tenda: \nNome: " + rs.getString("nome") + ", cidade " + rs.getString("cidade") + ", provincia " + rs.getString("provincia") +
                        ", empregado " + 
                        rs.getString("nomeEmpregado"));
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
   
     
    public void deleteTenda(Connection con, String nomeBorrar){
        try{
            String sql = "DELETE FROM Tendas WHERE nome = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nomeBorrar);
            pstmt.executeUpdate();
            System.out.println("Tenda borrada con éxito");
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    
    public String tendaSeleccionada(){
        Scanner teclado = new Scanner (System.in);
        System.out.println("Escribe o nome da tenda que desexas borrar");
        return teclado.nextLine();
    }
}
