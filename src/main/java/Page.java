import java.util.List;

public interface Page {
    String getURL();
    List<Page> getChildPages();
    int getHierarchyLevel();

}
