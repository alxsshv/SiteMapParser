import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class HTMLPage implements Page{
    private final String url;
    private final List<Page> childPages;
    private final int hierarchyLevel;

    public HTMLPage(String url, int level) {
        this.url = url;
        this.childPages = new ArrayList<>();
        this.hierarchyLevel = level;
    }


    @Override
    public String getURL() {
        return url;
    }

    @Override
    public List<Page> getChildPages() {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Document document = DocumentLoader.loadDocumentFrom(url);
            Elements references = document.select("a ");
            for (Element reference : references){
                if (reference.attr("href").contains("https")){
                    childPages.add(new HTMLPage(reference.attr("href").replaceAll(" ", ""),hierarchyLevel+1));
                }
            }
            return childPages;
        }


    public int getHierarchyLevel() {
        return hierarchyLevel;
    }
}
