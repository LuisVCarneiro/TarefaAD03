
import java.io.File;
import java.sql.Connection;
import java.util.Scanner;


    public class Main {//A ver se esta é a version subida ó repositorio
        
        public static void main (String [] args){
            
        String db = "Franquicia";
        BaseDatos base = new BaseDatos();
        Clientes c = new Clientes();
        Tendas t = new Tendas();
        Productos p = new Productos();
        ProductosTenda pt = new ProductosTenda();
        Provincias prov = new Provincias();
        Empregados e = new Empregados();
        EmpregadosTenda et = new EmpregadosTenda();
        Connection con = null;
        String url = "baseDeDatos.db";
        File ruta = new File (url);
        if (!ruta.exists()){
        base.createDatabase(db);
        }
        con = base.connectDatabase(db);
            Scanner teclado = new Scanner (System.in);
            boolean salir = false;
            while (!salir){
                System.out.println("--------- MENU PRINCIPAL ----------\n1: Engadir nova tenda. \n2: Mostrar tendas. \n3: Eliminar tenda. \n4: Engadir producto. "
                        + "\n5: Mostrar os productos da franquicia. \n6: Mostrar os productos dunha tenda. \n7: Engadir un producto a unha tenda "
                        + "\n8: Actualizar o stock do producto dunha tenda. "
                        + "\n9: Mostrar o stock dun producto dunha tenda. \n10: Eliminar un producto dunha tenda. \n11: Eliminar producto."
                        + "\n12: Engadir un empregado. \n13: Mostrar empregados. \n14: Engadir un empregado a unha tenda. "
                        + "\n15: Eliminar un empregado dunha tenda. \n16: Eliminar un empregado. \n17: Mostrar os empregados dunha tenda. "
                        + "\n18: Engadir cliente. \n19: Mostrar clientes. \n20: Eliminar cliente. \n21: Leer RRSS. \n22: Salir");
                byte opcion = teclado.nextByte();
                if (opcion > 0 && opcion <23){
                switch (opcion){
                    case 1:
                        t.insertTenda(con);
                        break;
                    case 2:
                        t.consultarTenda(con);
                        break;
                    case 3:
                        t.deleteTenda(con);
                        break;
                    case 4:
                        p.insertProducto(con);
                        break;
                    case 5:
                        p.consultarProductoFranquicia(con);
                        break;
                    case 6:
                        pt.consultarProductoTenda(con);
                        break;
                    case 7:
                        pt.insertProductoTenda(con);
                        break;
                    case 8:
                        pt.actualizarStockProductoTenda(con);
                        break;
                    case 9:
                        pt.consultarStockProducto(con);
                        break;
                    case 10:
                        pt.deleteProductoTenda(con);
                        break;
                    case 11:
                        p.deleteProducto(con);
                        break;
                    case 12:
                        e.insertEmpregado(con);
                        break;
                    case 13:
                        e.consultarEmpregado(con);
                        break;
                    case 14:
                        et.insertEmpregadoTenda(con);
                        break;
                    case 15:
                        et.deleteEmpregadoTenda(con);
                        break;
                    case 16:
                        e.deleteEmpregado(con);
                        break;
                    case 17:
                        et.consultarEmpregadoTenda(con);
                        break;
                    case 18:
                        c.insertCliente(con);
                        break;
                    case 19:
                        c.consultarCliente(con);
                        break;
                    case 20:
                        c.deleteCliente(con);
                        break;
                    case 21:
                        LeerXML lectura = new LeerXML();
                        lectura.leerXml();
                        break;
                    case 22:
                        base.desconnectDatabase(con);
                        System.exit(0);
                        break;
                    }
                }
            }
        }
    
}
