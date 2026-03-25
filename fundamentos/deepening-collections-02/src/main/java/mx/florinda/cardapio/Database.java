package mx.florinda.cardapio;

import java.math.BigDecimal;
import java.util.*;

import static mx.florinda.cardapio.ItemCardapio.CategoriaCardapio.*;

public class Database {
    private final Map<Long, ItemCardapio> itensPorId = new HashMap<>();
//    private final  Map<ItemCardapio, BigDecimal> auditoriaPreco = new HashMap<>();

    public Database() {
        var refrescoDoChaves = new ItemCardapio(1L, "Refresco do Chaves",
                "Suco de limão que parece de tamarindo e tem gosto de groselha.",
                BEBIDAS, new BigDecimal("2.99"), null);
        itensPorId.put(1L, refrescoDoChaves);

        var sanduicheDoChaves = new ItemCardapio(2L, "Sanduíche de Presunto do Chaves",
                "Sanduíche de presunto simples, mas feito com muito amor.",
                PRATOS_PRINCIPAIS, new BigDecimal("3.50"), new BigDecimal("2.99"));
        itensPorId.put(2L, sanduicheDoChaves);

        var tortaDaDonaFlorinda = new ItemCardapio(5L, "Torta de Frango da Dona Florinda",
                "Torta de frango com recheio cremoso e massa crocante.",
                PRATOS_PRINCIPAIS, new BigDecimal("12.99"), new BigDecimal("10.99"));
        itensPorId.put(5L, tortaDaDonaFlorinda);

        var pipocaDoQuico = new ItemCardapio(6L, "Pipoca do Quico",
                "Balde de pipoca preparado com carinho pelo Quico.",
                PRATOS_PRINCIPAIS, new BigDecimal("4.99"), new BigDecimal("3.99"));
        itensPorId.put(6L, pipocaDoQuico);

        var aguaDeJamaica = new ItemCardapio(7L, "Água de Jamaica",
                "Água aromatizada com hibisco e toque de açúcar.",
                BEBIDAS, new BigDecimal("2.50"), new BigDecimal("2.00"));
        itensPorId.put(7L, aguaDeJamaica);

        var cafeDaDonaFlorinda = new ItemCardapio(8L, "Café da Dona Florinda",
                "Café forte para começar o dia com energia.",
                BEBIDAS, new BigDecimal("1.99"), new BigDecimal("1.50"));
        itensPorId.put(8L, cafeDaDonaFlorinda);

        var churrosDoChaves = new ItemCardapio(9L, "Churros do Chaves",
                "Churros recheados com doce de leite, clássicos e irresistíveis.",
                SOBREMESA, new BigDecimal("4.99"), new BigDecimal("3.99"));
        itensPorId.put(9L, churrosDoChaves);

        var gelatinaDoNhonho = new ItemCardapio(10L, "Gelatina Colorida do Nhonho",
                "Gelatina de várias cores, a sobremesa favorita do Nhonho.",
                SOBREMESA, new BigDecimal("3.50"), new BigDecimal("2.99"));
        itensPorId.put(10L, gelatinaDoNhonho);

        var boloDaDonaClotilde = new ItemCardapio(11L, "Bolo de Chocolate da Dona Clotilde",
                "Bolo de chocolate com cobertura de brigadeiro.",
                SOBREMESA, new BigDecimal("5.99"), new BigDecimal("4.99"));
        itensPorId.put(11L, boloDaDonaClotilde);

//        Map<ItemCardapio.CategoriaCardapio, String> categoriasEmPromocao= new EnumMap(ItemCardapio.class);
//        categoriasEmPromocao.put(SOBREMESA, " O doce perfeito você!");
//        categoriasEmPromocao.put(ENTRADAS, " Comece sua refeicao com toque de sabor!");

    }

    public List<ItemCardapio> listaItensCardapio() {
        return new ArrayList<>(itensPorId.values());
    }

    public Optional<ItemCardapio> buscaItemCardapioPorId(Long id) {
        ItemCardapio itemCardapio = itensPorId.get(id);
        return Optional.ofNullable(itemCardapio);
    }

}