package mx.florinda.cardapio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public class Main {

    public static final String ITEM_NÃO_ENCONTRADO = "Item não encontrado";

    static void main() {
        System.out.println("\n-------------------------");
        System.out.println("CARDAPIO DA DONA FLORINDA");
        System.out.println("-------------------------");

        Database database = new Database();
        List<ItemCardapio> itens = database.listaItensCardapio();
        itens.forEach(System.out::println);

        System.out.println("\n=========================================");
        System.out.println("BUSCA ITEM POR ID");
        System.out.println("=========================================");
        Optional<ItemCardapio> optionalItem = database.buscaItemCardapioPorId(1L);
        String mensagem =  optionalItem.map(ItemCardapio::toString).orElse(ITEM_NÃO_ENCONTRADO);
        System.out.println(mensagem);

        optionalItem = database.buscaItemCardapioPorId(4L);
        mensagem =  optionalItem.map(ItemCardapio::toString).orElse(ITEM_NÃO_ENCONTRADO);
        System.out.println(mensagem);


        System.out.println("\n=========================================");
        System.out.println("REGISTRAR VISUALIZACOES");
        System.out.println("=========================================");

        HistoricoVisualizacao historico = new HistoricoVisualizacao(database);
        historico.registrarVisualizacao(1L);
        historico.registrarVisualizacao(2L);
        historico.registrarVisualizacao(4L);

        System.out.println("\n=========================================");
        System.out.println("HISTORICO DE VISUALIZACOES");
        System.out.println("=========================================");
        historico.listarVisualizacoes();

        System.out.println("\n=========================================");
        System.out.println("REMOVER ITEM DO CARDAPIO");
        System.out.println("=========================================");
        System.out.println("Remover item 1: " +  database.removerItemCardapio(1L));
        System.out.println("Remover item 4: " +  database.removerItemCardapio(4L));

        System.out.println("\n=========================================");
        System.out.println("ALTERAR PRECO DE ITEM DO CARDAPIO");
        System.out.println("=========================================");

        System.out.println("Alterar preco do item 2: " + database.alterarPreco(2L, new BigDecimal("3.99")));
        System.out.println("Alterar preco do item 2: " + database.alterarPreco(2L, new BigDecimal("2.99")));
        System.out.println("Alterar preco do item 2: " + database.alterarPreco(2L, new BigDecimal("3.59")));
        System.out.println("Alterar preco do item 3: " + database.alterarPreco(5L, new BigDecimal("13.99")));
        System.out.println();

        itens = database.listaItensCardapio();
        itens.forEach(System.out::println);

        database.rastroAuditoriaPrecos();
    }
}