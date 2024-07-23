package tech.lumos.picpay.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.lumos.picpay.client.AuthorizationClient;
import tech.lumos.picpay.controller.dto.TransferDTO;
import tech.lumos.picpay.exception.PicPayException;

@Service
@AllArgsConstructor
public class AuthorizationService {
    private final AuthorizationClient authorizationClient;

    public boolean isAuthorized(TransferDTO transfer) {
        var response = authorizationClient.isAuthorized();

        if (response.getStatusCode().isError()) {
            throw new PicPayException();
        }

        return response.getBody().authorized();
    }
}
