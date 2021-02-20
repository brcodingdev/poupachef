package br.com.poupachef.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRequest {

    @NotEmpty
    @ApiModelProperty(value = "Usuário de acesso", example = "user")
    private String username;

    @NotEmpty
    @ApiModelProperty(value = "Senha de acesso", example = "pass")
    private String password;
}
