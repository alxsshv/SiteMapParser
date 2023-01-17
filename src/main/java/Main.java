import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filename = "/home/alexei/Документы/siteMap.txt";
        SiteMap siteMap = new SiteMap();
        while (true){
            Scanner scanner = new Scanner(System.in);
            String url = scanner.nextLine();
            if (url.toLowerCase().contains("https")){
                siteMap.buildSiteMap(url);
                siteMap.writeSiteMapToFile(filename);
            }
            if (url.toLowerCase().contains("выход")){
                System.exit(0);
            }
            Thread.onSpinWait();
        }
    }
}
