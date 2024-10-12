package sc.pr.api.controllers.transaction;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sc.pr.api.dtos.auth.AuthResponse;
import sc.pr.api.dtos.buy_recharge.BuyRechargeRequest;
import sc.pr.api.dtos.buy_recharge.BuyRechargeResponse;
import sc.pr.api.dtos.suppliers.SupplierResponse;
import sc.pr.api.helpers.PuntoRedService;
import sc.pr.entities_repositories_and_services.transaction.Transaction;
import sc.pr.entities_repositories_and_services.transaction.TransactionService;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class TransactionController {
    private final PuntoRedService puntoRedService;
    @Autowired
    private final TransactionService transactionService;

    @GetMapping("/suppliers")
    public List<SupplierResponse> getSuppliers() {
        return puntoRedService.getSuppliers();
    }

    @PostMapping("/buy")
    public BuyRechargeResponse buyProduct(@RequestBody BuyRechargeRequest request) {
        BuyRechargeResponse response = puntoRedService.buyProduct(request);
        /*if (!response.getTransactionalID().isEmpty()){
            Transaction transaction = Transaction.builder()
                    .cellPhone(response.getCellPhone())
                    .message(response.getMessage())
                    .transactionalId(UUID.fromString(response.getTransactionalID()))
                    .value(response.getValue())
                    .transactionDate(new Timestamp(System.currentTimeMillis()))
                    .build();
            transactionService.saveTransaction(transaction);
        }*/
        // Maybe, probable the api return the same values if its ok or not so...
        Transaction transaction = Transaction.builder()
                .cellPhone(response.getCellPhone())
                .message(response.getMessage())
                .transactionalId(UUID.fromString(response.getTransactionalID()))
                .value(response.getValue())
                .transactionDate(new Timestamp(System.currentTimeMillis()))
                .build();
        transactionService.saveTransaction(transaction);
        return response;
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }
}
