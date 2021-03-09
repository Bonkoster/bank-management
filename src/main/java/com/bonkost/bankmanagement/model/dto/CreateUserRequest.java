package com.bonkost.bankmanagement.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Vladimir Luchnikov
 */


@Data
@ApiModel("User creation request")
public final class CreateUserRequest {

    @JsonProperty
    @ApiModelProperty("User name")
    private String name;
}
