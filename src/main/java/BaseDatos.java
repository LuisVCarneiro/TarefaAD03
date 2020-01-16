
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class BaseDatos {
    
    Scanner teclado = new Scanner (System.in);
    private String ruta = "baseDeDatos.db";
    File baseDeDatos = new File(ruta);
    
    public void createDatabase (String filename){  
        String databaseFile = "jdbc:sqlite:" + baseDeDatos;
        try {
            Connection connection = DriverManager.getConnection(databaseFile);
            if (connection != null){
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("A base de datos foi creada");
                String db = "Franquicia";
                Connection con = connectDatabase(db);
                crearTaboaFranquicia(con);
                crearTaboaTendas(con);
                crearTaboaProductos(con);
                crearTaboaClientes(con);
                crearTaboaEmpregados(con);
                crearTaboaProductosTendas(con);
                crearTaboaProvincias(con);
                crearTaboaEmpregadosTenda(con);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
    }  
}
    
    //Conecta á base de datos coa ruta atributo de getConnection
    public Connection connectDatabase(String filename){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:" + baseDeDatos);
            System.out.println("Conexión realizada con éxito");
            return connection;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    

    public void desconnectDatabase(Connection connection){
        try{
            if (connection != null){
                connection.close();
                System.out.println("Conexión cerrada con éxito");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void crearTaboaFranquicia(Connection con){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS Franquicia ("
                    + "nomeTendas text PRIMARY KEY,"
                    + "nomeClientes text"
                    + ");";
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Taboa Franquicias creada.");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void crearTaboaTendas(Connection con){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS Tendas ("
                    + "nome text PRIMARY KEY NOT NULL,"
                    + "cidade VARCHAR(20) NOT NULL,"
                    + "provincia VARCHAR (25) NOT NULL,"
                    + "nomeEmpregado VARCHAR (10)"
                    + ");";
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Taboa Tendas creada satisfactoriamente");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void crearTaboaProductosTendas(Connection con){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS ProductosTenda  (\n"
                    + "nomeTenda text PRIMARY KEY,"
                    + "nomeProducto text NOT NULL, \n"
                    + "stock integer NOT NULL \n"
                    + ");";
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Táboa ProductosTendas creada satisfactoriamente");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void crearTaboaProductos(Connection con){
        try{
            String sql ="CREATE TABLE IF NOT EXISTS Productos ("
                    + "nome VARCHAR2(10) PRIMARY KEY NOT NULL,"
                    + "descripcion VARCHAR2(25) NOT NULL,"
                    + "prezo INTEGER NOT NULL"
                    + ");";
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Taboa Productos creada satisfactoriamente");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void crearTaboaEmpregados(Connection con){
        try{
            String sql ="CREATE TABLE IF NOT EXISTS Empregados (\n"
                    + "nome PRIMARY KEY, \n"
                    + "apelidos text NOT NULL \n"
                    + ");";
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Taboa Empregados creada satisfactoriamente");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void crearTaboaClientes(Connection con){
        
        try{
            String sql ="CREATE TABLE IF NOT EXISTS Clientes (\n"
                    + "nome text PRIMARY KEY, \n"
                    + "apelido1 text NOT NULL, \n"
                    + "apelido2 text NOT NULL, \n"
                    + "email text NOT NULL"
                    + ");";
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Taboa Clientes creada satisfactoriamente");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void crearTaboaProvincias(Connection con){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS Provincias (\n"
                    + "id integer PRIMARY KEY,"
                    + "nome text NOT NULL"
                    + ");";
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Taboa Provincias creada satisfactoriamente");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void crearTaboaEmpregadosTenda(Connection con){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS EmpregadosTenda (\n"
                    + "nomeEmpregado PRIMARY KEY,"
                    + "nomeTenda text NOT NULL,"
                    + "horasSemanais integer NOT NULL"
                    + ");";
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Taboa EmpregadosTenda creada satisfactoriamente");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void modificar(Connection con){
        try{
            System.out.println("Introduce a sentencia SQL a executar");
            String sql = teclado.nextLine();
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Sentencia executada");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
