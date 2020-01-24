
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Empregados {
    String nomeEmpregado, apelidosEmpregado;
    Scanner teclado = new Scanner (System.in);

    public Empregados() {
    }

    public Empregados(String nomeEmpregado, String apelidosEmpregado) {
        this.nomeEmpregado = nomeEmpregado;
        this.apelidosEmpregado = apelidosEmpregado;
    }

    public String getNomeEmpregado() {
        return nomeEmpregado;
    }

    public void setNomeEmpregado(String nomeEmpregado) {
        this.nomeEmpregado = nomeEmpregado;
    }

    public String getApelidosEmpregado() {
        return apelidosEmpregado;
    }

    public void setApelidosEmpregado(String apelidosEmpregado) {
        this.apelidosEmpregado = apelidosEmpregado;
    }
    
    public void datosEmpregados(){
        System.out.println("Nome do empregado: ");
        this.nomeEmpregado = teclado.nextLine();
        System.out.println("Apelidos do empregado: ");
        this.apelidosEmpregado = teclado.nextLine();
    }
    
    public void insertEmpregado(Connection con){
        datosEmpregados();
        try{
            String sql = "INSERT INTO Empregados (nomeEmpregado, apelidosEmpregado) VALUES(?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, getNomeEmpregado());
            pstmt.setString(2, getApelidosEmpregado());
            pstmt.executeUpdate();
            System.out.println("Empregado engadido con éxito");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
      
      
    public void consultarEmpregado(Connection con){
         try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from Empregados");
            while(rs.next()){
                System.out.println("Tenda: \nNomeEmpregado: " + rs.getString("nomeEmpregado") + ", apelidos:  " + rs.getString("apelidosEmpregado"));
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
   
     
    public void deleteEmpregado(Connection con){
        try{
            try{
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery("Select nomeEmpregado from Empregados");
                while (rs.next()){
                    System.out.println(rs.getString("nomeEmpregado") + "\n");
                }
            }catch (SQLException e){
                        System.out.println("Non se poden ler os empregados");
                        }
            String sql = "DELETE FROM Empregados WHERE nomeEmpregado = (?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            System.out.println("Qué empregado queres eliminar?");
            String nomeBorrar = teclado.nextLine();
            pstmt.setString(1, nomeBorrar);
            pstmt.executeUpdate();
            System.out.println("Empregado borrado con éxito");
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public void listarEmpregados(Connection con){
         try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from Empregados");
             System.out.println("Listado de empregados: ");
            while(rs.next()){
                System.out.println(rs.getString("nomeEmpregado"));
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
