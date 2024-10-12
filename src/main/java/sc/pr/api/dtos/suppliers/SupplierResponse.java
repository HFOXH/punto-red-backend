package sc.pr.api.dtos.suppliers;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class SupplierResponse {
    private String id;
    private String name;
}
