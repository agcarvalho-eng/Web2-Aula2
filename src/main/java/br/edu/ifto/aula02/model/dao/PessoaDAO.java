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

public class PessoaDAO {

    //criar um objeto Connection para receber a conexão
    Connection con;

    public PessoaDAO(){
        con = MinhaConexao.conexao();
    }

    public Produto buscarPessoa(int id) {
        try {
            //comando sql
            String sql = "select * from pessoa where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setInt(1, id);
            //ResultSet, representa o resultado do comando SQL
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Produto> buscarPessoas() {
        try {
            //comando sql
            String sql = "select * from pessoa";
            PreparedStatement ps = con.prepareStatement(sql);
            //ResultSet, representa o resultado do comando SQL
            ResultSet rs = ps.executeQuery();
            //cria uma lista de pessoas para retornar
            List<Produto> produtos = new ArrayList();
            //laço para buscar todas as pessoas do banco
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                //add pessoa na lista
                produtos.add(p);
            }
            //retorna a lista de pessoas
            return produtos;
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
