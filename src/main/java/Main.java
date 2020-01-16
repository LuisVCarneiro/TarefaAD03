
import java.io.File;
import java.sql.Connection;
import java.util.Scanner;


    public class Main {
        
        public static void main (String [] args){
            
        String db = "Franquicia";
        BaseDatos base = new BaseDatos();
        
        String url = "baseDeDatos.db";
        File ruta = new File (url);
        if (!ruta.exists()){
        base.createDatabase(db);
        }
        Connection con = base.connectDatabase(db);
            Scanner teclado = new Scanner (System.in);
            boolean salir = false;
            while (!salir){
                System.out.println("--------- MENU PRINCIPAL ----------\n1: Engadir nova tenda. \n2: Mostrar tendas. \n3: Eliminar tenda. \n4: Engadir producto. "
                        + "\n5: Mostrar os productos da franquicia. \n6: Mostrar os productos dunha tenda. \n7: Engadir un producto a unha tenda "
                        + "\n8: Actualizar o stock do producto dunha tenda. "
                        + "\n9: Mostrar o stock dun producto dunha tenda. \n10: Eliminar un producto dunha tenda. \n11: Eliminar produto."
                        + "\n12: Engadir cliente. \n13: Mostrar clientes. \n14: Eliminar cliente. \n15: Leer RRSS. \n16: Salir. \n17: Pruebas");
                byte opcion = teclado.nextByte();
                if (opcion > 0 && opcion <18){
                switch (opcion){
                    case 1:
                        Tendas t = new Tendas();
                        t.datosTendas();
                        t.insertTenda(con, t.getNome(), t.getCidade(), t.getProvincia(), t.getNomeEmpregados());
                        break;
                    case 2:
                        Tendas t2 = new Tendas();
                        t2.consultarTenda(con);
                        break;
                    case 3:
                        Tendas t3 = new Tendas();
                        String tendaBorrar = t3.tendaSeleccionada();
                        t3.deleteTenda(con, tendaBorrar);
                        break;
                    case 4:
                        Productos p = new Productos();
                        p.datosProductos();
                        p.insertProducto(con, p.getNome(), p.getDescripcion(), p.getPrezo());
                        break;
                    case 5:
                        Productos p5 = new Productos();
                        p5.consultarProductoFranquicia(con);
                        break;
                    case 6:
                        Productos p6 = new Productos();
                        Tendas t6 = new Tendas();
                        String tendaConsulta = t6.tendaSeleccionada();
                        p6.consultarProductoTenda(con, tendaConsulta);
                        break;
                    case 7:
                        ProductosTenda pt = new ProductosTenda();
                        pt.datosProductosTenda();
                        pt.insertProductoTenda(con, pt.getNomeTenda(), pt.getNomeProducto(), pt.getStock());
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        ProductosTenda pt10 = new ProductosTenda();
                        String productoTendaBorrar = pt10.productoTendaSeleccionado();
                        pt10.deleteProductoTenda(con, productoTendaBorrar);
                        break;
                    case 11:
                        Productos p11 = new Productos();
                        String productoBorrar = p11.productoSeleccionado();
                        p11.deleteProducto(con, productoBorrar);
                        break;
                    case 12:
                        Clientes c12 = new Clientes();
                        c12.datosClientes();
                        c12.insertCliente(con,c12.getNome(),c12.getApelido1(),c12.getApelido2(),c12.getEmail());
                        break;
                    case 13:
                        Clientes c13 = new Clientes();
                        c13.consultarCliente(con);
                        break;
                    case 14:
                        Clientes c14 = new Clientes();
                        String clienteBorrar = c14.clienteSeleccionado();
                        c14.deleteCliente(con,clienteBorrar);
                        break;
                    case 15:
                        LeerXML lectura = new LeerXML();
                        lectura.leerXml();
                        break;
                    case 16:
                        base.desconnectDatabase(con);
                        System.exit(0);
                        break;
                    case 17:
                        base.modificar(con);
                        break;
                    }
                }
            }
        }
    
}
