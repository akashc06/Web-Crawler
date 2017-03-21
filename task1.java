import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.MalformedURLException;
import java.util.*;
import java.util.LinkedList;

public class task1 {

    static List<String> pagesVisited = new LinkedList<>();    // List to store all the visited pages
    static Queue<String> pagesToVisit = new LinkedList<String>();  // Queue to store all the pages to visit
    static int c = 0; //Initialize counter to 0
    static int d = 1; //Initialize depth to 1

    public static void main(String[] args) throws InterruptedException {
        // Create a PrintWriter object and initialize it to null
        PrintWriter write = null;
        try {
            write = new PrintWriter("Task 1-E.txt");  // Creates a text file called "task3"
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        task1 mn = new task1();                       // Create object of the Main class
        // Call to the crawl function with seed Url and the PrintWriter object as arguments
        mn.crawl("https://en.wikipedia.org/wiki/Sustainable_energy", write);
        // Writes the contents of the pagesToVisit to pagesVisited
        while (!pagesToVisit.isEmpty()) {
            String url = pagesToVisit.remove();
            if(!url.equals("#"))
            {
                pagesVisited.add(url);
            }
        }

        for(int i=0; i<1000 ;i++)
        {
            c += 1;
            Thread.sleep(500);
            System.out.println(pagesVisited.get(i)+ " " + c);
            write.write(pagesVisited.get(i));
        }
        System.out.println("Done");
        write.close();                                  // Close the PrintWriter object
    }

    // Function to crawl
    // Input : URL to crawl and the PrintWriter object
    // Uses Breadth First Search Algorithm
    public static boolean crawl(String url, PrintWriter write) throws InterruptedException {
        // crawls if the conditions mentioned are satisfied
        while ((pagesToVisit.size() + pagesVisited.size() < 1000)) {
            String currentUrl;
            if (pagesToVisit.isEmpty()) {
                currentUrl = url;
                pagesVisited.add(currentUrl+ "\r\n");           // add current url to the visited list
                // add a special character after adding the seed .
                // This step is for calculating depth
                pagesToVisit.add("#");
            } else {
                do {
                    // Remove the first element from the queue and pass it to crawl
                    currentUrl = pagesToVisit.remove();
                } while (pagesVisited.contains(currentUrl) || currentUrl.equals("#"));
                pagesVisited.add(currentUrl);
            }
            // Performs actual crawling
            try {
                String lhref = null;
                try{ Thread.sleep(3000);}    // Politeness Policy
                catch(Exception e) {}  // Delay of 1 second
                Document htmlDocument = Jsoup.connect(currentUrl).get();    // Establish Connection to the URL
                String docs = htmlDocument.body().text();            // fetech the text from the html document
                if(d<5) {                                            // condition of depth
                    Element linksOnPage = htmlDocument.getElementById("bodyContent"); //Narrowing the search by ID
                    Element s =linksOnPage.removeClass("references");
                    Elements links = linksOnPage.select("a");
                    // Select the anchor tag
                    for (Element link : links) {
                        lhref = link.attr("href");                       // get the HyperLink reference
                        if (!(lhref.contains(":")) && (lhref.startsWith("/wiki")) && (!(pagesToVisit.contains("https://en.wikipedia.org" + lhref + "\n")))) {
                            // adds lhref to the queue
                            String href = "https://en.wikipedia.org" + lhref + "\r\n";
                            pagesToVisit.add(href);
                        }
                    }
                }

                // Calculate Depth
                // Whenever the special character is encountered, depth (d) is incremented
                // and the special character is added to the queue again.
                // This helps us to keep a track of the depth in Breadth First Search Algorithm
                if (pagesToVisit.peek().equals("#")) {
                    d++;
                    pagesToVisit.add("#");
                }
            }catch (MalformedURLException ep) {
                return true;}
            catch (IOException ioe) {
                return false;}

        } return true;
    }
}


