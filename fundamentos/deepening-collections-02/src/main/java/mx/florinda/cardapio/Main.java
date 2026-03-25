package mx.florinda.cardapio;

import java.util.List;
import java.util.Optional;


public class Main {
    static void main() {
        System.out.println("\n-------------------------");
        System.out.println("CARDAPIO DA DONA FLORINDA");
        System.out.println("-------------------------");

        Database database = new Database();
        List<ItemCardapio> itens = database.listaItensCardapio();

        itens.forEach(System.out::println);

        System.out.println("=========================================");

        Optional<ItemCardapio> optionalItem = database.buscaItemCardapioPorId(1L);
        String mensagem =  optionalItem.map(ItemCardapio::toString).orElse("Item não encontrado");
        if (optionalItem.isPresent()) {
            System.out.println(optionalItem.get());
        } else {
            System.out.println(mensagem);
        }
        System.out.println("=========================================\n");

        HistoricoVisualizacao historico = new HistoricoVisualizacao(database);
        historico.registrarVisualizacao(1L);
        historico.registrarVisualizacao(2L);
        historico.registrarVisualizacao(4L);

        System.out.println("\n=========================================");
        System.out.println("HISTORICO DE VISUALIZACOES");
        System.out.println("=========================================");
        historico.listarVisualizacoes();
    }
}
