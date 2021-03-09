package com.bonkost.bankmanagement.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Vladimir Luchnikov
 */

@Data
@ApiModel("Retrieved user account")
public final class GetUserAccountResponse {

    @JsonProperty
    @ApiModelProperty("User account id")
    private long id;

    @JsonProperty
    @ApiModelProperty("User account current fund")
    private double funds;
}
