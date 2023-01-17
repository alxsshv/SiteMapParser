import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;


public class DocumentLoader {
    private static final String USER_AGENT = "Mozilla/5.0";

    public DocumentLoader() {
    }

    public static Document loadDocumentFrom(String url){
        try {
           return Jsoup.connect(url).userAgent(USER_AGENT).get();
        }
        catch(IllegalArgumentException ex){
            return new Document("url");
        }
        catch (IOException e) {
            return new Document("url");
        }
    }
}
