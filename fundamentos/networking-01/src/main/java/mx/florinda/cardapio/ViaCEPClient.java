package mx.florinda.cardapio;

import java.net.URL;
import java.util.Scanner;

public class ViaCEPClient {

    private static final String VIACEP_URL = "https://viacep.com.br/ws/$cep/json";

    static void main() throws Exception {
        String cep = "81050160";
        String urlComCEP = VIACEP_URL.replace("$cep", cep);
        URL url = new URL(urlComCEP);
        try (Scanner scanner = new Scanner(url.openStream())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        }
        catch (Exception e) {
            System.out.println("Erro durante a leitura do viaCEP: " + e.getMessage());
        }
    }
}
