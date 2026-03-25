package mx.florinda.cardapio;

import java.math.BigDecimal;
import java.util.Objects;

public record ItemCardapio(Long id, String nome, String descricao, CategoriaCardapio categoria, BigDecimal preco,
                           BigDecimal precoComDesconto) {

    public ItemCardapio alterarPreco(BigDecimal novoPreco) {
        return new ItemCardapio(id, nome, descricao, categoria, novoPreco, precoComDesconto);
    }

    public enum CategoriaCardapio {
        ENTRADAS, PRATOS_PRINCIPAIS, BEBIDAS, SOBREMESA
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItemCardapio that = (ItemCardapio) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(descricao, that.descricao) && Objects.equals(preco, that.preco) && categoria == that.categoria && Objects.equals(precoComDesconto, that.precoComDesconto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, categoria, preco, precoComDesconto);
    }
}