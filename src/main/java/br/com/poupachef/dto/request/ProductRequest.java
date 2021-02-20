package br.com.poupachef.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductRequest {

    @ApiModelProperty(value = "Identificador único", example = "1")
    private Long id;

    @NotEmpty
    @Size(min = 1, max = 255)
    @ApiModelProperty(value = "Nome do produto", example = "Arroz")
    private String name;

    @NotNull
    @ApiModelProperty(value = "Preço do produto", example = "1.0")
    private Double price;

    @NotNull
    @ApiModelProperty(value = "ID do fornecedor", example = "1")
    private Long supplierId;
}
