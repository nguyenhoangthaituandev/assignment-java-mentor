package org.example.services;

import org.example.models.CreditCard;
import org.example.models.PaymentMethod;
import org.example.models.Transaction;
import org.example.models.User;
import java.util.ArrayList;
import java.util.List;

import static org.example.constants.Constant.sc;

public class TransactionService implements ITransactionService {
    private IOTPService otpService = new OTPService();
    private IPaymentService paymentService = new PaymentService();
    private List<Transaction> transactions=new ArrayList<>();
    private IFraudDetectionService fraudDetectionService = new FrauDetectionService();


    @Override
    public boolean processTransaction(User user, double amount, PaymentMethod selectedPaymentMethod) {
        if (fraudDetectionService.isFrauDectection(user,transactions)) {
            fraudDetectionService.callMessage(user);
            return false;
        }

        checkOver5000AndOTP(amount);

        if (selectedPaymentMethod instanceof CreditCard) {
            CreditCard creditCard = (CreditCard) selectedPaymentMethod;
            if (creditCard.hasSufficientBalance(amount)) {
                return paymentService.processPayment(user, amount, selectedPaymentMethod);
            }
        } else if (selectedPaymentMethod.getBalance() >= amount) {
            return paymentService.processPayment(user, amount, selectedPaymentMethod);
        }

        System.out.println("Số dư không đủ, vui lòng chọn phương thức thanh toán khác:");
        displayAvailablePaymentMethod(user);

        PaymentMethod alternativeMethod = getUserSelectedPaymentMethod(user);
        if (alternativeMethod != null ) {
            if (alternativeMethod instanceof CreditCard) {
                CreditCard creditCard = (CreditCard) alternativeMethod;
                if (creditCard.hasSufficientBalance(amount)) {
                    checkOver5000AndOTP(amount);
                    return paymentService.processPayment(user, amount, alternativeMethod);
                }
            } else if (alternativeMethod.getBalance() >= amount) {
                checkOver5000AndOTP(amount);
                return paymentService.processPayment(user, amount, alternativeMethod);
            }
        }

        System.out.println("Không có phương thức đủ số dư để thanh toán, xin vui lòng nạp tiền");
        return false;
    }

    private boolean checkOver5000AndOTP(double amount) {
        if (amount > 5000) {
            String otp = otpService.generateOTP();
            System.out.print("Mã OTP của bạn là: " + otp);

            int attemps = 3;
            while (attemps > 0) {
                System.out.print("Enter your otp: ");
                sc.nextLine();
                String userOTP = sc.nextLine();
                if (otpService.verifyOTP(otp, userOTP)) {
                    System.out.println("Xác minh thành công");
                    break;
                } else {
                    attemps--;
                    System.out.printf("Nhập sai OTP, bạn còn %s lần thử %n", attemps);
                }
            }
            if (attemps == 0) {
                System.out.println("Nhập sai OTP 3 lần, hủy giao dịch");
                return false;
            }
        }
        return true;
    }

    private void displayAvailablePaymentMethod(User user){
        int index = 1;
        for (PaymentMethod method : user.getPaymentMethods()) {
            if(method instanceof CreditCard){
                CreditCard creditCard = (CreditCard) method;
                System.out.println(index+". " +method.getClass().getSimpleName() +" hạn mức khả dụng là: "+(creditCard.getBalance()+creditCard.getLimit()));
            }
            System.out.println(index + ". " + method.getClass().getSimpleName() + " số dư là: " + method.getBalance());
            index++;
        }
    }

    private PaymentMethod getUserSelectedPaymentMethod(User user) {
        int methodIndex = sc.nextInt() - 1;
        if (methodIndex >= 0 && methodIndex < user.getPaymentMethods().size()) {
            return user.getPaymentMethods().get(methodIndex);
        }
        System.out.println("Lựa chọn không hợp lệ.");
        return null;
    }
}
