package mx.florinda.cardapio;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCEPHttpClient {

    private static final String VIACEP_URL = "https://viacep.com.br/ws/$cep/json";

    static void main() throws Exception {
        String cep = "81050160";
        String urlComCEP = VIACEP_URL.replace("$cep", cep);
        URI uri = URI.create(urlComCEP);

        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder(uri).build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("\nHTTP Response: \n- status: " + response.statusCode() + "\n- body: " + response.body());
        }
    }
}
