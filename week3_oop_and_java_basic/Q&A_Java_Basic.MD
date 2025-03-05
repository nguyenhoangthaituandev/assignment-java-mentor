
# Week 3: Java basic

1. Sự khác biệt giữa dữ liệu primitive và non-primitive trong Java là gì?

- Answer: primitive type là kiểu dữ liệu nguyên thủy cách và nó lưu trữ data vào value trong RAM luôn. Còn non-primitive type thì lưu cái ô nhớ để biến đó reference tới

2. tại sao String là non-primitive type trong khi có vẻ nó chỉ chứa kí tự?

- Answer: Tại vì Java thiết kế nó để lưu trữ value kèm cùng các method hay sử dụng như length, charAt,concat, toLowercase, toUpppercase…
Còn StringPool trong heap area để tiết kiệm bộ nhớ

3. Sự khác biệt giữa int và Integer là gì? khi nào nào dùng cái nào?

- Answer: int là primitive type và Integer là wrapper class cũng là non-primitive type. 
Sử dụng int khi thao tác bình thường
Sử dụng Integer khi làm việc với Collection – Collection chỉ làm việc với non-primitive type. Sử dụng với các properties của object – vì Wrapper class có nhận giá trị null. Ngoài ra thì có các method để tiện sử dụng

4. Auto-boxing và unboxing là gì? Khi nào chúng xảy ra?

- Answer: Autoboxing và AutoUnboxing là khi ép kiểu qua lại giữa 2 primitive type và wrapper class. 
Chúng xảy ra khi ta truyền int vào Collection<Integer>…, Integer -> int

5. Sự khác biệt giữa mảng tĩnh (Array) và danh sách động (ArrayList) trong Java?- Answer:
- Answer:  Array có độ dài cố định còn ArrayList sẽ tự động thêm độ dài mảng khi cần thiết
Array chỉ lưu primitive type ocnf ArrayList chỉ lưu non-primitive type
ArrayListt cung cấp những method tiện sử dụng hơn khi thêm, xóa, sửa

6. Sự khác biệt giữa String, StringBuffer, và StringBuilder?
- Answer: String là immutable còn StringBuffer và StringBuilder là mutable
StringBuffer khác StringBuilder là nó có threadsafe – an toàn hơn khi sử dụng trong môi trường đa luồng
StringBuffer được sử dụng biến global vì chia sẻ data giữa các luồng
StringBuilder thì ưu tiên sử dụng trong local variable vì không khóa thread lại – nên tốc độ sẽ nhanh hơn

7. Giải thích về String Pool trong Java? Tại sao Java sử dụng String Pool?
- Answer: Java sử dụng StringPool để caching lại những data đã khởi tạo rồi, nếu biến sau sử dụng data đấy thì sẽ sử dụng lại data đấy chứ không cần khởi tạo thêm. => tiết kiệm bộ nhớ nhưng lại chậm về thời gian

8. String là immutable, điều này có nghĩa gì? Tại sao String được thiết kế theo cách này?
- Answer: String là immutable nghĩa là khi chỉnh sửa tới một biến String thì sẽ tạo mới thêm chứ không phải là chỉnh sửa trực tiếp vào biến String đó.
String thiết kế như vậy bởi vì có thể có nhiều thằng String khác đang sử dụng cái biến đấy. Nếu 1 thằng thay đổi trực tiếp vào biến đấy thì những thằng còn lại cũng bị thay đổi theo -> không tốt

9. intern() method trong String làm gì?
- Answer: intern() là kiểm tra xem trong stringpool có biến đấy chưa. Nếu có thì lấy biến trong stringpool còn không thì lấy trong heap area

10. Chuyện gì xảy ra nếu thay đổi một giá trị của String được tạo bằng String literal?
- Answer: sẽ tạo ra một biến mới trong stringpool

11. Toán tử == và .equals() khác nhau thế nào khi so sánh Object?
- Answer: == sẽ so sánh reference trỏ tới ô nhớ mà object được lưu trữ không
Equals thì phải được class implement lại để so sánh value của object. Nếu không thì equals cũng chỉ so sánh như ==

12. Tại sao 
double a = 0.1
double b = 0.2
boolean check = (a + b) == 0.3
tại sao check lại bằng false?
Nếu double có nhược điểm như vậy tại sao nó vẫn tồn tại
- Answer: tại vì số thập phân có những số là vô hạn như 1/3 mà khi lưu xuống RAM thì bắt buộc phải là hữu hạn thì java đã làm tròn nó thành 0.33333333333334 đại loại vậy. Vì thế a+b == 3 không thì sẽ sai

Double biểu diễn số thực với độ lớn dữ liệu lớn còn sai số thì nếu không quá chú trọng tới thì vẫn chấp nhận được. Nếu cần chính xác sử dụng BigDecimal
