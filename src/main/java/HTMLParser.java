import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HTMLParser {
    private String url = "https://habr.com/";
    private String userAgent = "Mozilla/5.0 (X11; Linux x86_64; rv:107.0)";

    public void getDocument(){
        Document document;
        try {
            document = Jsoup.connect(url).userAgent(userAgent).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements listUrl = document.select("https");
        for (Element url : listUrl){
            System.out.println(url.text());
        }
    }

}
