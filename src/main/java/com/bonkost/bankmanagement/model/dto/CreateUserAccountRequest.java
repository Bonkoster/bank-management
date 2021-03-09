package com.bonkost.bankmanagement.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Vladimir Luchnikov
 */

@Data
public final class CreateUserAccountRequest {

    @JsonProperty("user_id")
    private long userId;
}
