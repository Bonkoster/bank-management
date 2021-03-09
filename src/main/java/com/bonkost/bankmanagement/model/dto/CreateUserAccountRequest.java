package com.bonkost.bankmanagement.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Vladimir Luchnikov
 */

@Data
@ApiModel("User account creation request")
public final class CreateUserAccountRequest {

    @JsonProperty("user_id")
    @ApiModelProperty("Account owner id")
    private long userId;
}
