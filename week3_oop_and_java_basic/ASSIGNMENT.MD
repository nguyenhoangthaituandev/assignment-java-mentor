# WEEK3: BÀI TẬP JAVA OOP: HỆ THỐNG THANH TOÁN ĐA PHƯƠNG THỨC

## 1. Bối cảnh
Công ty **XYZ** đang phát triển hệ thống thanh toán trực tuyến, hỗ trợ khách hàng cá nhân & doanh nghiệp thực hiện giao dịch thông qua các phương thức:

1. **Thẻ tín dụng (Credit Card)**
2. **Ví điện tử (E-Wallet)**
3. **Chuyển khoản ngân hàng (Bank Transfer)**

## 2. Yêu cầu nghiệp vụ

### 2.1. Xác minh danh tính & bảo mật giao dịch
- Người dùng phải **đăng nhập** trước khi sử dụng hệ thống.
- Nếu phát hiện giao dịch bất thường (**trên 5000$**, **giao dịch nhiều lần trong 1 phút**, hoặc **chuyển tiền đến tài khoản đáng ngờ**), hệ thống sẽ yêu cầu xác minh **OTP**.
- Nếu nhập sai OTP **3 lần liên tiếp**, tài khoản bị khóa.
- Hệ thống phải theo dõi **trạng thái người dùng (User Status)**:
    - **Active**: Có thể giao dịch bình thường.
    - **Suspended**: Bị khóa tạm thời do giao dịch đáng ngờ.
    - **Banned**: Bị cấm vĩnh viễn do vi phạm chính sách.

### 2.2. Quản lý phương thức thanh toán & hạn mức
- **Thẻ tín dụng** có hạn mức riêng, khi vượt hạn mức thì phải thanh toán số dư trước khi tiếp tục sử dụng.
- **Ví điện tử** chỉ có thể giao dịch **tối đa 5000$/ngày**.
- **Chuyển khoản ngân hàng** áp dụng **phí giao dịch 1%** nếu số tiền lớn hơn **2000$**.
- Nếu tài khoản không đủ số dư, **hệ thống sẽ tự động chia nhỏ thanh toán trên nhiều phương thức**.

### 2.3. Giao dịch quốc tế & tỷ giá
- Hệ thống hỗ trợ giao dịch quốc tế, chuyển đổi ngoại tệ dựa trên tỷ giá mới nhất.
- Nếu thanh toán bằng **Credit Card**, có phí chuyển đổi ngoại tệ **2%**.
- Nếu thanh toán bằng **Bank Transfer**, có thể bị giữ lại **3 ngày làm việc** trước khi tiền được giải ngân.

### 2.4. Xử lý rủi ro giao dịch
- Nếu khách hàng thực hiện **5 giao dịch lớn hơn 5000$ trong 1 giờ**, tài khoản bị đánh dấu **"rủi ro cao"** và cần xác minh qua **call center**.
- Nếu một người thực hiện giao dịch đến **3 tài khoản ngân hàng lạ trong 1 ngày**, hệ thống sẽ tự động cảnh báo đến khách hàng.

### 2.5. Hoàn tiền (Refund)
- Người dùng có thể yêu cầu **hoàn tiền giao dịch** trong vòng **7 ngày** nếu có khiếu nại hợp lệ.
- Khi hoàn tiền, hệ thống phải trả lại số tiền vào **phương thức thanh toán ban đầu**.
- Nếu hoàn tiền qua **Credit Card**, số tiền sẽ mất **3 ngày làm việc** để hoàn tất.

### 2.6. Báo cáo tài chính & tổng kết giao dịch
- Người dùng có thể xem báo cáo **tổng số tiền đã thanh toán** theo từng phương thức.
- Hệ thống sẽ phân tích **giao dịch đáng ngờ**, hiển thị danh sách **các giao dịch có dấu hiệu gian lận**.
- Doanh nghiệp có tài khoản đặc biệt, có thể xuất **báo cáo tài chính theo tháng** để phục vụ kế toán.

