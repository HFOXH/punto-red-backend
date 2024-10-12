package sc.pr.api.helpers;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;
import sc.pr.api.dtos.auth.AuthRequest;
import sc.pr.api.dtos.auth.AuthResponse;
import sc.pr.api.dtos.buy_recharge.BuyRechargeRequest;
import sc.pr.api.dtos.buy_recharge.BuyRechargeResponse;
import sc.pr.api.dtos.suppliers.SupplierResponse;

import java.util.List;

public interface PuntoRed {
    @RequestLine("POST /auth")
    @Headers({"Content-Type: application/json", "x-api-key: {apiKey}"})
    AuthResponse createAuthentication(@Param("apiKey") String apiKey, @RequestBody AuthRequest body);

    @RequestLine("GET /getSuppliers")
    @Headers({"Content-Type: application/json", "Authorization: Bearer {token}"})
    List<SupplierResponse> getSuppliers(@Param("token") String token);

    @RequestLine("POST /buy")
    @Headers({"Content-Type: application/json", "Authorization: Bearer {token}"})
    BuyRechargeResponse buyProduct(@Param("token") String token, @RequestBody BuyRechargeRequest body);
}
