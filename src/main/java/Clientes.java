
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Clientes {
    String nomeCliente, apelidoCliente1, apelidoCliente2, emailCliente;

    public Clientes() {
    }

    public Clientes(String nomeCliente, String apelidoCliente1, String apelidoClinete2, String emailCliente) {
        this.nomeCliente = nomeCliente;
        this.apelidoCliente1 = apelidoCliente1;
        this.apelidoCliente2 = apelidoCliente2;
        this.emailCliente = emailCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getApelidoCliente1() {
        return apelidoCliente1;
    }

    public void setApelidoCliente1(String apelidoCliente1) {
        this.apelidoCliente1 = apelidoCliente1;
    }

    public String getApelidoCliente2() {
        return apelidoCliente2;
    }

    public void setApelidoCliente2(String apelidoCliente2) {
        this.apelidoCliente2 = apelidoCliente2;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }
    
    public void datosClientes(){
        Scanner teclado = new Scanner (System.in);
        System.out.println("Nome do cliente: ");
        this.nomeCliente = teclado.nextLine();
        System.out.println("Primeiro apelido do cliente: ");
        this.apelidoCliente1 = teclado.nextLine();
        System.out.println("Segundo apelido do cliente: ");
        this.apelidoCliente2 = teclado.nextLine();
        System.out.println("Email do cliente: ");
        this.emailCliente = teclado.nextLine();
    }
    
    public void insertCliente(Connection con){
        datosClientes();
        try{
            String sql = "INSERT INTO Clientes (nomeCliente, apelidoCliente1, apelidoCliente2, emailCliente) VALUES(?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, getNomeCliente());
            pstmt.setString(2, getApelidoCliente1());
            pstmt.setString(3,getApelidoCliente2());
            pstmt.setString(4, getEmailCliente());
            pstmt.executeUpdate();
            System.out.println("Cliente engadido con éxito");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
     
    public void consultarCliente(Connection con){
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from Clientes");
            while(rs.next()){
                System.out.println("Cliente = " + rs.getString("nomeCliente") + " " + rs.getString("apelidoCliente1") + " " + rs.getString("apelidoCliente2")
                + " " + rs.getString("emailCliente"));
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
     }
     
      public void deleteCliente(Connection con){
        try{
            Scanner teclado = new Scanner (System.in);
            String sql = "DELETE FROM Clientes WHERE nomeCliente= ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select nomeCliente from Clientes");
            while (rs.next()){
                System.out.println("Nome dos clientes: " + rs.getString("nomeCliente"));
            }
            }catch (SQLException e){
                    System.out.println("Non hai nomes que mostrar");
                    }
            System.out.println("Nome do cliente que desexa borrar: ");
            String clienteBorrar = teclado.nextLine();
            pstmt.setString(1, clienteBorrar);
            pstmt.executeUpdate();
            System.out.println("Cliente borrado con éxito");
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
     
     public String clienteSeleccionado(){
        Scanner teclado = new Scanner (System.in);
        System.out.println("Escribe o nome do cliente que desexas borrar");
        return teclado.nextLine();
     }
}
