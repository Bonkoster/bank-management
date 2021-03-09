package com.bonkost.bankmanagement.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Vladimir Luchnikov
 */

@Data
@ApiModel("Retrieved user")
public final class GetUserResponse {

    @JsonProperty
    @ApiModelProperty("User id")
    private long id;

    @JsonProperty
    @ApiModelProperty("User name")
    private String name;
}
