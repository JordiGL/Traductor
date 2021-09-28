package model;

import java.io.*;
import com.squareup.okhttp.*;
import gestor.GestorException;
import org.json.JSONArray;
import org.json.JSONObject;
//import com.google.gson.*;

public class Traductor {

    private static final OkHttpClient CLIENT = new OkHttpClient();
    private String subscriptionKey;
    private String location;
    private HttpUrl url;
    
    public Traductor(String subscriptionKey, String location, String from, String to) throws GestorException{
        this.subscriptionKey = subscriptionKey;
        this.location = location;
        setUrl(from, to);
    }
    
    public HttpUrl getUrl() {
        return url;
    }
    
    /**
     * Mètode SET de la URL. 
     * Aquest crea la URL que s'utilitzarà en el POST per a connectar-se amb el
     * servei traductor d'Azure Microsoft Cognitive Services.
     * @param from, idioma del text a traduir.
     * @param to, idioma al qual volem traduir.
     * @throws GestorException 
     */
    private void setUrl(String from, String to) throws GestorException {
        
        try{
            
            if(from.equalsIgnoreCase("es") || from.equalsIgnoreCase("ca") || 
                    from.equalsIgnoreCase("en")){
                
                this.url = new HttpUrl.Builder()
                    .scheme("https")
                    .host("api.cognitive.microsofttranslator.com")
                    .addPathSegment("/translate")
                    .addQueryParameter("api-version", "3.0")
                    .addQueryParameter("from", from)
                    .addQueryParameter("to", to)
                    .build();
            }else{
                throw new GestorException("L'Idioma de la frase a traduir no està disponible. "
                        + "Idiomes disponibles: català (ca), castellà (es), anglès (en).");
            }  
            
        }catch(Exception e){
            throw new GestorException("Error en la construcció de la url "
                    + "per a efectuar la comunicació amb el servei traductor. "
                    + e);
        }

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

    /**
     * Mètode públic que realitza la connexió amb el servei traductor, i n'obté
     * el text traduït de la resposta.
     * @param text, text a traduir.
     * @return text traduït.
     * @throws IOException
     * @throws GestorException 
     */
    public String Traduir(String text) throws IOException, GestorException{
        String resposta = Post(text);
        String textTraduit = ObtenirParaulaTraduida(resposta);
        return textTraduit;
    }
    
    /**
     * Mètode que realitza la sol·licitud Post al servei Traductor 
     * d'Azure Microsoft Cognitive Services.
     * @param text, text a traduir
     * @return La resposta del POST efectuat.
     * @throws IOException
     * @throws GestorException 
     */
    private String Post(String text) throws IOException, GestorException {
        
        try{
            
            //Creem la sol·licitud.
            MediaType mediaType = MediaType.parse("application/json");

            RequestBody body = RequestBody.create(mediaType,
                    "[{\"Text\": \""+ text +"\"}]");

            Request request = new Request.Builder().url(url).post(body)
                    .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
                    .addHeader("Ocp-Apim-Subscription-Region", location)
                    .addHeader("Content-type", "application/json")
                    .build();
            
            //Enviem la sol·licitud i n'obtenim la resposta.
            Response response = CLIENT.newCall(request).execute();

            return  response.body().string();
        
        }catch(Exception e){
            throw new GestorException("Error a l'hora d'efectuar la connexió "
                    + "amb el servei traductor. " + e);
        }
    }
    
    /**
     * Mètode que extreu el text traduït (que és la part que ens interessa) 
     * de la resposta del POST.
     * @param json String en format JSON que es rep del servei traductor, un cop efectuat el Post.
     * @return text traduït.
     * @throws GestorException 
     */
    private String ObtenirParaulaTraduida(String json) throws GestorException{
        
        try{
            
            JSONArray jsonArray = new JSONArray(json);
            JSONArray jsonArray2 = (JSONArray)jsonArray.getJSONObject(0).get("translations");
            JSONObject jsonArray3 = jsonArray2.getJSONObject(0);
            String text = jsonArray3.get("text").toString();

            return text;
        
        }catch(Exception e){
            throw new GestorException("Error a l'hora d'extreure la paraula "
                    + "traduida de l'arxiu JSON rebut. " + e);
        }
    }
}