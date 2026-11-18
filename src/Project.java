import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Project {

    public static void main(String[] args) {
        Project html = new Project();
    }

    public Project() {

        String filterString;
        int index;
        int trackerEnd;

        Scanner input = new Scanner(System.in);



        try {
            System.out.print("Enter the link: ");
            URL url = new URL(input.nextLine());

            System.out.print("Enter the filter name: ");
            filterString = input.nextLine();

            URLConnection urlc = url.openConnection();
            urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlc.getInputStream())
            );
            String line;
            while ( (line = reader.readLine()) != null ) {



                if(line.contains("href=\"") || line.contains("url=")) {
                    //System.out.println(line);
                    String processedString = line;
                    while(processedString.contains("href=")){
                        index = processedString.indexOf("href=");
                        processedString = processedString.substring(index+6);
                        for(int i=0;i<processedString.length();i++){
                            if(processedString.charAt(i) == '"' || processedString.charAt(i) == '\'' || processedString.charAt(i) == ' ' || processedString.charAt(i) == '}'){
                                trackerEnd = i;
                                if(processedString.substring(0,trackerEnd).contains(filterString)){
                                    System.out.println(processedString.substring(0,trackerEnd));
                                }
                                processedString = processedString.substring(trackerEnd);
                                break;
                            }
                        }
                    }
                }
            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }

    }

}
