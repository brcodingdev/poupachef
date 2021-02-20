package br.com.poupachef.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductResponse {

    @ApiModelProperty(value = "Identificador único", example = "1")
    private Long id;
    @ApiModelProperty(value = "Nome", example = "Arroz")
    private String name;
    @ApiModelProperty(value = "Preço", example = "1.0")
    private Double price;
    @ApiModelProperty(value = "Quantidade no estoque", example = "1")
    private Integer qty;
    private SupplierResponse supplier;
    @ApiModelProperty(value = "Data do cadastro", example = "2021-02-19T14:06:43.891Z")
    private LocalDateTime createdAt;
    @ApiModelProperty(value = "Data da última atualização", example = "2021-02-19T14:06:43.891Z")
    private LocalDateTime updatedAt;

}
