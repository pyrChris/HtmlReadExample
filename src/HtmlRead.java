import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HtmlRead {

    public static void main(String[] args) {
        HtmlRead html = new HtmlRead();
    }

    public HtmlRead() {

        try {
            System.out.println();
            System.out.print("hello \n");
            URL url = new URL("https://en.wikipedia.org/wiki/Inception");

            URLConnection urlc = url.openConnection();
            urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlc.getInputStream())
            );
            String line;
            while ( (line = reader.readLine()) != null ) {
                if(line.contains("https:")) {
                    int index = line.indexOf("https:");
                    String newSubstring = line.substring(index);
                    int tracker = 0;
                    for(int i=0;i<newSubstring.length();i++){
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
