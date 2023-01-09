import java.util.List;

public interface Page {
    String getURL();
    List<Page> getChildPages();
    void setHierarchyLevel();
    int getHierarchyLevel();
    List<String> getPageMap();
}
