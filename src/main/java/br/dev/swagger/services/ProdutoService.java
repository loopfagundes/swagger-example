package br.dev.swagger.services;

import br.dev.swagger.dto.Produto;
import br.dev.swagger.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> allProducts() {
        try {
            return produtoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Produto> getIdProduct(Long id) {
        try {
            return produtoRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public Produto save(Produto produto) {
        try {
            return produtoRepository.save(produto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            produtoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}