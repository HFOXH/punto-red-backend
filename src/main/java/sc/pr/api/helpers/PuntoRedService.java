package sc.pr.api.helpers;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sc.pr.api.dtos.auth.AuthRequest;
import sc.pr.api.dtos.auth.AuthResponse;
import sc.pr.api.dtos.buy_recharge.BuyRechargeRequest;
import sc.pr.api.dtos.buy_recharge.BuyRechargeResponse;
import sc.pr.api.dtos.suppliers.SupplierResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PuntoRedService {
    private final Environment env;
    private final PuntoRed puntoRed;
    private String token;

    @Autowired
    public PuntoRedService(Environment env){
        this.env = env;
        this.puntoRed = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(PuntoRed.class, env.getProperty("puntored.url"));
    }

    public void authenticate() {
        String apiKey = getApiKey();
        AuthRequest authRequest = AuthRequest.builder()
                .user(env.getProperty("puntored.user"))
                .password(env.getProperty("puntored.password"))
                .build();
        AuthResponse authResponse = puntoRed.createAuthentication(apiKey, authRequest);
        this.token = authResponse.getToken().replace("Bearer ", "");
    }

    public List<SupplierResponse> getSuppliers() {
        ensureAuthenticated();
        return puntoRed.getSuppliers(this.token);
    }

    public BuyRechargeResponse buyProduct(BuyRechargeRequest request) {
        ensureAuthenticated();
        return puntoRed.buyProduct(this.token, request);
    }

    private void ensureAuthenticated() {
        if (token == null || token.isEmpty()) {
            authenticate();
        }
    }

    private String getApiKey() {
        return env.getProperty("puntored.api-key");
    }
}
