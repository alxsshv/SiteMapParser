

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

public class SiteMapBuildingTask extends RecursiveTask<List<String>> {
    private final Page page;
    public static volatile Set<String> verifiedPages = new HashSet<>();

    public SiteMapBuildingTask(Page page) {
        this.page = page;
    }

    @Override
    protected List<String> compute() {
        List <String> references = new ArrayList<>();
//        for(String page : verifiedPages){
//            System.out.println(page);
//        }
      if (!verifiedPages.contains(page.getURL()) && page.getURL().contains("skillbox.ru")){
            verifiedPages.add(page.getURL());
          System.out.println(page.getURL());
            references.add(page.getURL());
            references.addAll(page.getAllPageReferencesOnPage());
            List<SiteMapBuildingTask> tasks = new ArrayList<>();
            for (String childReference : page.getAllPageReferencesOnPage()){
                SiteMapBuildingTask task = new SiteMapBuildingTask(new HTMLPage(childReference));
                task.invoke();
            }
            for (SiteMapBuildingTask task : tasks){
                references.addAll(task.join());
            }
     }
        return references;

    }

//    public void parse(){
//        if (!verifiedPages.contains(URL)){
//            verifiedPages.add(URL);
//            getALLReferencesFromPage();
//            ;
//        }
//    }






}
