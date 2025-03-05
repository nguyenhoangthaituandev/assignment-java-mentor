
# Week 3: OOP

1. Lập trình hướng đối tượng là gì? So sánh với hướng lập trình thủ tục

- Answer: Lập trình OOP là đem đối tượng từ đời sống thật của mình vào trong code và mình sẽ lập trình xoay quanh đối tượng đó. 
Lập trình POP là lập trình xoay quanh các hành động, các hàm

2. Giải thích 4 nguyên tắc cơ bản OOP: Encapsulation, abstraction, Inheritance, Polymorphism

- Answer: Encapsulation: tính đóng gói thông qua modifier access với 4 mức độ 
• private là mức độ bảo mật cao nhất – chỉ cho phép truy cập ở trong cùng class
• Default – chỉ cho phép truy cập trong cùng package
• Protected – Cho phép truy cập nếu cùng package và class con kế thừa nó
• Public – cho phép toàn bộ chương trình của mình truy cập tới
Abstraction: Là tính chất trừu tượng - ẩn đi phần body của method, chỉ hiển thị ra params, tên method, kiểu trả về

3. Interface và abstract Class khác nhau như thế nào?

- Answer: Abstract class là có những method chung của nhiều class thì ta trừu tượng hóa nó thành abstract method + abstract class để chứa những method đó. Việc của những class con là thực thi những method theo cách của riêng mình
Interface là tập hợp của những method chung của nhiều class và tùy vào class implement lại method theo cách khác nhau
Khác biệt ở chỗ java chỉ cho phép một class extends một abstract class và implements nhiều interface
4. Một class có thể kế thừa nhiều class cùng lúc không? nếu không thì tại sao?

- Answer: Java không cho phép một class kế thừa nhiều class cùng một lúc. Vì nếu trường hợp 2 class cha đều có chung một method thì ở class con gọi method đó thì java không biết phải chọn method nào

5. Làm thế nào để đảm bảo một class tuân theo nguyên tắc Encapsulation
- Answer: Một calss tuân theo nguyên tắc Encapsulaton – java bean
• Access modifier cho fields là private
• Cung cấp getter và setter để truy cập và gán giá trị cho field
• Có constructor không tham số
• Implements Serializable nếu thao tác tới db

6. Tại sao nên sử dụng getter và setter thay vì truy cập trực tiếp biến instance?
- Answer: Mình không cho người dùng truy cập, sửa đổi trực tiếp tới biến  instance vì mình sẽ kiểm soát được cách mà người dùng setter(thêm điều kiện – tính toán logic kiểu gì đó)

7. Một class có thể vừa là abstract class và là interface được không? Tại sao Java không hỗ trợ đa kế thừa với class, nhưng lại hỗ trợ đa kế thừa với interface?
- Answer: Java không cho phép. 
Abstract class vẫn là class, vẫn có biến, method bình thường kèm theo abstract method
Interface là tập hợp abstract method, hằng số
Hỗ trợ đa kế thừa với interface để giải quyết config khi đa kế thừa class xảy ra. Trường hợp nếu 2 interface cha đều có cùng method thì class con implement sẽ overide lại method đóe

8. Khi nào sử dụng abstract class thay vì interface?
- Answer: Sử dụng Abstract class khi chắc chắn class con sẽ luôn là một abstract class vd SinhVien luôn là một ConNguoi. Và có những field, method sử dụng chung cho các lớp con. Các class con quan hệ gần nhau
Sử dụng Interface để xử lý việc đa kế thừa ở trên. Tạo ra interface chứa method(hành động) của những class không quan hệ gần nhau lắm, DI, Constract

9. Sự khác nhau giữa method overloading và method overriding
- Answer: Overloading diễn ra trong cùng một class – chạy trong quá trình compiletime. Khi cùng một method cùng xử lý một công việc gì đó nhưng lại khác tham số đầu vào
Overriding diễn ra ở class cha và class con – chạy trong quá trình runtime. Là một method khi ở class cha thì sẽ thực thi một kiểu, khi ở class con sẽ thực thi một kiểu

10. Có thể override một phương thức static không? về mặt ý nghĩa thì tại sao?
- Answer: Không, Override ở quá trình runtime còn static ở quá trình compile rồi. 
Static là nó thuộc về class chứ không thuộc về đối tượng. Static chia sẻ data giữa các object mà bị một object nào đó override lại thì sẽ trái ngược lại với cách static hoạt động 

11. Garbage Collection hoạt động như thế nào trong Java? Làm sao để buộc hệ thống thực hiện thu gom rác?
- Answer: GC sẽ quan sát những object được khởi tạo và theo dõi nó. Đánh dấu những object không còn được tham chiếu tới và theo định kì sẽ xóa những object đó đi.
Thì heap area chia ra 2 vùng đó là young generation và old generation. Old generation là vùng chứa những object sống lâu trăm tuổi rồi nên sẽ ít ưu tiên chạy GC vùng bộ nhớ này
Young Generation là vùng bộ nhớ chứa những object mới được khởi tạo. Thì GC sẽ ưu tiên quét vùng nhớ này hơn. Khi những object sống sót sau khi quét thì sẽ được ném qua lại 2 vùng nhỏ trong Young Generation là L1, L2. Khi đủ số tuổi quy định sẽ được ném qua OldGeneration.
Khi GC quét rác xong thì sẽ xuất hiện phân mảnh bộ nhớ - GC sẽ nén bộ nhớ lại sao cho hạn chế việc phân mảnh này
