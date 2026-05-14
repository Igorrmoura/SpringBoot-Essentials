package br.com.Igor.spring_boot_essentials.controller;


import br.com.Igor.spring_boot_essentials.dto.ProdutoDto;
import br.com.Igor.spring_boot_essentials.exceptions.NotFoundException;
import br.com.Igor.spring_boot_essentials.model.ProdutoEntity;
import br.com.Igor.spring_boot_essentials.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List< ProdutoEntity> findAll() {
        return produtoService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoEntity createdProduct (@RequestBody ProdutoDto produtoDto) {
        return produtoService.createdProduct(produtoDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoEntity updateProduct (@PathVariable Integer id, @RequestBody ProdutoDto produtoDto)
    throws NotFoundException {
        return produtoService.atualizarProduto(produtoDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Integer id) {
        produtoService.removerProduto(id);
    }
}
