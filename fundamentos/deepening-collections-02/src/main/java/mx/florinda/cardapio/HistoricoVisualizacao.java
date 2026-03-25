package mx.florinda.cardapio;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HistoricoVisualizacao {

    private final Database database;
    private final Map<ItemCardapio, LocalDateTime> visualizacoes = new HashMap<>();

    public HistoricoVisualizacao (Database database) {
        this.database = database;
    }

    public void registrarVisualizacao(Long itemId) {
        Optional<ItemCardapio> optionalItem = database.buscaItemCardapioPorId(itemId);
        if (optionalItem.isEmpty()) {
            System.out.println("Item nao encontrado: " + itemId);
        }
        else {
            ItemCardapio itemCardapio = optionalItem.get();
            visualizacoes.put(itemCardapio, LocalDateTime.now());
        }
    }

    public void listarVisualizacoes() {
        if (visualizacoes.isEmpty()) {
            System.out.println("Nenhum item foi visualizado ainda.");
        }
        else {
            visualizacoes.forEach((itemCardapio, localDateTime) -> {
                System.out.printf("- %s em %s\n", itemCardapio, localDateTime);
            });
        }
    }
}
