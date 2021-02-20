package br.com.poupachef.controller;

import br.com.poupachef.dto.request.ProductRequest;
import br.com.poupachef.dto.response.ProductResponse;
import br.com.poupachef.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api(tags = {"Produtos"})
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = "{id}")
    @ApiOperation(value = "Adquire o produto pelo ID", notes = "Retorna o produto pelo ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o produto"),
            @ApiResponse(code = 404, message = "Produto não encontrado")
    })
    public ProductResponse getById(@PathVariable  Long id) {
        return productService.getById(id);
    }

    @GetMapping
    @ApiOperation(value = "Adquire todos os produtos cadastrados", notes = "Retorna lista de produtos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os produtos cadastrados")
    })
    public Page<ProductResponse> getAll(
            @RequestParam(name = "name", defaultValue = "") String name,
            @PageableDefault(size = 5)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "name", direction = Sort.Direction.ASC)
            }) Pageable pageable) {
        return productService.getAll(name, pageable);
    }

    @PostMapping
    @ApiOperation(value = "Cadastra o produto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Produto cadastrado"),
            @ApiResponse(code = 400, message = "Erro na validação do payload")
    })
    public ResponseEntity<Void> create(@Valid @RequestBody ProductRequest productRequest) {
        productService.create(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @ApiOperation(value = "Atualiza o produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto atualizado"),
            @ApiResponse(code = 400, message = "Erro na validação do payload"),
            @ApiResponse(code = 404, message = "Produto não encontrado")
    })
    public void update(@Valid @RequestBody ProductRequest productRequest) {
        productService.update(productRequest);
    }

    @DeleteMapping(path = "{id}")
    @ApiOperation(value = "Remove o produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto removido"),
            @ApiResponse(code = 404, message = "Produto não encontrado")
    })
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
