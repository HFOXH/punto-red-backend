package sc.pr.api.dtos.buy_recharge;

import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Setter
public class BuyRechargeRequest {
    private String cellPhone;
    private String value;
    private String supplierId;
}
