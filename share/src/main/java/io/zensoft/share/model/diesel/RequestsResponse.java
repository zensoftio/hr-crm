package io.zensoft.share.model.diesel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestsResponse {
    @Enumerated(value = EnumType.STRING)
    private RequestStatus status;
}
