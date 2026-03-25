package mx.florinda.cardapio;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorItensCardapioComSocket {

    static void main(String[] args) throws Exception {

        try(ExecutorService executorService = Executors.newFixedThreadPool(50)) {
            try(ServerSocket serverSocket = new ServerSocket(8000)) {
                System.out.println("\n===================================");
                System.out.println("O servidor esta operando.");
                System.out.println("===================================\n");

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    executorService.execute(() -> trataRequisicao(clientSocket));
                }
            }
        }
    }

    private static void trataRequisicao(Socket clientSocket) {
        try(clientSocket) {
            InputStream inputStream = clientSocket.getInputStream();
            StringBuilder requestBuilder = new StringBuilder();
            int data;
            do {
                data = inputStream.read();
                requestBuilder.append((char) data);
            } while (inputStream.available() > 0);

            String request = requestBuilder.toString();
            System.out.println(request);

            Path path = Path.of("itensCardapio.json");
            String json = Files.readString(path);
            Thread.sleep(250);
            OutputStream clientOS = clientSocket.getOutputStream();
            PrintStream clientOut = new PrintStream(clientOS);

            clientOut.println("HTTP/1.1 200 OK");
            clientOut.println("Content-type: application/json; charset=UTF-8");
            clientOut.println();
            clientOut.println(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}