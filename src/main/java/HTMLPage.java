import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class HTMLPage implements Page{
    private final String url;
    private List<Page> childPages;
    private List<String> pageMap;
    private int hierarchyLevel;

    public HTMLPage(String url, int level) {
        this.url = url;
        this.childPages = new ArrayList<>();
        this.pageMap = new ArrayList<>();
        this.hierarchyLevel = level;
    }


    @Override
    public String getURL() {
        return url;
    }

    @Override
    public List<Page> getChildPages() {
            try {
                Thread.sleep(10);
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



    public void setChildPages(List<Page> childPages) {
        this.childPages = childPages;
    }

    public List<String> getPageMap() {
        getChildPages(); // bad construction, findPageMapAndGetPageMap
        for (Page childPage : childPages){
            pageMap.add(" ".repeat(childPage.getHierarchyLevel()).concat(childPage.getURL()));
        }
        return pageMap;
    }

    public void setPageMap(List<String> pageMap) {
        this.pageMap = pageMap;
    }

    public int getHierarchyLevel() {
        return hierarchyLevel;
    }
    @Override
    public void setHierarchyLevel() {
        hierarchyLevel = hierarchyLevel;
    }
}
