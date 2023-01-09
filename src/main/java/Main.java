import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    // todo сделать фасад и сохранение в файл
    public static void main(String[] args) {
        String NEW_LINE = System.lineSeparator();
        String filename = "/home/alexei/Документы/file.txt";
        Page site = new HTMLPage("https://skillbox.ru/", 0);
        SiteMapBuildingTask parser = new SiteMapBuildingTask(site);
        List<String> urls = new ForkJoinPool().invoke(parser);
        System.out.println("Карта сайта");
        StringBuilder siteMapText = new StringBuilder();
        for (String url : urls){
            siteMapText.append(url.concat(NEW_LINE));
            System.out.println(url);
        }
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(siteMapText.toString());
            writer.flush();
            writer.close();
            System.out.println("Файл успешно записан");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        System.out.println("Содержимое статической переменной");
//        for (String page : SiteMapBuildingTask.verifiedPages){
//            System.out.println(page);
//        }
    }
}
