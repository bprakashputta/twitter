package tech.codingclub.helix.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tech.codingclub.helix.global.HttpURLConnectionExample;

import java.io.IOException;

public class WikiDownloader{

    private String keyWord;
    //private String result;

    public WikiDownloader(String keyWord){
        this.keyWord = keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    //get method
    public String getKeyWord() {
        return keyWord;
    }

    public String getWikiURLForQuery(String cleanKeyWord)
    {
        return "https://en.wikipedia.org/wiki/"+cleanKeyWord;
    }

    public WikiResult getResult() {
        //1. Get clean keyWord!
        //2. Get the url for wikipedia!
        //3. Make a get Request to wikipedia!
        //4. Parsing useful results from the query to document using jsoup!
        //5. Showing the results.

        if(getKeyWord()==null|| getKeyWord().toString().length()==0)
        {
            return null;
        }
        //Step 1!!
        String keyWord = getKeyWord().trim().replaceAll("[ ]+","_");
        //Step 2!!
        String wikiURL = getWikiURLForQuery(keyWord);
        String response = "";
        String imageURL = "";
        //Step 3!!
        try {
            //Step 3!!
            //HTML response received from wikipedia through get request.
            String wikiResponseHTML = HttpURLConnectionExample.sendGet(wikiURL);
            //Parse data received from wikipedia into a document using jsoup.
            Document document = Jsoup.parse(wikiResponseHTML,"https://en.wikipedia.org/wiki/");
            //Select the class name for the data we need.
            Elements childElements = document.body().select(".mw-parser-output > *");
            int state = 0;
            //THis is the loop to parse the response received from
            //wikipedia and get the text of the first paragraph and
            // first image.
            for(Element childElement : childElements)
            {
                //state is 0 initially and it is always true.
                if(state == 0){
                    //until the parser reaches to the table tag this won't run
                    if(childElement.tagName().equals("table"))
                    {
                        state=1;// change the value of state to 1 now.
                    }
                }
                else if(state == 1){
                    //print the text in the tag named "p" from the HTML occuring after table.
                    if(childElement.tagName().equals("p")){
                        state = 2;// change state value to 2;
                        response = childElement.text();//store the text of the HTML p-tag in the string.
                        break;//break;
                    }
                }
            }
            //image url for the keyword that we have passed to wikipedia.
            imageURL = document.body().select(".infobox img").get(0).attr("src");

        } catch (IOException e) {
            e.printStackTrace();
        }

//        assert imageURL!=null;
        if(imageURL.startsWith("//")){
            imageURL = "https:"+imageURL;
        }
        System.out.println(imageURL);
        //Declare the wikiresult object to store the information retreived.
        //Normally we store the retreived data in the database.
        //We still haven't done databases, so for now we'll try to store them as JSON objects
        //using the GSON java package to convert object data to JSON.
        WikiResult wikiResult = new WikiResult(keyWord,response,imageURL);
        //create the gson object using the GSON open-source package for java.
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //convert the gson to json.
//        String json = gson.toJson(wikiResult);
//        System.out.println(json);//print the json objects.
        return wikiResult;
    }
}
