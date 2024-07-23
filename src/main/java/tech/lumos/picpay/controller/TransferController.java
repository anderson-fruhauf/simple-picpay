package tech.lumos.picpay.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.lumos.picpay.controller.dto.TransferDTO;
import tech.lumos.picpay.entity.Transfer;
import tech.lumos.picpay.service.TransferService;

@RestController
@RequestMapping("/transfer")
@AllArgsConstructor
public class TransferController {
    private final TransferService  transferService;

    @PostMapping
    public ResponseEntity<Transfer> transfer( @RequestBody @Valid TransferDTO dto){
        var response = transferService.transfer(dto);

        return ResponseEntity.ok(response);
    }
}
