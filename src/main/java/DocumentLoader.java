
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class DocumentLoader {
    private static final String USER_AGENT = "Mozilla/5.0";
    public static final Logger log = LogManager.getLogger(DocumentLoader.class);

    public DocumentLoader() {
    }

    public static Document loadDocumentFrom(String url){
        try {
           return Jsoup.connect(url).userAgent(USER_AGENT).get();
        } catch(IllegalArgumentException ex){
            log.info("Неверный формат страницы " + url);
            return new Document("url");
        }
        catch (IOException e) {
            log.info("Не удалось пропарсить страницу " + url);
            return new Document("url");
        }
    }
}
