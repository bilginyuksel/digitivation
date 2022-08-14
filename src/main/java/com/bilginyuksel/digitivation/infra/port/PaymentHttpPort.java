package com.bilginyuksel.digitivation.infra.port;

import com.bilginyuksel.digitivation.BusinessUseCase;
import com.bilginyuksel.digitivation.infra.port.request.ThreeDSecurePaymentRequest;
import com.bilginyuksel.digitivation.infra.port.request.ThreeDSecurePaymentResult;
import com.bilginyuksel.digitivation.infra.port.response.ThreeDSecureInitiatePaymentResponse;
import com.bilginyuksel.digitivation.payment.model.CompletePayment;
import com.bilginyuksel.digitivation.payment.model.Payment;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/payments")
@AllArgsConstructor
public class PaymentHttpPort {
    private BusinessUseCase<Payment, String> useCaseInitiateThreeDSecurePayment;
    private BusinessUseCase<CompletePayment, String> useCaseReceiveThreeDSecurePaymentResult;

    @PostMapping
    public ResponseEntity<ThreeDSecureInitiatePaymentResponse> initiateThreeDSecurePayment(HttpServletRequest servletRequest, @RequestBody ThreeDSecurePaymentRequest request) {
        var ipAddress = servletRequest.getRemoteAddr();
        var htmlContent = useCaseInitiateThreeDSecurePayment.handle(request.toPayment(ipAddress));
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(new ThreeDSecureInitiatePaymentResponse(htmlContent));
    }

    @PostMapping("/callback")
    public ResponseEntity<String> receiveThreeDSecurePaymentResult(@RequestBody ThreeDSecurePaymentResult result) {
        return ResponseEntity
                .status(HttpStatus.TEMPORARY_REDIRECT)
                .location(URI.create("https://google.com"))
                .body(useCaseReceiveThreeDSecurePaymentResult.handle(result.toCompletePayment()));
    }
}
