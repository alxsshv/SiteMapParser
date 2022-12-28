import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class HTMLPage implements Page{
    private final String url;


    public HTMLPage(String url) {
        this.url = url;
    }

    @Override
    public List<String> getAllPageReferencesOnPage() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Document document = DocumentLoader.loadDocumentFrom(url);
        Elements references = document.select("a ");
        List <String> urls = new ArrayList<>();
            for (Element reference : references){
                if (reference.attr("href").contains("https")){
                    urls.add("\t" + reference.attr("href").replaceAll(" ", ""));
                }
            }
        return urls;
    }



    @Override
    public String getURL() {
        return url;
    }
}
