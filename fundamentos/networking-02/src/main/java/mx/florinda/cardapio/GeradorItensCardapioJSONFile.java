package mx.florinda.cardapio;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GeradorItensCardapioJSONFile {

    static void main() throws IOException {
        List<ItemCardapio> listaItemsCardapio = new Database().listaItensCardapio();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listaItemsCardapio);

        Path path = Paths.get("itensCardapio.json");
        Files.writeString(path, json);
    }
}
