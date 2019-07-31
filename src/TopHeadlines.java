import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class Defaults {

    private final static String endpoint = "https://newsapi.org/v2/top-headlines?";

    /*
    country options to choose from:-
    ae ar at au be bg br ca ch cn co cu cz de eg
    fr gb gr hk hu id ie il in it jp kr lt lv ma
    mx my ng nl no nz ph pl pt ro rs ru sa se sg
    si sk th tr tw ua us ve za
     */
    private final static String srcCountry = "fr";



    private final static String apiKey = "8b17debeb9694c3c8bbb2044c17a1231";

    public final static String srcURL = endpoint+"country="+srcCountry+"&apiKey="+apiKey;
    public final static List<Headline> headlineList = new ArrayList<>();
}

class Utils {
    public static void clrscr(){
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
}

class Headline {

    private String source;
    private String author;
    private String title;
    private String desc;
    private String date;

    public Headline(String source, String author, String title, String desc, String date) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.desc = desc;
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }
/*
    @Override
    public String toString() {
        return "\t"+title.toUpperCase() +"\n\t\t~ "+author+" @ "+source+"\n\t\t~ "+date
                + "\n\t" + getTabbedDesc(desc);
    }

    private String getTabbedDesc(String desc) {
        String res="";

        desc
    }*/
}

interface OnDoneLoadingDataListener { void onDataLoaded(String jsonStr); }

class ParseData implements OnDoneLoadingDataListener {

    @Override
    public void onDataLoaded(String jsonStr) {

        System.out.println(jsonStr); // for  check !

        /*
        if (jsonStr == null || jsonStr.isEmpty()) return;

        parseJSON(jsonStr);

        // parse loaded data JSON here ! //
        // also display it on console //
        // at end ask for refresh (r|R) or quit (any other key) ! //


         */
    }
/*
    private void parseJSON(String jsonStr) {

        JSONParser parser = new JSONParser();

        try {
            JSONObject obj = (JSONObject)parser.parse(jsonStr);

            String status =  obj.get("status").toString();
            int totalRes = Integer.parseInt(obj.get("totalResults").toString());

            if(status.equals("ok") && totalRes>0)
            {
                JSONArray articles = (JSONArray) obj.get("articles");

                for(Object o : articles)
                {
                    JSONObject element = (JSONObject)o;
                    Defaults.headlineList.add(new Headline(
                            ((JSONObject)element.get("source")).get("name"),

                    ))
                }
            }







        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    */

}

class LoadData {

    private OnDoneLoadingDataListener mListener;

    public void registerDataLoadingListener(OnDoneLoadingDataListener mListener) {
        this.mListener = mListener;
    }


    // AsyncTask for Loading data //
    public void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                String jsonStr = fetchDataFromSite();

                // at end when done ... go to listener
                // .. to parse data
                if (mListener != null) {   // check if listener is registered
                    Utils.clrscr();
                    mListener.onDataLoaded(jsonStr);
                }
            }
        }).start();
    }

    private String fetchDataFromSite() {


        String jsonStringElement = "";
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL(Defaults.srcURL).openConnection();

            BufferedReader buf = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = buf.readLine()) != null) {
                jsonStringElement += line;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return jsonStringElement;
    }

}

public class TopHeadlines {

    public static void main(String[] args) {

        LoadData loadDataObj = new LoadData();
        OnDoneLoadingDataListener mListener = new ParseData();
        loadDataObj.registerDataLoadingListener(mListener);
        loadDataObj.loadData();
        System.out.println("Loading Headlines ...");

    }
}
