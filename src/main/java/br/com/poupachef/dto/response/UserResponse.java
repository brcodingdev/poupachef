package br.com.poupachef.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserResponse {

    @ApiModelProperty(value = "Identificador único", example = "1")
    private Long id;
    @ApiModelProperty(value = "Usuário de acesso", example = "user")
    private String username;
    @ApiModelProperty(value = "Permissão de acesso", example = "ADMIN")
    private String role;
}
