package br.edu.ifto.aula02.model.dao;

import br.edu.ifto.aula02.model.entity.Produto;
import br.edu.ifto.aula02.model.jdbc.MinhaConexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoDAO {

    // Criando um objeto Connection para receber a conexão.
    Connection con;

    public ProdutoDAO(){
        con = MinhaConexao.conexao();
    }

    // Buscando produto pelo id.
    public Produto buscarProduto(int id) {
        try {
            // Comando sql para buscar o produto com o id informado.
            String sql = "select * from produto where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            // Referenciando o parâmetro do método para a "?".
            ps.setInt(1, id);
            // ResultSet, representa o resultado do comando SQL
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setDescricao(rs.getString("descricao"));
                p.setValor(rs.getDouble("valor"));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Buscar todos os produtos cadastrados na tabela Produto.
    public List<Produto> buscarProdutos() {
        try {
            // Comando sql para buscar todos os produtos da tabela.
            String sql = "select * from produto";
            PreparedStatement ps = con.prepareStatement(sql);
            // ResultSet, representa o resultado do comando SQL.
            ResultSet rs = ps.executeQuery();
            // Criando uma lista de produtos para retornar.
            List<Produto> produtos = new ArrayList();
            // Laço para buscar todas os produtos do banco.
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setDescricao(rs.getString("descricao"));
                p.setValor(rs.getDouble("valor"));
                // Sdicionando produto "p" na lista de produtos.
                produtos.add(p);
            }
            // Retornando a lista de produtos.
            return produtos;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Remover um produto da tabela Produto pelo id.
    public boolean remove(int id) {
        try {
            // Comando sql para deletar um produto pelo id.
            String sql = "delete from produto where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            // Referenciando o parâmetro do método para a "?".
            ps.setInt(1, id);
            if(ps.executeUpdate() == 1)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Salvar um produto na tabela Produto.
    public boolean save(Produto produto) {
        try {
            // Comando sql para salvar um produto.
            String sql = "insert into produto (descricao, valor) values (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            // Referenciando os parâmetros do método para "?".
            ps.setString(1, produto.getDescricao());
            ps.setDouble(2, produto.getValor());

            if(ps.executeUpdate() == 1)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Update um produto na tabela Produto.
    public boolean update(Produto produto) {
        try {
            // Comando sql para atualizar um produto.
            String sql = "update produto set descricao=?, valor=? where id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            // Referenciando os parâmetros do método para as "?".
            ps.setString(1, produto.getDescricao());
            ps.setDouble(2, produto.getValor());
            ps.setInt(3, produto.getId());

            if(ps.executeUpdate() == 1)
                return true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
