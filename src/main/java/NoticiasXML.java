
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class NoticiasXML extends DefaultHandler{
    private ArrayList <Noticias> noticias;
    private Noticias noticiaAux;
    private String cadeaTexto;
    
    public NoticiasXML(){
        super();
    }
    
    @Override
    public void startDocument() throws SAXException{
        noticias = new ArrayList<>();
    }
    
    @Override
    public void endDocument() throws SAXException{    
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{    
        if ("title".equals(localName)){
            this.noticiaAux = new Noticias();
        }
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException{    
        if (localName.equals("title")){
            this.noticiaAux.setTitular(this.cadeaTexto);
            this.noticias.add(this.noticiaAux);      
        }
    }
    
    public void characters (char[] ch, int start, int length) throws SAXException{
        this.cadeaTexto = new String (ch,start,length);
    }    
    
    public ArrayList <Noticias> getNoticias(){
        return noticias;
    }    
}


