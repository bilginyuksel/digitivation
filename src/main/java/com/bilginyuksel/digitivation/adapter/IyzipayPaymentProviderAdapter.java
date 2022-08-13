package com.bilginyuksel.digitivation.adapter;

import com.bilginyuksel.digitivation.adapter.configuration.IyzipayConfiguration;
import com.bilginyuksel.digitivation.payment.PaymentProvider;
import com.bilginyuksel.digitivation.payment.model.Card;
import com.bilginyuksel.digitivation.payment.model.Customer;
import com.bilginyuksel.digitivation.payment.model.Payment;
import com.iyzipay.Options;
import com.iyzipay.model.*;
import com.iyzipay.request.CreatePaymentRequest;
import com.iyzipay.request.CreateThreedsPaymentRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class IyzipayPaymentProviderAdapter implements PaymentProvider {
    private final static int INSTALLMENT = 1;
    private final static String CALLBACK_URL = "http://localhost:8080/payments/callback";

    private final Options options;

    public IyzipayPaymentProviderAdapter(IyzipayConfiguration configuration) {
        options = new Options();
        options.setApiKey(configuration.getApiKey());
        options.setBaseUrl(configuration.getBaseURL());
        options.setSecretKey(configuration.getSecretKey());
    }

    @Override
    public String initiatePayment(Payment payment) {
        var request = new CreatePaymentRequest();
        request.setLocale(Locale.TR.getValue());
        request.setConversationId(payment.getResourceId());
        request.setPrice(BigDecimal.valueOf(payment.getPrice()));
        request.setPaidPrice(BigDecimal.valueOf(payment.getPaidPrice()));
        request.setCurrency(Currency.TRY.name());
        request.setInstallment(INSTALLMENT);
//        request.setBasketId("something");
        request.setPaymentChannel(PaymentChannel.WEB.name());
        request.setPaymentGroup(PaymentGroup.PRODUCT.name());
        request.setCallbackUrl(CALLBACK_URL);

        request.setPaymentCard(createPaymentCard(payment.getCard()));
        request.setBuyer(createBuyer(payment.getCustomer()));

        var response = ThreedsInitialize.create(request, options);
        // TODO: throw error if returns error
        return response.getHtmlContent();
    }

    @Override
    public void completePayment() {
        var request = new CreateThreedsPaymentRequest();
        request.setLocale(Locale.TR.getValue());
        request.setConversationId("");
        request.setConversationData("");
        request.setPaymentId("");

        var response = ThreedsPayment.create(request, options);
    }

    private PaymentCard createPaymentCard(Card card) {
        var paymentCard = new PaymentCard();
        paymentCard.setCardHolderName(card.getCardHolderName());
        paymentCard.setCardNumber(card.getCardNumber());
        paymentCard.setExpireMonth(card.getExpireMonth());
        paymentCard.setExpireYear(card.getExpireYear());
        paymentCard.setCvc(card.getCvc());

        return paymentCard;
    }

    private Buyer createBuyer(Customer c) {
        var buyer = new Buyer();
        buyer.setId(c.getId());
        buyer.setName(c.getName());
        buyer.setSurname(c.getSurname());
        buyer.setGsmNumber(c.getGsmNumber());
        buyer.setEmail(c.getEmail());
        buyer.setIdentityNumber(c.getIdentityNumber());
        buyer.setRegistrationAddress(c.getRegistrationAddress());
        buyer.setIp(c.getIpAddress());
        buyer.setCity(c.getCity());
        buyer.setCountry(c.getCountry());

        return buyer;
    }
}
