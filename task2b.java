package com.extra;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class task2b {
    List<String> Frontier = new LinkedList<>();    //List for Frontier
    int c = 1;      // count initialized to 1 as seed will be the first url
    int pages =1;   // pages initialized to 1 as seed will be the first url
    int d =1;       // depth

    public static void main(String[] args) throws IOException {

                PrintWriter outer = new PrintWriter("Task 2-B.txt");                        // Create object to write file
                task2b m = new task2b();                                                        // object of Main class
                System.out.println("https://en.wikipedia.org/wiki/Sustainable_energy  1");  // Seed as 1st URL
                outer.write("https://en.wikipedia.org/wiki/Sustainable_energy"+ "\r\n");    // Write seed as the first URL
                m.crawl("https://en.wikipedia.org/wiki/Sustainable_energy", "solar", outer); // Call crawl with arguments
                outer.close();                                                               // Close write object
            }

            public void crawl(String url, String keyword, PrintWriter outer) throws IOException {
                try{
                    Frontier.add(url);                      // add url to Frontier
                    d++;                                    // increment depth
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e)                    // Delay of 1 second
                        {
                    }
                    Document htmlDocument = Jsoup.connect(url).get();                       // Establish connection using Jsoup
                    Element linksOnPage = htmlDocument.getElementById("bodyContent");       // Narrow down by clas
                    Element s = linksOnPage.removeClass("references");                         //remove references
                    Elements links = s.select("a");                         // anchor tag
                    for (Element link : links) {
                        String lhref = link.attr("href");
                        if ((!(lhref.contains(":"))) && (lhref.startsWith("/wiki"))
                                && ((lhref.toLowerCase().contains(keyword))
                                && (!(Frontier.contains("https://en.wikipedia.org" + lhref))))) {
                            String href = "https://en.wikipedia.org" + lhref;
                            c++;                                                    // Increment count
                            Frontier.add(href);                                     // add href to the Frontier
                            System.out.println(href + " " + c);                     // print href with count
                            outer.write(href+" "+ "\r\n");                           // Write the URL to a file
                            pages++;                                                // Increament the pages
                            if(d < 5) {
                                crawl(href, keyword, outer);                          //call crawler again
                            }
                        }
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    e.printStackTrace();}
            }
        }