## 3. Cấu trúc thư mục
```
payment-system/
│── src/
│   ├── constance
│   │   ├── UserStatus.java
│   ├── model/          
│   │   ├── User.java
│   │   ├── PaymentMethod.java
│   │   ├── CreditCard.java
│   │   ├── EWallet.java
│   │   ├── BankTransfer.java
│   │   ├── Transaction.java
│   ├── service/          
│   │   ├── UserService.java
│   │   ├── PaymentService.java
│   │   ├── TransactionService.java
│   │   ├── FraudDetectionService.java
│   │   ├── OTPService.java
│   │   ├── RefundService.java
│   │   │   ├──impl/      
│   │   │   ├── UserServiceImpl.java
│   │   │   ├── PaymentServiceImpl.java
│   │   │   ├── TransactionServiceImpl.java
│   │   │   ├── FraudDetectionServiceImpl.java
│   │   │   ├── OTPServiceImpl.java
│   │   │   ├── RefundServiceImpl.java
│   ├── main/           
│   │   ├── PaymentApplication.java
│   
│── README.md
```
## 4. Flow Menu Hệ Thống Thanh Toán
```
1. Đăng nhập
   ├── Nhập username & password
   ├── Nếu nhập sai quá 3 lần → Khóa tài khoản
   └── Nếu thành công → Hiển thị menu chính

2. Menu chính
   ├── 1. Thực hiện thanh toán
   │     ├── Nhập số tiền cần thanh toán
   │     ├── Chọn phương thức thanh toán (hệ thống có thể tự động chọn)
   │     ├── Nếu số tiền > 5000$ → Yêu cầu xác minh OTP 
   │     │     ├── Gửi OTP đến người dùng (hiện thị OTP ra lúc đó luôn và yêu cầu người dùng nhập)
   │     │     ├── Nếu nhập OTP sai quá 3 lần → Hủy giao dịch
   │     │     └── Nếu đúng → Tiếp tục thanh toán
   │     ├── Kiểm tra số dư của phương thức thanh toán
   │     │     ├── Nếu đủ tiền → Xử lý giao dịch
   │     │     ├── Nếu không đủ → Gợi ý chuyển sang phương thức khác hoặc chia nhỏ thanh toán
   │     │     └── Nếu không thể thanh toán → Giao dịch thất bại
   │     ├── Nếu thành công → Lưu vào lịch sử giao dịch
   │     └── Nếu giao dịch có dấu hiệu gian lận → Chặn giao dịch, yêu cầu xác minh qua call center

   ├── 2. Yêu cầu hoàn tiền
   │     ├── Hiển thị danh sách giao dịch có thể hoàn tiền (trong vòng 7 ngày)
   │     ├── Chọn giao dịch cần hoàn tiền
   │     ├── Nếu giao dịch hợp lệ → Xử lý hoàn tiền về phương thức ban đầu
   │     ├── Nếu giao dịch không hợp lệ → Từ chối hoàn tiền
   │     └── Lưu thông tin hoàn tiền vào lịch sử giao dịch

   ├── 3. Báo cáo tài chính
   │     ├── Hiển thị tổng số tiền đã thanh toán theo từng phương thức
   │     ├── Hiển thị số lần thanh toán thành công/thất bại
   │     ├── Hiển thị danh sách giao dịch nghi ngờ gian lận
   │     ├── Nếu là tài khoản doanh nghiệp → Xuất báo cáo theo tháng
   │     └── Quay lại menu chính

   ├── 4. Cài đặt tài khoản
   │     ├── Thay đổi mật khẩu
   │     ├── Thêm phương thức thanh toán mới
   │     ├── Xóa phương thức thanh toán cũ (nếu không có giao dịch đang chờ xử lý)
   │     ├── Xem thông tin bảo mật tài khoản
   │     └── Quay lại menu chính

   ├── 5. Đăng xuất
   │     ├── Xác nhận đăng xuất
   │     └── Quay lại màn hình đăng nhập
```

## tự add 1 list danh user, còn không thì có thể thêm chức năng đăng ký user tuỳ các bạn