
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class EmpregadosTenda {
    String nomeEmpregado, nomeTenda;
    int horasSemanais;
    Scanner teclado = new Scanner (System.in);

    public EmpregadosTenda() {
    }

    public EmpregadosTenda(String nomeEmpregado, String nomeTenda, int horasSemanais) {
        this.nomeEmpregado = nomeEmpregado;
        this.nomeTenda = nomeTenda;
        this.horasSemanais = horasSemanais;
    }

    public String getNomeEmpregado() {
        return nomeEmpregado;
    }

    public void setNomeEmpregado(String nomeEmpregado) {
        this.nomeEmpregado = nomeEmpregado;
    }

    public String getNomeTenda() {
        return nomeTenda;
    }

    public void setNomeTenda(String nomeTenda) {
        this.nomeTenda = nomeTenda;
    }

    public int getHorasSemanais() {
        return horasSemanais;
    }

    public void setHorasSemanais(int horasSemanais) {
        this.horasSemanais = horasSemanais;
    }
    
    public void datosEmpregadosTenda(){
        System.out.println("Nome da tenda na que engadir o empregado: ");
        this.nomeTenda = teclado.nextLine();
        System.out.println("Nome do empregado: ");
        this.nomeEmpregado= teclado.nextLine();
        System.out.println("Horas semanais que traballa o empregado: ");
        this.horasSemanais = teclado.nextInt();
    }
    
    public void insertEmpregadoTenda(Connection con){
        try{
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery("Select nomeTenda from Tendas");
                while (rs.next()){
                    System.out.println(rs.getString("nomeTenda") + "\n");
                }
            }catch (SQLException e){
                        System.out.println("Non se poden ler as tendas");
                        }
         System.out.println("Indica o nome da tenda na que engadir o empregado");
         this.nomeTenda = teclado.nextLine();
         System.out.println("Qué empregado queres engadir?");
         this.nomeEmpregado = teclado.nextLine();
         System.out.println("Horas semanais: ");
         this.horasSemanais = teclado.nextInt();
         
        try{
            String sql = "INSERT INTO EmpregadosTenda (nomeTenda, nomeEmpregado, horasSemanais) VALUES(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setString(1, nomeTenda);
            pstmt.setString(2, nomeEmpregado);
            pstmt.setInt(3, horasSemanais);
            pstmt.executeUpdate();
            System.out.println("Empregado engadido");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteEmpregadoTenda(Connection con){
        Tendas tenda = new Tendas();
        tenda.listarTendas(con);
        System.out.println("De qué tenda queres eliminar o empregado?");
        this.nomeTenda = teclado.nextLine();
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select nomeEmpregado from EmpregadosTenda where nomeTenda = '" + getNomeTenda() + "';");
            while(rs.next()){
                System.out.println("Os empregados desta tenda son: ");
                System.out.println(rs.getString("nomeEmpregado") + "\n");
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
            System.out.println("Cal é o empregado que desexas eliminar?");
            this.nomeEmpregado = teclado.nextLine();
            try{
                Statement statement = con.createStatement();
                String sql = "delete from EmpregadosTenda where nomeTenda = ? and nomeEmpregado = ?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, getNomeTenda());
                pstmt.setString(2, getNomeEmpregado());
                pstmt.executeUpdate();
                System.out.println("Empregado eliminado");
            }
            catch(SQLException e){
                System.err.println(e.getMessage());
            }
    }
    
    public void consultarEmpregadoTenda(Connection con){
       Tendas tenda = new Tendas();
       tenda.listarTendas(con);
        System.out.println("De qué tenda queres consultar os empregados?");
        this.nomeTenda = teclado.nextLine();
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select nomeEmpregado from EmpregadosTenda where nomeTenda = '" + getNomeTenda() + "';");
            System.out.println("Empregados da tenda: ");
            while(rs.next()){
                System.out.println(rs.getString("nomeEmpregado"));
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public void consultarHorasSemanais(Connection con){
        Empregados e = new Empregados ();
        e.listarEmpregados(con);
        System.out.println("De qué Empregado queres consultar as horas?");
        this.nomeTenda = teclado.nextLine();
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select horasSemanais from EmpregadosTenda where nomeEmpregado = '" + getNomeEmpregado() + "';");
            while(rs.next()){
                System.out.println("As horas semanais do empregado " + getNomeEmpregado() + " son: ");
                System.out.println(rs.getString("horasSemanais") + "\n");
            }
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }     
    }
}