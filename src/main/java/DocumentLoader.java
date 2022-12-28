import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class DocumentLoader {
    private static final String USER_AGENT = "Mozilla/5.0";
    public static Document loadDocumentFrom(String url){
        try {
           return Jsoup.connect(url).userAgent(USER_AGENT).get();
        } catch(IllegalArgumentException ex){
            System.out.println("Неверный формат страницы " + url);
            return new Document("url");
        }
        catch (IOException e) {
            System.out.println("Не удалось пропарсить страницу " + url);
            return new Document("url");// throw new RuntimeException(e);
        }
    }
}
