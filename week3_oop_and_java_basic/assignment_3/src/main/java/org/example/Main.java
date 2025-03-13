package org.example;


import org.example.models.*;
import org.example.services.*;

import java.util.ArrayList;
import java.util.List;

import static org.example.constants.Constant.sc;

public class Main {
    public static IUserService userService = new UserServiceImp();
    public static User currentUser;
    public static TransactionService transactionService = new TransactionService();
    public static IRefundService refundService = new RefundServiceImp();
    public static IFinancialReportService financialReportService = new FinancialReportImp();


    public static void main(String[] args) {
        while (true) {
            System.out.println("-----------WELCOME TO PAYMENT SYSTEM------------");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    userService.register();
                    break;
                case 2:
                    System.out.print("Enter your username: ");
                    String username = sc.nextLine();
                    System.out.print("Enter your password: ");
                    String password = sc.nextLine();
                    currentUser = userService.login(username, password);
                    if (currentUser != null) {
                        showMenu();
                    }
                    break;
                default:
                    System.out.println("Exiting...");
            }
            if (choice == 3) {
                break;
            }
        }
    }

    private static void showMenu() {
        while (true) {
            System.out.println("-----------WELCOME TO PAYMENT SYSTEM------------");
            System.out.println("0. Nạp tiền");
            System.out.println("1. Thực hiện thanh toán");
            System.out.println("2. Yêu cầu hoàn tiền");
            System.out.println("3. Báo cáo tài chính");
            System.out.println("4. Cài đặt tài khoản");
            System.out.println("5. Đăng xuất");
            System.out.println("6. Exit");
            System.out.print("Choose your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 0:
                    System.out.print("Nhập số dư cho Credit Card: ");
                    double creditCardBalance = sc.nextDouble();
                    System.out.print("Nhập hạn mức cho Credit Card: ");
                    double creditCardLimit = sc.nextDouble();

                    System.out.print("Nhập số dư cho EWallet: ");
                    double eWalletBalance = sc.nextDouble();

                    System.out.print("Nhập số dư cho Bank account: ");
                    double bankBalance = sc.nextDouble();

                    List<PaymentMethod> userPayments = new ArrayList<>();
                    userPayments.add(new CreditCard(creditCardBalance, creditCardLimit));
                    userPayments.add(new EWallet(eWalletBalance));
                    userPayments.add(new BankTransfer(bankBalance));
                    currentUser.setPaymentMethods(userPayments);
                    currentUser.displayPaymentMethods();
                    break;
                case 1:
                    System.out.print("Nhập số tiền thanh toán: ");
                    double amount = sc.nextDouble();
                    System.out.println("Phương thức thanh toán khả dụng là: ");
                    for (int i = 0; i < currentUser.getPaymentMethods().size(); i++) {
                        System.out.println((i + 1) + ". " + currentUser.getPaymentMethods().get(i).getClass().getSimpleName());
                    }
                    System.out.print("Mời bạn chọn phương thức thanh toán: ");
                    int methodIndex = sc.nextInt() - 1;

                    if (methodIndex < 0 || methodIndex >= currentUser.getPaymentMethods().size()) {
                        System.out.println("Lựa chọn không hợp lệ.");
                        continue;
                    }

                    PaymentMethod paymentMethod = currentUser.getPaymentMethods().get(methodIndex);

                    transactionService.processTransaction(currentUser, amount, paymentMethod);

                    break;
                case 2:
                    List<Transaction> refundalbleTransactions = refundService.getRefundableTransaction(currentUser, transactionService);
                    if (refundalbleTransactions.isEmpty()) {
                        System.out.println("Không có giao dịch trong vòng 7 ngày");
                        return;
                    }


                    for (int i = 0; i < refundalbleTransactions.size(); i++) {
                        Transaction t = refundalbleTransactions.get(i);
                        System.out.println(i + 1 + ". " + t.getUser().getUsername() + " với giao dịch " + t.getAmount() + " với phương thức thanh toán " + t.getPaymentMethod().getClass().getSimpleName());
                    }
                    System.out.print("Chọn giao dịch để hoàn tiền: ");
                    int indexRefundTransaction = sc.nextInt() - 1;
                    if (indexRefundTransaction < 0 || indexRefundTransaction >= refundalbleTransactions.size()) {
                        System.out.println("Lựa chọn không hợp lệ");
                        return;
                    }

                    Transaction selectedTransaction = refundalbleTransactions.get(indexRefundTransaction);
                    refundService.processRefund(currentUser, selectedTransaction);
                    break;
                case 3:
                    financialReportService.generateReport(currentUser, transactionService);
                    break;
                case 4:
                    settingAccount();
                    break;
                case 5:
                    currentUser.logout(currentUser);
                    return;
                default:
                    System.out.println("Exiting...");
            }
            if (choice == 6) {
                break;
            }
        }
    }

    static void  settingAccount() {
        while (true) {
            System.out.println("Cài đặt tài khoản");
            System.out.println("1. Thay đổi mật khẩu");
            System.out.println("2. Thêm phương thức thanh toán mới");
            System.out.println("3. Xóa phương thức thanh toán cũ");
            System.out.println("4. Xem thông tin bảo mật tài khoản");
            System.out.println("5. Quy trở lại menu chính");
            System.out.print("Choose your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter your old password: ");
                    String oldPassword = sc.nextLine();
                    System.out.print("Enter your new password: ");
                    String newPassword = sc.nextLine();
                    currentUser.changePassword(currentUser, oldPassword, newPassword);
                    break;
                case 2:
                    System.out.println("Chọn loại phương thức thanh toán mới:");
                    System.out.println("1. Credit Card");
                    System.out.println("2. E-Wallet");
                    System.out.println("3. Bank Transfer");
                    int methodChoice = sc.nextInt();
                    sc.nextLine();

                    PaymentMethod newMethod = null;
                    switch (methodChoice) {
                        case 1:
                            System.out.print("Nhập số dư ban đầu: ");
                            double creditBalance = sc.nextDouble();
                            System.out.print("Nhập hạn mức: ");
                            double creditLimit = sc.nextDouble();
                            newMethod = new CreditCard(creditBalance, creditLimit);
                            break;
                        case 2:
                            System.out.print("Nhập số dư ban đầu: ");
                            double walletBalance = sc.nextDouble();
                            newMethod = new EWallet(walletBalance);
                            break;
                        case 3:
                            System.out.print("Nhập số dư ban đầu: ");
                            double bankBalance = sc.nextDouble();
                            newMethod = new BankTransfer(bankBalance);
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ!");
                            break;
                    }
                    if (newMethod != null) {
                        currentUser.addPaymentMethod(currentUser, newMethod);
                        System.out.println("Phương thức thanh toán đã được thêm thành công!");
                    }
                    break;
                case 3:
                    System.out.println("Danh sách phương thức thanh toán của bạn:");
                    List<PaymentMethod> methods = currentUser.getPaymentMethods();
                    if (methods.isEmpty()) {
                        System.out.println("Bạn chưa có phương thức thanh toán nào.");
                        break;
                    }
                    for (int i = 0; i < methods.size(); i++) {
                        System.out.println((i + 1) + ". " + methods.get(i).getClass().getSimpleName());
                    }

                    System.out.print("Chọn phương thức thanh toán cần xóa: ");
                    int removeIndex = sc.nextInt() - 1;
                    sc.nextLine();

                    if (removeIndex < 0 || removeIndex >= methods.size()) {
                        System.out.println("Lựa chọn không hợp lệ!");
                        break;
                    }

                    PaymentMethod selectedMethod = methods.get(removeIndex);
                    currentUser.removePaymentMethod(currentUser,selectedMethod);
                    break;

                case 4:
                    currentUser.viewSecurityInfo(currentUser);
                    break;
                case 5:return;
            }
        }
    }
}