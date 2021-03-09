package com.bonkost.bankmanagement.model.dto;

import com.bonkost.bankmanagement.model.enumeration.OperationType;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Vladimir Luchnikov
 */

@Data
@ApiModel("Deposit or withdrawal operation request")
public final class PerformAccountOperationRequest {

    @JsonProperty
    @ApiModelProperty("Funds amount")
    public double funds;

    @JsonProperty
    @ApiModelProperty("Deposit or withdrawal")
    public OperationType type;
}
