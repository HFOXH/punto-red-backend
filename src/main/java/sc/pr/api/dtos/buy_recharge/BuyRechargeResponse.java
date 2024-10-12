package sc.pr.api.dtos.buy_recharge;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class BuyRechargeResponse {
    private String message;
    private String transactionalID;
    private String cellPhone;
    private Integer value;
}
