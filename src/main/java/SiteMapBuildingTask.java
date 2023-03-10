import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RecursiveTask;

public class SiteMapBuildingTask extends RecursiveTask<List<String>> {

    private final String url;
    private final Page page;
    public static volatile Set<String> verifiedPages = ConcurrentHashMap.newKeySet();

    public SiteMapBuildingTask(Page page, String url) {
        this.page = page;
        this.url = url;
    }

    @Override
    protected List<String> compute() {
        List <String> siteMap = new ArrayList<>();
      if (!verifiedPages.contains(page.getURL()) && page.getURL().contains(url)){
          verifiedPages.add(page.getURL());
          siteMap.add("\t".repeat(page.getHierarchyLevel()).concat(page.getURL()));
          List<SiteMapBuildingTask> tasks = new ArrayList<>();
          for (Page childPage : page.getChildPages()){
             SiteMapBuildingTask task = new SiteMapBuildingTask(childPage, url);
             task.fork();
             tasks.add(task);
            }
            for (SiteMapBuildingTask task : tasks){
                siteMap.addAll(task.join());
            }
     }
        return siteMap;

    }

}
