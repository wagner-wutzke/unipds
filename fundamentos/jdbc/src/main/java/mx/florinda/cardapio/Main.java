package mx.florinda.cardapio;


import mx.florinda.cardapio.jdbc.Database;
import mx.florinda.cardapio.jdbc.MysqlDatabase;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    static void main(String[] args) {
        Database database = new MysqlDatabase("localhost", 3306, "root", "senha123", "cardapio");

        List<ItemCardapio> listaItensCardapio = database.listaItensCardapio();
        listaItensCardapio.forEach(System.out::println);

        int total = database.totalItensCardapio();
        System.out.println(total);

        var novoItemCardapio = new ItemCardapio(10L, "Tacos de Carnitas", "Incríveis tacos recheados com carne tenra", ItemCardapio.CategoriaCardapio.PRATOS_PRINCIPAIS, new BigDecimal("25.9"), null);
        database.adicionaItemCardapio(novoItemCardapio);
    }

}