
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;


public class Provincias {
    int id;
    String nome;
    String FILENAME = new String ("provincias.json"); 
    File arquivo = new File (FILENAME);
    ArrayList <Provincias> provincias = null;
    Provincias provinciaAux;

    public Provincias() {
    }

    public Provincias(int id, String nome) {
        this.id = id;
        this.nome= nome;
        this.provincias = new ArrayList <>();
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Provincias> getProvincias() {
        return provincias;
    }

    public void setProvincias(ArrayList<Provincias> provincias) {
        this.provincias = provincias;
    }

    
    
    public void lerProvincias(){    
        try{

            FileReader fluxoDatos = new FileReader(arquivo);
            BufferedReader buferEntrada = new BufferedReader(fluxoDatos);

            StringBuilder jsonBuilder = new StringBuilder();
            String linea;
            while ((linea=buferEntrada.readLine()) != null) {
                jsonBuilder.append(linea).append("\n");
            }
            buferEntrada.close();

            String json = jsonBuilder.toString();
            Gson gson = new Gson();
            Provincias p = gson.fromJson(json, Provincias.class);
            System.out.println("Provincias: " );
            for (int i = 0; i < p.provincias.size(); i++){
                provinciaAux = p.getProvincias().get(i);
                System.out.println(provinciaAux.getId() + " " + provinciaAux.getNome());
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    } 
    
    public void insertProvincia(Connection con, int id, String nome){
        try{
            FileReader fluxoDatos = new FileReader(arquivo);
            BufferedReader buferEntrada = new BufferedReader(fluxoDatos);

            StringBuilder jsonBuilder = new StringBuilder();
            String linea;
            while ((linea=buferEntrada.readLine()) != null) {
                jsonBuilder.append(linea).append("\n");
            }
            buferEntrada.close();

            String json = jsonBuilder.toString();
            Gson gson = new Gson();
            Provincias p = gson.fromJson(json, Provincias.class);
            //System.out.println("Provincias: " );
            String sql = "INSERT INTO Provincias (id, nomeProvincia) VALUES (?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            for (int i = 0; i < p.provincias.size(); i++){
                provinciaAux = p.getProvincias().get(i);
                pstmt.setInt(1,provinciaAux.getId());
                pstmt.setString(2, provinciaAux.getNome());
                pstmt.executeUpdate();
               // System.out.println("Provincia introducida correctamente");
            }
            System.out.println("Provincias insertadas");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
