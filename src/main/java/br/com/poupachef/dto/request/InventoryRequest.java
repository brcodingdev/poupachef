package br.com.poupachef.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class InventoryRequest {

    @NotNull
    @Min(value = 1)
    private Long productId;

    @NotNull
    @Min(value = 1)
    private Integer qty;
}
