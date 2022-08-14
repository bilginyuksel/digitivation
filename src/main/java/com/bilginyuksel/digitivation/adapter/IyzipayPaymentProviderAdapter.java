package com.bilginyuksel.digitivation.adapter;

import com.bilginyuksel.digitivation.BusinessException;
import com.bilginyuksel.digitivation.adapter.configuration.IyzipayConfiguration;
import com.bilginyuksel.digitivation.payment.PaymentProvider;
import com.bilginyuksel.digitivation.payment.model.Card;
import com.bilginyuksel.digitivation.payment.model.CompletePayment;
import com.bilginyuksel.digitivation.payment.model.Customer;
import com.bilginyuksel.digitivation.payment.model.Payment;
import com.iyzipay.Options;
import com.iyzipay.model.*;
import com.iyzipay.request.CreatePaymentRequest;
import com.iyzipay.request.CreateThreedsPaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;

@Slf4j
@Component
public class IyzipayPaymentProviderAdapter implements PaymentProvider {
    private final static String IYZIPAY_FAILURE_STATUS_MSG = "failure";
    private final static int INSTALLMENT = 1;

    private final IyzipayConfiguration iyzipayConfiguration;
    private final Options options;

    public IyzipayPaymentProviderAdapter(IyzipayConfiguration configuration) {
        this.iyzipayConfiguration = configuration;

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
        request.setPaymentChannel(PaymentChannel.WEB.name());
        request.setPaymentGroup(PaymentGroup.PRODUCT.name());
        request.setCallbackUrl(iyzipayConfiguration.getCallbackURL());

        request.setBasketItems(Collections.singletonList(createBasketItem(payment.getResourceId(), payment.getPrice())));
        request.setBillingAddress(createBillingAddress());
        request.setPaymentCard(createPaymentCard(payment.getCard()));
        request.setBuyer(createBuyer(payment.getCustomer()));

        var response = ThreedsInitialize.create(request, options);
        if (isIyzipayResponseFailed(response.getStatus())) {
            log.error("[iyzipay] 3d secure payment initialization failed due to provider error, response= {}", response);
            throw new BusinessException(response.getErrorCode(), "Payment provider 3d secure payment initialization failed");
        }

        log.info("[iyzipay] 3d secure payment initialized");
        return response.getHtmlContent();
    }

    @Override
    public void completePayment(CompletePayment payment) {
        var request = new CreateThreedsPaymentRequest();
        request.setLocale(Locale.TR.getValue());
        request.setPaymentId(payment.getPaymentId());
        request.setConversationId(payment.getResourceId());
        request.setConversationData(payment.getExtras());

        var response = ThreedsPayment.create(request, options);

        if (isIyzipayResponseFailed(response.getStatus())) {
            log.error("[iyzipay] 3d secure payment complete failed due to provider, request= {}, response= {}", request, response);
            throw new BusinessException(response.getErrorCode(), "Payment provider 3d secure could not completed");
        }

        log.info("[iyzipay] 3d secure payment complete request= {}, response= {}", request, response);
    }

    private boolean isIyzipayResponseFailed(String status) {
        return status.equals(IYZIPAY_FAILURE_STATUS_MSG);
    }

    private BasketItem createBasketItem(String resourceId, double price) {
        var item = new BasketItem();
        item.setId(resourceId);
        item.setPrice(BigDecimal.valueOf(price));
        item.setItemType(BasketItemType.VIRTUAL.name());
        item.setCategory1("invitation");
        item.setName("Invitation");

        return item;
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

    private Address createBillingAddress() {
        var billingAddress = new Address();
        billingAddress.setCity(iyzipayConfiguration.getBillingAddress().getCity());
        billingAddress.setCountry(iyzipayConfiguration.getBillingAddress().getCountry());
        billingAddress.setAddress(iyzipayConfiguration.getBillingAddress().getAddress());
        billingAddress.setContactName(iyzipayConfiguration.getBillingAddress().getContactName());

        return billingAddress;
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
