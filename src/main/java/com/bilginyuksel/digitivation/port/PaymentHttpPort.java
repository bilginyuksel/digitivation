package com.bilginyuksel.digitivation.port;

import com.bilginyuksel.digitivation.BusinessUseCase;
import com.bilginyuksel.digitivation.payment.model.Payment;
import com.bilginyuksel.digitivation.port.request.ThreeDSecurePaymentRequest;
import com.bilginyuksel.digitivation.port.request.ThreeDSecurePaymentResult;
import com.bilginyuksel.digitivation.port.response.ThreeDSecureInitiatePaymentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/payments")
public class PaymentHttpPort {
    private BusinessUseCase<Payment, String> useCaseInitiateThreeDSecurePayment;
    private BusinessUseCase<String, String> useCaseReceiveThreeDSecurePaymentResult;

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
                .body(useCaseReceiveThreeDSecurePaymentResult.handle(result.toString()));
    }
}
