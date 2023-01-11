import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {
    // todo сделать фасад и сохранение в файл
    public static void main(String[] args) {
        String filename = "/home/alexei/Документы/file.txt";
        SiteMapRunner runner = new SiteMapRunner();
        while (true){
            Scanner scanner = new Scanner(System.in);
            String url = scanner.nextLine();
            if (url.toLowerCase().contains("https://")){
                runner.buildSiteMap(url);
                runner.writeSiteMapToFile(filename);
            }
            if (url.toLowerCase().contains("выход")){
                System.exit(0);
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }




//        System.out.println("Содержимое статической переменной");
//        for (String page : SiteMapBuildingTask.verifiedPages){
//            System.out.println(page);
//        }
    }
}
