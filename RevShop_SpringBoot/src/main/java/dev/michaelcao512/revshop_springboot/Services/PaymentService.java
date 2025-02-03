package dev.michaelcao512.revshop_springboot.Services;

import dev.michaelcao512.revshop_springboot.DTO.PaymentDto;
import dev.michaelcao512.revshop_springboot.Entities.Order;
import dev.michaelcao512.revshop_springboot.Entities.Payment;
import dev.michaelcao512.revshop_springboot.Repositories.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    public Payment processPayment(Order order, PaymentDto request) {

//        TODO: payment gateway -> processes credit card, etc.
        String paymentMethod = request.paymentMethod();
        String cardNumber = request.cardNumber();
        String expiryDate = request.expiryDate();
        String cvv = request.cvv();

        boolean paymentSuccess = true;

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotal());
        payment.setPaymentMethod(request.paymentMethod());
        payment.setStatus(paymentSuccess ? Payment.PaymentStatus.PROCESSED : Payment.PaymentStatus.CANCELLED);

        return paymentRepository.save(payment);
    }
}
