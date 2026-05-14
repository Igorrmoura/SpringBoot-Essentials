package br.com.Igor.spring_boot_essentials.service;

import br.com.Igor.spring_boot_essentials.dto.ProdutoDto;
import br.com.Igor.spring_boot_essentials.model.ProdutoEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    // Lista mutável
    private static final List<ProdutoEntity> PRODUTOS = new ArrayList<>();

    // Bloco estático para inicializar os produtos
    static {

        PRODUTOS.add(ProdutoEntity.builder()
                .id(1)
                .name("Notebook")
                .price(new BigDecimal("5000"))
                .amount(10)
                .build());

        PRODUTOS.add(ProdutoEntity.builder()
                .id(2)
                .name("Iphone")
                .price(new BigDecimal("7000"))
                .amount(10)
                .build());

        PRODUTOS.add(ProdutoEntity.builder()
                .id(3)
                .name("Mouse")
                .price(new BigDecimal("500"))
                .amount(10)
                .build());
    }

    public List<ProdutoEntity> findAll() {
        return new ArrayList<>(PRODUTOS);
    }

    public ProdutoEntity createdProduct(ProdutoDto produtoDto) {

        Integer identificador = PRODUTOS.stream()
                .mapToInt(ProdutoEntity::getId)
                .max()
                .orElse(0) + 1;

        ProdutoEntity novoProduto = ProdutoEntity.builder()
                .id(identificador)
                .name(produtoDto.getName())
                .price(produtoDto.getPrice())
                .amount(produtoDto.getAmount())
                .build();

        PRODUTOS.add(novoProduto);

        return novoProduto;
    }
    public ProdutoEntity atualizarProduto (ProdutoDto produtoDto, Integer id) {

        ProdutoEntity produto = PRODUTOS.stream()
                .filter(p -> p.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new RuntimeException("produto não encontrado"));

        produto.setName(produto.getName());
        produto.setPrice(produto.getPrice());
        produto.setAmount(produto.getAmount());
        return produto;
    }

    public void removerProduto(Integer id) {
        PRODUTOS.removeIf(p -> p.getId().equals(id));
    }
}