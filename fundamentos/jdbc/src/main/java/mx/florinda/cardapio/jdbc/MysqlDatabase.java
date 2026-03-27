package mx.florinda.cardapio.jdbc;

import mx.florinda.cardapio.ItemCardapio;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MysqlDatabase implements Database {

    private final String host;
    private final int port;
    private final String user;
    private final String password;
    private final String database;

    public MysqlDatabase(String host, int port, String user, String password, String database) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    private DatabaseCloseableResources executeQuery(String query) throws SQLException {
        DatabaseCloseableResources resources = createPreparedStatement(query);
        resources.resultSet = resources.preparedStatement.executeQuery();
        return resources;
    }

    private DatabaseCloseableResources createPreparedStatement(String query) throws SQLException {
        DatabaseCloseableResources resources = new DatabaseCloseableResources();
        resources.connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
        resources.preparedStatement = resources.connection.prepareStatement(query);
        return resources;
    }

    @Override
    public List<ItemCardapio> listaItensCardapio() {
        List<ItemCardapio> itensCardapio = new ArrayList<>();
        String sql = "SELECT id, nome, descricao, categoria, preco, preco_promocional FROM item_cardapio";

        try (DatabaseCloseableResources closeableResources = executeQuery(sql)) {
            ResultSet rs = closeableResources.resultSet;

            while (rs.next()) {
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                String categoriaStr = rs.getString("categoria");
                BigDecimal preco = rs.getBigDecimal("preco");
                BigDecimal precoPromocional = rs.getBigDecimal("preco_promocional");

                ItemCardapio.CategoriaCardapio categoria = ItemCardapio.CategoriaCardapio.valueOf(categoriaStr);
                ItemCardapio itemCardapio = new ItemCardapio(id, nome, descricao, categoria, preco, precoPromocional);
                itensCardapio.add(itemCardapio);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return itensCardapio;
    }

    @Override
    public int totalItensCardapio() {
        String sql = "SELECT count(*) FROM item_cardapio";
        try (DatabaseCloseableResources closeableResources = executeQuery(sql)) {
            ResultSet rs = closeableResources.resultSet;
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void adicionaItemCardapio(ItemCardapio item) {
        String sql = "INSERT INTO item_cardapio (id, nome, descricao, categoria, preco, preco_promocional) VALUES (?, ?, ?, ?, ?, ?)";
        try (DatabaseCloseableResources closeableResources = createPreparedStatement(sql)) {
            PreparedStatement ps = closeableResources.preparedStatement;
            ps.setLong(1, item.id());
            ps.setString(2, item.nome());
            ps.setString(3, item.descricao());
            ps.setString(4, item.categoria().name());
            ps.setBigDecimal(5, item.preco());
            ps.setBigDecimal(6, item.precoPromocional());
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ItemCardapio> itemCardapioPorId(Long id) {
        String sql = "SELECT id, nome, descricao, categoria, preco, preco_promocional FROM item_cardapio " +
                "WHERE id = ? ";
        try (DatabaseCloseableResources closeableResources = createPreparedStatement(sql)) {
            PreparedStatement ps = closeableResources.preparedStatement;
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new ItemCardapio(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        ItemCardapio.CategoriaCardapio.valueOf(rs.getString("categoria")),
                        rs.getBigDecimal("preco"),
                        rs.getBigDecimal("preco_promocional")
                ));
            }
            else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeItemCardapio(Long id) {
        String sql = "DELETE FROM item_cardapio WHERE id = ?";
        try (DatabaseCloseableResources closeableResources = createPreparedStatement(sql)) {
            PreparedStatement ps = closeableResources.preparedStatement;
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean alteraPrecoItemCardapio(Long id, BigDecimal novoPreco) {
        throw new UnsupportedOperationException("TODO");
    }

    static class DatabaseCloseableResources implements AutoCloseable {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        @Override
        public void close() throws Exception {
            if (connection != null && !connection.isClosed()) connection.close();
            if (preparedStatement != null && !preparedStatement.isClosed()) preparedStatement.close();
            if (resultSet != null && !resultSet.isClosed()) resultSet.close();
        }
    }
}