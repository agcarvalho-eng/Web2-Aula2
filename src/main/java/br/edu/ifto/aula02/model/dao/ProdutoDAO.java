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

    //criar um objeto Connection para receber a conexão
    Connection con;

    public ProdutoDAO(){
        con = MinhaConexao.conexao();
    }

    // Buscar produto pelo id.
    public Produto buscarProduto(int id) {
        try {
            //comando sql
            String sql = "select * from produto where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setInt(1, id);
            //ResultSet, representa o resultado do comando SQL
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

    // Buscar todos os produtos cadastrados.
    public List<Produto> buscarProdutos() {
        try {
            //comando sql
            String sql = "select * from produto";
            PreparedStatement ps = con.prepareStatement(sql);
            //ResultSet, representa o resultado do comando SQL.
            ResultSet rs = ps.executeQuery();
            //cria uma lista de pessoas para retornar
            List<Produto> produtos = new ArrayList();
            //laço para buscar todas os produtos do banco.
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setDescricao(rs.getString("descricao"));
                p.setValor(rs.getDouble("valor"));
                //adicionar produto "p" na lista de produtos.
                produtos.add(p);
            }
            //retorna a lista de produtos.
            return produtos;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
