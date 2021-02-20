package br.com.poupachef.controller;

import br.com.poupachef.dto.request.InventoryRequest;
import br.com.poupachef.service.InventoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api(tags = {"Estoque"})
@RequestMapping("/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    @PutMapping(path = "/add/{productId}/{qty}")
    @ApiOperation(value = "Adiciona produto ao estoque")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Qtde atualizada"),
            @ApiResponse(code = 400, message = "Erro na validação do payload")
    })
    public void add(@Valid InventoryRequest inventoryRequest) {
        inventoryService.add(inventoryRequest);
    }

    @PutMapping(path = "/subtract/{productId}/{qty}")
    @ApiOperation(value = "Subtrai produto ao estoque")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Qtde atualizada"),
            @ApiResponse(code = 400, message = "Erro na validação do payload")
    })
    public void subtract(@Valid InventoryRequest inventoryRequest) {
        inventoryService.subtract(inventoryRequest);
    }
}
