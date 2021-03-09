package com.bonkost.bankmanagement.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Vladimir Luchnikov
 */

@Data
public final class GetUserResponse {

    @JsonProperty
    private long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String email;
}
