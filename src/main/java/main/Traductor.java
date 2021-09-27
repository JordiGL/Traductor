package main;

import java.io.*;
import com.google.gson.*;
import com.squareup.okhttp.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class Traductor {

    private static final OkHttpClient CLIENT = new OkHttpClient();
    private String subscriptionKey;
    private String location;
    private HttpUrl url;
    
    public Traductor(String subscriptionKey, String location, String from, String to){
        this.subscriptionKey = subscriptionKey;
        this.location = location;
        this.url = new HttpUrl.Builder()
        .scheme("https")
        .host("api.cognitive.microsofttranslator.com")
        .addPathSegment("/translate")
        .addQueryParameter("api-version", "3.0")
        .addQueryParameter("from", from)
        .addQueryParameter("to", to)
        .build();
    }
    
    public HttpUrl getUrl() {
        return url;
    }

    public void setUrl(HttpUrl url) {
        this.url = url;
    }
    
    public void setSubscriptionKey(String subscriptionKey) {
        this.subscriptionKey = subscriptionKey;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // This function performs a POST request.
    public String Traduir(String text) throws IOException {

        MediaType mediaType = MediaType.parse("application/json");
        
        RequestBody body = RequestBody.create(mediaType,
                "[{\"Text\": \""+ text +"\"}]");
        
        Request request = new Request.Builder().url(url).post(body)
                .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
                .addHeader("Ocp-Apim-Subscription-Region", location)
                .addHeader("Content-type", "application/json")
                .build();
        Response response = CLIENT.newCall(request).execute();
        return  ObtenirParaulaTraduida(response.body().string());
    }

    // This function prettifies the json response.
    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(json_text);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }
    
    public String ObtenirParaulaTraduida(String json){
        JSONArray jsonArray = new JSONArray(json);
        JSONArray jsonArray2 = (JSONArray)jsonArray.getJSONObject(0).get("translations");
        JSONObject jsonArray3 = jsonArray2.getJSONObject(0);
        String text = jsonArray3.get("text").toString();
        return text;
    }
}