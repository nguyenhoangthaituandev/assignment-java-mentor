# Week 2: How to Java Work

1. JDK, JVM, và JRE khác nhau như thế nào? Vai trò của từng thành phần trong hệ sinh thái Java?

    Answer: JDK là tập hợp công cụ như javac, debug dùng để phát triển và run ứng dụng java
• JVM là một application của java dùng để interpreter class bytecode thành machine code cho OS hiểu. Và mỗi OS thì đều có JVM phù hợp tương ứng
• JRE bao gồm JRE + libraries native như lang, io, nio, sql… Các libraries này khác nhau trên từng OS nên mới gọi là libraries native.
• Để phục vụ nhu cầu Run Anywhere thì phải cài đặt phiên bản JRE phù hợp với OS người sử dụng đang sử dụng 

2. JDK có những công cụ nào quan trọng cho lập trình viên Java?

    Answer:Công cụ quan trọng nhất là javac dùng để compiler class.java thành class.class
• JDK chứa cả JVM + JRE nên cũng chạy java “class bytecode name” để interpreter class.class này thành machinecode
• Công cụ đóng gói jar cf “name packaged”.jar “nameBytecode”.class
• Và còn nhiều tool trong JDK…

3. JVM hoạt động như thế nào khi bạn chạy một chương trình Java?

    Answer: Đầu tiên đầu vào của JVM là những file bytecode vào classLoader và tùy theo JVM request mà file bytecode nào sẽ được chạy trước file bytecode nào sẽ được chạy sau. Khi chạy vào classLoader thì sẽ được check xem file bytecode đấy sẽ cần gọi tới những libraries nào – và những libraries đấy sẽ gọi tới classLoader interface tương ứng nào. Nếu là các libraries gốc thì sẽ chạy BootstrapClassLoader (.lang, .util, .io, .nio), nếu là những libraries mở rộng thì sẽ chạy vào PlatformClassLoader còn nếu là do người dùng tự định nghĩa thì là chạy vào ApplicationClassLoader – và khi load xong file bytecode nào và tải libraries phù hợp thì sẽ tới bước linking sẽ verify là giải mã bytecode, kiểm tra định dạng nếu ok thì mới cho chạy tiếp nếu không thì ném ra lỗi. Sau đó là prepare là cấp phát bộ nhớ JVM memory như là biến static, gán giá trị mặc định cho static(0, null, false). Resolution là liên kết tham chiếu của class đến class khác. Sau đó tới bước Init là gán giá trị thực của người dùng vào từng cái biến đã khởi tạo trước đó trong giai đoạn prepare

• file bytecode sẽ được load vào từng vùng nhớ phù hợp trong JVM memory. Xong rồi sẽ tới bước Execution Engine là biên dịch bytecode thành machine code
4. Có những thành phần quan trọng nào bên trong JVM?

	Answer: Có 3 thành phần quan trọng đó là
• classLoader SubSystem
• JVM Memory
• Execution Engine


5. JVM quản lý bộ nhớ như thế nào? Các vùng nhớ quan trọng trong JVM là gì?

    Answer: JVM memory quản lý bộ nhớ bằng cách chia bộ nhớ thành nhiều vùng nhớ khác nhau – mỗi vùng nhớ lưu loại dữ liệu khác nhau
• Method area (Native memory)
• Heap area
• Stack area
• PC Register
• Native method stack

6. Class Metadata (Method Area) trong JVM có vai trò gì?

    Answer:Method Area có nhiệm vụ lưu trữ class metadata vào bộ nhớ của hệ điều hành như là tên class, tên packaged, danh sách method, properties và code trong method, các biến global như static, constant pool – những biến này sẽ chia sẻ giữa các thread

7. PermGen và Metaspace khác nhau như thế nào? Tại sao Java chuyển từ PermGen sang Metaspace từ Java 8?

    Answer:PermGen trước java 8 thì nằm trong heap area sẽ chứa những biến của method area – ngoài ra thì kích thước của nó là cố định nên có thể gây ra lỗi khi bị tràn
• Thì từ Java 8 thì java loại bỏ permgen thành metaspace(Method area) nằm trong native memory – bộ nhớ của OS luôn. Và vẫn lưu class metadata, constant pool, static variable, static block, machine code do JIT compiler caching lại nhưng sẽ không còn giới hạn bộ nhớ của heap nữa mà mở rộng linh hoạt theo bộ nhớ của OS

8. Heap và Stack trong JVM khác nhau như thế nào?

    Answer: Heap là nơi lưu trữ instance của object + instance variable của object luôn, String pool
• Stack thì lưu trữ biến local variable – tức là những biến được khai báo trong method như là params, return address, stack frame
• Stack thì sẽ tự giải phóng bộ nhớ mỗi khi nó chạy xong
• Heap thì sẽ được GC quản lý dọn dẹp những object không còn được biến bên stack tham chiếu tới

10. Garbage Collection trong JVM là gì? Tại sao nó quan trọng?

    Answer:GC là application dùng để dọn dẹp lại lại những object mà không còn được tham chiếu tới.
