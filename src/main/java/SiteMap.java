import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class SiteMap {
    private static final String NEW_LINE = System.lineSeparator();
    private final StringBuilder siteMapBuilder = new StringBuilder();
    public void buildSiteMap(String url){
        System.out.println("Формируем карту сайта ...");
        Page site = new HTMLPage(url, 0);
        SiteMapBuildingTask parser = new SiteMapBuildingTask(site, url);
        List<String> references = new ForkJoinPool().invoke(parser);
        System.out.println("Карта сайта");
        for (String reference : references){
            siteMapBuilder.append(reference.concat(NEW_LINE));
            System.out.println(reference);
        }
    }
    public void writeSiteMapToFile(String filename){
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(siteMapBuilder.toString());
            writer.flush();
            writer.close();
            System.out.println("Файл успешно записан");
        } catch (IOException e) {
            System.out.println("При сохранении файла возникла ошибка");
            throw new RuntimeException(e);
        }
    }
}
