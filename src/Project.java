import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Project {

    public static void main(String[] args) {
        Project html = new Project();
    }

    public Project() {

        try {
            URL url = new URL("https://en.wikipedia.org/wiki/Inception");

            URLConnection urlc = url.openConnection();
            urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlc.getInputStream())
            );
            String line;
            while ( (line = reader.readLine()) != null ) {

                if(line.contains("href=\"")) {
                    System.out.println(line);

                    int index = line.indexOf("href=\"");
                    String newSubstring = line.substring(index+6);
                    int tracker = 0;
                    for(int i=0;i<newSubstring.length();i++){
                        //
                        if(newSubstring.charAt(i) == '"' || newSubstring.substring(i,i).equals("'")){
                            tracker = i;
                            break;
                        }
                    }
                    System.out.println(newSubstring.substring(0,tracker));
                }
            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }

    }

}
