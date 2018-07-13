package io.zensoft.share.model.diesel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestResponse {
    @Enumerated(value = EnumType.STRING)
    private RequestStatus status;
}
