package modelos;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ResponseApi {
    private String API_URL = "https://v6.exchangerate-api.com/v6/4eb3f796934acfad6c885e65/latest/";

    //Logica para obtener la conversion de monedas
    public Moneda respuestaApi(String base_code){
        Gson gson = new Gson();
        String url = API_URL + base_code;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(response.body(), Moneda.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
