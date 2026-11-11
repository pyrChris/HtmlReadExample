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

                if(line.contains("href=\"") || line.contains("url=")) {
                    System.out.println(line);

//                    for(int i=0;i<line.length();i++){
//
//                    }
//                    int temp1 = line.indexOf("href=\"");
//                    int temp2 = line.indexOf("url=");
//                    int index;
//                    String processedString;
//                    if(temp1<temp2){
//                        index = temp1;
//                        processedString = line.substring(index+6);
//                    }
//                    else{
//                        index = temp2;
//                        processedString = line.substring(index+4);
//                    }
                    int index = line.indexOf("href=\"");
                    String processedString = line.substring(index+6);
                    int tracker;
                    while(processedString.contains("href=\"")||processedString.contains("url=")){
                        for(int i=0;i<processedString.length();i++){
                            if(processedString.charAt(i) == '"' || processedString.charAt(i) == '\'' || processedString.charAt(i) == ' ' || processedString.charAt(i) == '}'){
                                tracker = i;
                                System.out.println(processedString.substring(0,tracker));
                                processedString = processedString.substring(tracker);
                                break;
                            }
                        }
                    }
//                    int index = line.indexOf("href=\"");
//                    String newSubstring = line.substring(index+6);
//                    int tracker = 0;
//                    for(int i=0;i<newSubstring.length();i++){
//                        //
//                        if(newSubstring.charAt(i) == '"' || newSubstring.substring(i,i).equals("'")){
//                            tracker = i;
//                            break;
//                        }
//                    }
//                    System.out.println(newSubstring.substring(0,tracker));
                }
            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }

    }

}
