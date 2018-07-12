import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

/**
 * Created by nikit on 2018/02/15.
 */
public class FactUploader {
    private URL url;
    private JSONParser parser;

    public String getFact() {

        try {
            Document doc = Jsoup.connect("https://randstuff.ru/fact/").get();

            Element div = doc.getElementById("fact").select("td").first();
            return div.text();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }




}
