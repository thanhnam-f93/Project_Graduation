package net.javaguides.springboot.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import net.javaguides.springboot.dto.PaymentDto;
import net.javaguides.springboot.entity.PaymentHistory;
import net.javaguides.springboot.entity.ServiceFee;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.paypal.enums.PaypalPaymentIntent;
import net.javaguides.springboot.paypal.enums.PaypalPaymentMethod;
import net.javaguides.springboot.services.PaypalService;
import net.javaguides.springboot.repository.PaymentHistoryRepository;
import net.javaguides.springboot.repository.ServiceFeeRepository;
import net.javaguides.springboot.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/paypal")
public class PaypalController {
    public static final String URL_PAYPAL_SUCCESS = "pay/success";
    public static final String URL_PAYPAL_CANCEL = "pay/cancel";
    Logger logger = LoggerFactory.getLogger(PaypalController.class);
    Date date = new Date();
    Calendar cal = Calendar.getInstance();
    @Autowired
    private PaypalService paypalService;
    @Autowired
    private ServiceFeeRepository serviceFeeRepository;
    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/service-fee")
    public ResponseEntity<?> getService() {
        return new ResponseEntity<>(serviceFeeRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save-history")
    public ResponseEntity<?> saveHistory(@RequestBody PaymentDto bill) {

        User user = userRepository.findOneByEmail(bill.getEmail());
        ServiceFee serviceFee = serviceFeeRepository.findServiceFeeByPrice(bill.getPrice());
        PaymentHistory existHistory = paymentHistoryRepository.getPaymentHistoriesByUsersAndStatus(user, true);
        if (existHistory != null) {
            cal.setTime(existHistory.getDateEnd());
            existHistory.setTotalPrice(existHistory.getTotalPrice() + serviceFee.getPrice());
            if (serviceFee.getNameService().equals("1 tháng")) {
                cal.add(Calendar.MONTH, 1);
                existHistory.setDateEnd(cal.getTime());
            } else if (serviceFee.getNameService().equals("3 tháng")) {
                cal.add(Calendar.MONTH, 3);
                existHistory.setDateEnd(cal.getTime());
            } else if (serviceFee.getNameService().equals("6 tháng")) {
                cal.add(Calendar.MONTH, 6);
                existHistory.setDateEnd(cal.getTime());
            } else {
                cal.add(Calendar.MONTH, 12);
                existHistory.setDateEnd(cal.getTime());
            }
            return new ResponseEntity<>(paymentHistoryRepository.save(existHistory), HttpStatus.OK);
        }
        PaymentHistory paymentHistory = new PaymentHistory();
        paymentHistory.setServiceFee(serviceFee);
        paymentHistory.setUsers(user);
        paymentHistory.setStatus(true);
        paymentHistory.setDateStart(date);
        paymentHistory.setTotalPrice(serviceFee.getPrice());
        if (serviceFee.getNameService().equals("1 tháng")) {
            cal.add(Calendar.MONTH, 1);
            date = cal.getTime();
            paymentHistory.setDateEnd(date);
        } else if (serviceFee.getNameService().equals("3 tháng")) {
            cal.add(Calendar.MONTH, 3);
            date = cal.getTime();
            paymentHistory.setDateEnd(date);
        } else if (serviceFee.getNameService().equals("6 tháng")) {
            cal.add(Calendar.MONTH, 6);
            date = cal.getTime();
            paymentHistory.setDateEnd(date);
        } else {
            cal.add(Calendar.MONTH, 12);
            date = cal.getTime();
            paymentHistory.setDateEnd(date);
        }

        return new ResponseEntity<>(paymentHistoryRepository.saveAndFlush(paymentHistory), HttpStatus.OK);
    }

    @GetMapping("/payment-history/{email}")
    public ResponseEntity<List<?>> getPaymentHistory(@PathVariable String email) {
        System.out.println(email);
        User user = userRepository.findOneByEmail(email);
        if (user == null) {
            return new ResponseEntity(new ResourceNotFoundException(email + "is not exist"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<?>>(paymentHistoryRepository.findAllByUsers(user), HttpStatus.OK) {
        };
    }

    @PostMapping("/pay")
    public  String pay(HttpServletRequest request, @RequestBody double price) {
//        String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
        String cancelUrl = "http://localhost:3000/admin/failed";
        String successUrl = "http://localhost:3000/admin/success";
        try {
            Payment payment = paypalService.createPayment(
                    price,
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "payment description",
                    cancelUrl,
                    successUrl);
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            logger.error(e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping(URL_PAYPAL_CANCEL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(URL_PAYPAL_SUCCESS)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            logger.error(e.getMessage());
        }
        return "redirect:/";
    }
}
