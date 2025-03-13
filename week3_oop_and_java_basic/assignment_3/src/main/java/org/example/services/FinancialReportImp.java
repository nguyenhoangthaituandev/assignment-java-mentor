package org.example.services;

import org.example.models.Transaction;
import org.example.models.User;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FinancialReportImp implements IFinancialReportService{
    @Override
    public void generateReport(User user,TransactionService transactionService) {
        List<Transaction> transactions=transactionService.getTransactionsForUser(user);

        // total amount per payment method
        Map<String,Double> totalAmountByMethod=transactions.stream()
                .filter(t->t.isSuccess())
                .collect(Collectors.groupingBy(t->t.getPaymentMethod().getClass().getSimpleName(),
                        Collectors.summingDouble(t->t.getAmount())));

        System.out.println("Tổng số tiên thanh toán cho từng phương thức");
        totalAmountByMethod.forEach((method,total)-> System.out.println(method + ": "+total));

        // total payment success/fail
        long successCount=transactions.stream().filter(Transaction::isSuccess).count();
        long failCount=transactions.size()-successCount;
        System.out.println("Số lần thanh toán thành công: "+successCount);
        System.out.println("Số lần thanh toán thất bại: "+failCount);

        // List transaction is fraud
        List<Transaction> fraudTransactions=transactions.stream()
                .filter(t->t.isFraudDetection())
                .collect(Collectors.toList());
        if(fraudTransactions.isEmpty()){
            System.out.println("Danh sách giao dịch nghi ngờ gian lận: ");
            fraudTransactions.forEach(System.out::println);
        }else{
            System.out.println("Không có giao dịch nghi ngờ gian lận");
        }

        // Nếu là tài khoản doanh nghiệp → Xuất báo cáo theo tháng
        if (user.isBusinessAccount()) {
            System.out.println("--- Báo cáo theo tháng ---");
            Map<YearMonth, Double> monthlyReport = transactions.stream()
                    .filter(Transaction::isSuccess)
                    .collect(Collectors.groupingBy(t -> YearMonth.from(t.getTime()),
                            Collectors.summingDouble(Transaction::getAmount)));

            monthlyReport.forEach((month, total) ->
                    System.out.println(month + ": " + total + " USD"));
        }

    }
}
