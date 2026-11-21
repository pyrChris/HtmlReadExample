import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


public class Project{

    public static void main(String[] args) {
        Project html = new Project();
    }

    public Project() {
        //a while loop is used so that the program keeps processing links until the user stops the program
        while(true){
            processLink();
        }
    }

    public void processLink(){
        //definition of variables used
        String link = "";
        String filterString;
        int index;
        int trackerEnd;
        boolean specialStat = false;

        //new scanner object
        Scanner input = new Scanner(System.in);

        try {
            //prompting the initial user input
            System.out.print("Enter the link: ");
            link = input.nextLine();

            //special cases of user input
            //for each special input, output the corresponding link and indicate the link is special
            switch(link){
                case "Happy Thanks Giving":
                    System.out.println("try this: https://en.wikipedia.org/wiki/Thanksgiving");
                    specialStat = true;
                    break;
                case "Merry Christmas":
                    System.out.println("try this: https://en.wikipedia.org/wiki/Christmas");
                    specialStat = true;
                    break;
                case "Happy New Year":
                    System.out.println("try this: https://en.wikipedia.org/wiki/New_Year");
                    specialStat = true;
                    break;
            }

            //if the link is special, the program ends, else the program runs the rest
            if(!specialStat){
                //cast the link we entered to URL
                URL url = new URL(link);

                //prompting the user to enter the filter
                System.out.print("Enter the filter name: ");
                filterString = input.nextLine();

                URLConnection urlc = url.openConnection();
                urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(urlc.getInputStream())
                );


                String line;
                //each loop in the following while loop is the reading of one line
                while ( (line = reader.readLine()) != null ) {

                    //detects if the line contains elements of a link, and looks for the link only if there is
                    if(line.contains("href=\"") || line.contains("url=")) {

                        //initialize processedString
                        String processedString = line;

                        //the while loop runs only when the processedString still contains a link element, so that the program looks for the multiple links in one line
                        while(processedString.contains("href=")){

                            //set the index variable to the index of the first link element
                            index = processedString.indexOf("href=");
                            //create a substring from after the "href="
                            processedString = processedString.substring(index+6);
                            //the for loop looks for the symbol that marks the end of a link
                            for(int i=0;i<processedString.length();i++){

                                //if the program detects the ending mark, it executes the following
                                if(processedString.charAt(i) == '"' || processedString.charAt(i) == '\'' || processedString.charAt(i) == ' ' || processedString.charAt(i) == '}'){
                                    trackerEnd = i;

                                    //check if the link contains the filter, and output only if the filter is present
                                    if(processedString.substring(0,trackerEnd).contains(filterString)){
                                        System.out.println(processedString.substring(0,trackerEnd));
                                    }

                                    //delete the processed part from processedString
                                    processedString = processedString.substring(trackerEnd);
                                    break;
                                }
                            }
                        }
                    }
                }
                reader.close();
            }
        }
        //exceptions
        catch(Exception ex) {
            //print out error
            System.out.println("Error: "+ex);
            String error = ex.toString();

            //if the link is invalid, suggest a correct link
            if(error.contains("MalformedURLException: no protocol")){
                System.out.println("do you mean: www." + link + ".com");
            }
        }
    }
}
