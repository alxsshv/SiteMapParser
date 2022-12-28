import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        Page site = new HTMLPage("https://skillbox.ru/");
        SiteMapBuildingTask parser = new SiteMapBuildingTask(site);
        List<String> urls = new ForkJoinPool().invoke(parser);
        for (String url : urls){
            System.out.println(url);
        }
        System.out.println("Содержимое статической переменной");
        for (String page : SiteMapBuildingTask.verifiedPages){
            System.out.println(page);
        }
    }
}