• GC quan trọng là bởi vì nếu không dọn dẹp những object dư thừa thì sẽ gây tràn bộ nhớ - mà tràn bộ nhớ thì project không hoạt động được

11. Những thuật toán chính của Garbage Collection mà JVM sử dụng là gì?

    Answer:Thuật toán cốt lõi là  Markphase and Sweep tức là đánh dấu những object không có tham chiếu tới và giải phóng nó ra khỏi bộ nhớ
• Chia Heap thành vùng Young, Old để GC quét vùng Young – tức là vùng object mới được tạo mới và S0, S1 – những vùng object sống sót qua nhiều lần quét và nếu object sống đạt tới mức JVM quy định vd như là 25 thì sẽ được cho vào vùng Old

12. JVM có những loại Garbage Collector nào? Khác nhau ở điểm nào?

    Answer: Serial GC là dọn dẹp bộ nhớ cơ bản nhất sau đó là parallel – chạy nhiều luồng quét
• Và java 0 tới hiện nay thì sử dụng G1 Garbage First là chủ yếu – tức là chia Heap thành từng vùng bộ nhớ và theo dõi vùng nào có bộ nhớ rác nhiều thì ưu tiên quét nơi đó thường xuyên hơn

13. Sự khác biệt giữa Minor GC, Major GC và Full GC là gì?

    Answer: Minor GC là GC quét ở Young Generation – là nơi object mới được tạo và là nơi có object rác nhiều hơn
• Major GC là GC quét ở Old Generation – là nơi mà GC quét rác ít thường xuyên hơn vì ở vùng nhớ này cơ bản là object sẽ được lưu trữ lâu dài
• Full GC tức là bộ nhớ heap bị tràn sẽ quét toàn bộ vùng nhớ heap

14. Bộ nhớ Stack có bị thu hồi bởi Garbage Collector không? Nếu không, nó được quản lý như thế nào?

    Answer: Stack không bị thu hồi bổi GC, GC chỉ hoạt động ở Heap area nơi lưu trữ object. 
• Stack thì được quản lý trực tiếp với JVM nhờ cơ thế hoạt động First In Last Out của nó. Tức là khi hàm main hoạt động thì sẽ load từ trên xuống dưới từ params tới reference method vào Stack memory theo Stack Frame và theo nguyên tắc First In Last Out. Khi load xong vào Stack memory thì sẽ thực thi từ trên xuống và khi thực thi được Stack Frame nào thì sẽ giải phóng bộ nhớ của Frame đó

15. Làm thế nào để tránh Memory Leak trong Java?

    Answer: Memory Leak là do GC không hoạt động hiệu quả hoặc là mình code sao cho giải phóng object không còn được sử dụng tới
• Như là biến static do nằm trong method area nên GC không thể thu hồi cho nên phải lưu ý khi sử dụng static
• Cần thận với Collection giữ tham chiếu không cần thiết
• Đóng sout, streams, connection mỗi khi sử dụng xong

16. JIT Compiler trong JVM là gì? Nó cải thiện hiệu năng như thế nào?

    Answer: JIT compiler là cái để biên dịch bytecode thành machine code và lưu trữ trong method area.
• Interpreter là biên dịch từng dòng bytecode sang machine code và nếu gặp cùng một đoạn bytecode 1000 dòng như một thì vẫn cứ lặp lại việc biên dịch lặp đi lặp lại như vậy -> Điều này thì không tối ưu về performance
• JIT compiler sinh ra để khắc phục điều này. JIT Compiler sẽ đánh dấu đoạn bytecode là hotspot và biên dịch đoạn mã đó thành machine code và caching nó lại trong method area. Khi Interpreter chạy tới đoạn bytecode có đánh dấu hotspot đó thì sẽ hiểu là sẽ lấy machine code đã caching lại chạy luôn -> tránh interpreter lại

17. Khi nào JIT Compiler dịch mã bytecode sang mã máy thực thi?

    Answer: Compiler là biên dịch toàn bộ file java thành file bytecode một lần. Nhưng JIT Compiler thì không như thế - JIT compiler chỉ biên dịch bytecode khi JVM nhận ra có đoạn mã bytecode mà thấy lặp đi lặp lại nhiều lần thì đánh dấu là hotspot và gọi JIT compiler tới để biên dịch nó

18. Khi ứng dụng Java chạy chậm, bạn sẽ kiểm tra gì đầu tiên?

    Answer: Kiểm tra Heap có bị tràn không, GC có hoạt động tốt không, tối ưu code chưa – có để dư thừa object không còn tham chiếu tới không, đóng các cổng kết nối tới IO chưa

20. Làm thế nào để debug lỗi liên quan đến bộ nhớ Heap và Stack?

    Answer: Nếu là lỗi StackOverFlow thì kiểm tra xem có lỗi vòng lặp vô hạn không hay là gọi đệ quy không có điều kiện dừng
• Lỗi Heap thì bật chế độ debug lên và kiểm tra GC làm việc hiệu quả không
