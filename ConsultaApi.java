import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaApi {

    private static final String API_KEY = "06ee5981ba2341e5f8dc342b"; // clave de API
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public Monedas buscarMoneda(String moneda, String convertir, double cantidad) {
        String url = BASE_URL + API_KEY + "/pair/" + moneda + "/" + convertir;

        URI direccion = URI.create(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonResponse = new Gson().fromJson(response.body(), JsonObject.class);
            if (jsonResponse.get("result").getAsString().equals("success")) {
                double tasaDeCambio = jsonResponse.get("conversion_rate").getAsDouble();
                double resultado = cantidad * tasaDeCambio;
                return new Monedas(moneda, convertir, resultado);
            } else {
                throw new RuntimeException("Error en la respuesta de la API: " + jsonResponse.get("error-type").getAsString());
            }
        } catch (Exception e) {
            throw new RuntimeException("No se pudo completar la solicitud a la API: " + e.getMessage());
        }
    }
}



