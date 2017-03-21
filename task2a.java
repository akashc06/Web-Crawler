import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.MalformedURLException;
import java.util.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class task2a {

    static List<String> Frontier = new LinkedList<>();    // List to store all the visited pages
    static Queue<String> Wishlist = new LinkedList<String>();  // Queue to store all the pages to visit
    static List<String> pages = new LinkedList<>();
    static int c = 0; //Initialize counter to 0
    static int d = 1; //Initialize depth to 1

    public static void main(String[] args) throws InterruptedException {
        // Create a PrintWriter object and initialize it to null
        PrintWriter write = null;
        try {
            write = new PrintWriter("Task 2-A.txt");  // Creates a text file called "Task 2-A"
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        task2a mn = new task2a();                       // Create object of the Main class
        // Call to the crawl function with seed Url and the PrintWriter object as arguments
        mn.crawl("https://en.wikipedia.org/wiki/Sustainable_energy", "solar", write);
        // Writes the contents of the pagesToVisit to pagesVisited
        while (!Wishlist.isEmpty()) {
            String url = Wishlist.remove();
            if(!url.equals("#"))
            {
                Frontier.add(url);
            }
        }
        for(int i=0; i<1000; i++)
        {
            c += 1;
            Thread.sleep(500);
            System.out.println(Frontier.get(i)+ " " +c);
            write.write(Frontier.get(i) + "\r\n");
        }
        System.out.println("Done");
        System.out.println("Pages Crawled =" + " " +pages.size());
        write.close();                                  // Close the PrintWriter object
    }

    // Function to crawl
    // Input : URL to crawl and the PrintWriter object
    // Uses Breadth First Search Algorithm
    public static boolean crawl(String url, String keyword, PrintWriter write) throws InterruptedException {
        // crawls if the conditions mentioned are satisfied
        while ((Wishlist.size() + Frontier.size() < 1000)) {
            String currentUrl;
            if (Wishlist.isEmpty()) {
                currentUrl = url;
                Frontier.add(currentUrl);           // add current url to the visited list
                pages.add(url);
                // add a special character after adding the seed .
                // This step is for calculating depth
                Wishlist.add("#");
            } else {
                do {
                    // Remove the first element from the queue and pass it to crawl
                    currentUrl = Wishlist.remove();
                } while (Frontier.contains(currentUrl) || currentUrl.equals("#"));
                Frontier.add(currentUrl);
                pages.add(url);
            }
            // Performs actual crawling

            try {
                String lhref = null;

                // Politeness Policy
                // Delay of 1 second
                Thread.sleep(1000);
                Document htmlDocument = Jsoup.connect(currentUrl).get();    // Establish Connection to the URL
                String docs = htmlDocument.body().text();            // fetech the text from the html document
                if(d<5) {                                            // condition of depth
                    Element linksOnPage = htmlDocument.getElementById("bodyContent"); //Narrowing the search by ID
                    Element s =linksOnPage.removeClass("references");
                    Elements links = linksOnPage.select("a");
                    // Select the anchor tag
                    for (Element link : links) {
                        lhref = link.attr("href");
                        //String ltext = link.text();// get the HyperLink reference
                        if (!(lhref.contains(":")) && (lhref.toLowerCase().contains(keyword))
                                && (lhref.startsWith("/wiki")) && (!(Wishlist.contains(lhref)))) {
                            String href = "https://en.wikipedia.org" + lhref;
                            Wishlist.add(href);   // add href ot wishlist
                        }
                    }
                }

                // Calculate Depth
                // Whenever the special character is encountered, depth (d) is incremented
                // and the special character is added to the queue again.
                // This helps us to keep a track of the depth in Breadth First Search Algorithm
                if (Wishlist.peek().equals("#")) {
                    d++;
                    Wishlist.add("#");
                }
            }catch (MalformedURLException ep) {
                return true;}
            catch (IOException ioe) {
                return false;}

        } return true;
    }
}

