
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
                crearTaboaTendas(con);
                crearTaboaProductos(con);
                crearTaboaClientes(con);
                crearTaboaEmpregados(con);
                crearTaboaProductosTendas(con);
                crearTaboaProvincias(con);
                Provincias prov = new Provincias();
                prov.insertProvincia(con, 0, db);
                crearTaboaEmpregadosTenda(con);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
    }  
}
    
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
    
    public void crearTaboaTendas(Connection con){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS Tendas ("
                    + "nomeTenda text PRIMARY KEY NOT NULL,"
                    + "cidade text,"
                    + "nomeProvincia text,"
                    + "foreign key (nomeProvincia) references Provincias (nomeProvincia)"
                    + ");";
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Táboa Tendas creada correctamente");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void crearTaboaProductosTendas(Connection con){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS ProductosTenda  (\n"
                    + "nomeTenda text NOT NULL,"
                    + "nomeProducto text NOT NULL, \n"
                    + "stock integer, \n"
                    + "foreign key (nomeProducto) references Productos(nomeProducto), \n"
                    + "foreign key (nomeTenda) references Tendas(nomeTenda)"
                    + ");";
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Táboa ProductosTenda creada correctamente");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void crearTaboaProductos(Connection con){
        try{
            String sql ="CREATE TABLE IF NOT EXISTS Productos ("
                    + "nomeProducto text PRIMARY KEY,"
                    + "descripcion text NOT NULL,"
                    + "prezo Double"
                    + ");";
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Táboa Productos creada correctamente");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void crearTaboaEmpregados(Connection con){
        try{
            String sql ="CREATE TABLE IF NOT EXISTS Empregados (\n"
                    + "nomeEmpregado PRIMARY KEY, \n"
                    + "apelidosEmpregado text NOT NULL \n"
                    + ");";
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Táboa Empregados creada correctamente");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void crearTaboaClientes(Connection con){
        
        try{
            String sql ="CREATE TABLE IF NOT EXISTS Clientes (\n"
                    + "nomeCliente text, \n"
                    + "apelidoCliente1 text, \n"
                    + "apelidoCliente2 text, \n"
                    + "emailCliente text"
                    + ");";
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Táboa Clientes creada correctamente");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void crearTaboaProvincias(Connection con){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS Provincias (\n"
                    + "id integer PRIMARY KEY,"
                    + "nomeProvincia text"
                    + ");";
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Táboa Provincias creada correctamente");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void crearTaboaEmpregadosTenda(Connection con){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS EmpregadosTenda (\n"
                    + "nomeEmpregado PRIMARY KEY, \n"
                    + "nomeTenda text NOT NULL, \n"
                    + "horasSemanais integer, \n"
                    + "foreign key (nomeEmpregado) references Empregados(nomeEmpregado), \n"
                    + "foreign key (nomeTenda) references Tendas (nomeTenda)"
                    + ");";
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Táboa EmpregadosTenda creada correctamente");
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
