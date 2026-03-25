package mx.florinda.cardapio;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;

public class ServidorItensCardapio {

    static void main() throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);

        httpServer.createContext("/cardapio", exchange -> {
            Path path = Path.of("itensCardapio.json");
            String json = Files.readString(path);
            byte[] bytes = json.getBytes();

            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
            exchange.sendResponseHeaders(200, bytes.length);
            exchange.getResponseBody().write(bytes);
        });

        System.out.println("\n==========================================");
        System.out.println("Servidor de cardapio iniciado");
        System.out.println("==========================================");
        httpServer.start();
    }
}
