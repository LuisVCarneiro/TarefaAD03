
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Clientes {
    String nome, apelido1, apelido2, email;

    public Clientes() {
    }

    public Clientes(String nome, String apelido1, String apelido2, String email) {
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido1() {
        return apelido1;
    }

    public void setApelido1(String apelido1) {
        this.apelido1 = apelido1;
    }
    
    public String getApelido2() {
        return apelido2;
    }

    public void setApelido2(String apelido2) {
        this.apelido2 = apelido2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Clientes datosClientes(){
        Scanner teclado = new Scanner (System.in);
        System.out.println("Nome do cliente: ");
        this.nome = teclado.nextLine();
        System.out.println("Primeiro apelido do cliente: ");
        this.apelido1 = teclado.nextLine();
        System.out.println("Segundo apelido do cliente: ");
        this.apelido2 = teclado.nextLine();
        System.out.println("Email do cliente: ");
        this.email = teclado.nextLine();
        return new Clientes();
    }
    
    public void insertCliente(Connection con, String nome, String apelido1, String apelido2, String email){
        try{
            String sql = "INSERT INTO Clientes (nome, apelido1, apelido2, email) VALUES(?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, nome);
            pstmt.setString(2,apelido1);
            pstmt.setString(3,apelido2);
            pstmt.setString(4, email);
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
                System.out.println("Cliente = " + rs.getString("nome") + " " + rs.getString("apelido1") + " " + rs.getString("apelido2"));
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
     }
     
     public void deleteCliente(Connection con, String clienteBorrar){
        try{
            String sql = "DELETE FROM Clientes WHERE nome = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
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
