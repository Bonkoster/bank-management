package com.bonkost.bankmanagement.model.dto;

import com.bonkost.bankmanagement.model.enumeration.OperationType;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Vladimir Luchnikov
 */

@Data
@ApiModel("Retrieved account operation")
public final class GetAccountOperationResponse {

    @JsonProperty
    @ApiModelProperty("Current fund amount")
    public double fundsAmount;

    @JsonProperty
    @ApiModelProperty("Deposit or withdrawal")
    public OperationType type;

    @JsonProperty("created_at")
    @ApiModelProperty("Creation date")
    public Date createdAt;
}
