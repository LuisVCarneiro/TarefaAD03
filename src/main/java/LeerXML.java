
import java.io.IOException;
import java.util.ArrayList;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class LeerXML {
     XMLReader procesadorXML = null;
    
    public void leerXml(){
                    try {
                        //Creo parseador de texto e engádolle a clase que vai parsear o texto:
                        procesadorXML = XMLReaderFactory.createXMLReader();
                        NoticiasXML noticiaXML = new NoticiasXML();
                        procesadorXML.setContentHandler((ContentHandler) noticiaXML);

                        //Indico o texto donde estága gardada a noticia
                        InputSource arquivoXML = new InputSource("http://ep00.epimg.net/rss/elpais/portada.xml");
                        procesadorXML.parse(arquivoXML);

                        //Imprimo os datos lidos no XML
                        ArrayList <Noticias> noticias = noticiaXML.getNoticias();
                        for (int j = 0; j < noticias.size(); j++){
                            Noticias noticiaAux = noticias.get(j);
                            System.out.println("Noticia: "  + noticiaAux.getTitular());
                        }
                    }
                    catch (SAXException saxe){
                            System.out.println ("Ocurriu un erro ao ler o arquivo XML");
                            }
                    catch (IOException ioe){
                            System.out.println ("Ocurriu outro erro ao ler o arquivo XML");
                            }
    }
}

