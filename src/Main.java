import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main
        {
            public static void main(String[] args) {
                String startingURL = "https://wikipedia.org";
                crawl(startingURL);
            }

            public static void crawl(String startingURL)
            {
                ArrayList<String> pendingURLs = new ArrayList<>();  //to visit
                ArrayList<String> traversedURLs = new ArrayList<>(); //visited
                pendingURLs.add(startingURL);

                while(!pendingURLs.isEmpty() && traversedURLs.size()<50)
                {
                    String URLString = pendingURLs.remove(0);
                    traversedURLs.add(URLString);
                    System.out.println(URLString);
                    ArrayList<String> subURLs = getSubURLs(URLString);
                    for(String sub:subURLs)
                    {
                        if((!pendingURLs.contains(sub))&&(!traversedURLs.contains(sub)))
                        {
                            pendingURLs.add(sub);
                        }
                    }

                }
            }
            public static ArrayList<String> getSubURLs(String URLString)
            {
                ArrayList<String> list = new ArrayList<>();
                String regex = "(http|https)://(\\w+\\.)+(com|edu|org|gov)(\\.tr)?";
                Pattern pattern =Pattern.compile(regex);
                Matcher matcher;
                try {
                    URL url = new URL(URLString);
                    Scanner scan = new Scanner(url.openStream());
                    while(scan.hasNext())
                    {
                        String line = scan.nextLine();
                        matcher = pattern.matcher(line);
                        while(matcher.find())
                        {
                            list.add(matcher.group());
                        }

                    }
                    scan.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return list;


            }
        }

