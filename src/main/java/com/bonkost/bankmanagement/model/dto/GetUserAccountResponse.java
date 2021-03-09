package com.bonkost.bankmanagement.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Vladimir Luchnikov
 */

@Data
public final class GetUserAccountResponse {

    @JsonProperty
    private long id;

    @JsonProperty
    private double fund;
}
