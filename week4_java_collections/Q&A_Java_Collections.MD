
# Week 4: Java Collections

1. Sự khác biệt giữa HashSet, LinkedHashSet và TreeSet Trong trường hợp nào thì nên dùng cái nào?

- Answer: Sự giống nhau đều có cấu trúc Set – đảm bảo data lưu trữ phần tử không trùng lặp
	HashSet là sự kết hợp Set với Hashing. Phần tử vì generate HashValue thông qua Hastable – cái mà xác định vị trí của data nên phần tử sẽ có thứ tự theo HashTable. Nhưng đổi lại tốc độ truy vấn, thêm, xóa thì sẽ là O1 – nhưng lưu ý có thể chậm On nếu xảy ra quá nhiều collision
	LinkedHashSet là sự kết hợp của Set, Hashing, LinkedList. Phần tử sẽ theo thứ tự như khi thêm vào. Tốc độ truy vấn, thêm, xóa thì nhờ có Hashing nên cũng sẽ là O1
	TreeSet là sự kết hợp giữa cân bằng tree và Set nên sẽ sắp xếp theo thứ tự. Tốc độ truy vấn, thêm, xóa thì sẽ là Ologn vì tình huống xấu nhất vẫn phải duyệt một nửa data
	Dùng HashSet khi cần hiệu suất truy vấn, thao tác nhanh nhất trong 3 kiểu mà không cần quan tâm đến thứ tự element hay sắp xếp thứ tự phần tử
	Dùng LinkedHashSet khi cần hiệu suất cao khi truy vấn, thao tác tới data nhưng vẫn muốn giữ nguyên thứ tự khi thêm phần tử vào
	Dùng TreeSet khi muốn dữ liệu có sự sắp xếp nhưng bù lại tốc độ truy vấn, thao tác sẽ là Ologn
2. Làm thế nào để HashSet đảm bảo các phần tử không trùng lặp?

- Answer:Thông qua Hash Function thì phần tử được thêm mới vào sẽ generate ra HashValue. HashValue này quyết định vị trí của phần tử đó. Thì vì là Set nên sẽ có thêm một bước là kiểm tra HashValue này. Đầu tiên nếu HashValue này giống nhau thì chắc chắn xảy ra collision rồi – nhưng vẫn sẽ kiểm tra data của HashValue này bằng equals() nếu true thì sẽ không thêm vì trùng, nếu false thì xảy ra collision

3. Sự khác biệt giữa HashMap, LinkedHashMap và TreeMap? Trong trường hợp nào thì nên dùng cái nào?

- Answer: Điểm chung là cấu trúc Map kiểu key-value, key luôn là duy nhất. Và mọi key đều ánh xạ tới value tương ứng
	Hashmap là sự kết hợp Hashing với Map. Vị trí của từng element sẽ là ngẫu nhiên phụ thuộc vào HashFunction, tốc độ truy vấn và thao tác tới data là O1
	LinkedHashMap là sự kết hợp LinkedList, Hashing, Map thì sắp xếp trí element theo thứ tự thêm vào, tốc độ truy vấn và thao tác data là O1
	TreeMap là sự kết hợp giữa cây cân bằng và Map thì các elements sẽ được sắp xếp theo thứ tự. Tốc dộ truy vấn và thao tác phần tử là Ologn
	Áp dụng HashMap vào bài toán cần tốc độ cao nhất trong 3 kiểu mà không quan tâm tới thứ tự data và data cũng không cần phải sắp xếp
	Áp dụng LinkedHashMap vào bài toán thứ tự thêm vào của phần tử. Và có LinkedLhashmap có removeEldestEntry() hoạt động kiểu xóa phần tử cũ nhất và thêm phần tử mới vào đó khi hết giới hạn bộ nhớ
	Áp dụng Treemap vào bài toán cũng phải có sự sắp xếp tăng dần hay giảm dần, quản lý phần tử nhớ nhất, lớn nhất

4. Từ sau Java 8, HashMap xử lý collisions như thế nào?

- Answer: Trước Java 8 thì khi xảy ra collision thì java tạo ra linkedlist để lưu trữ data. khi LinkedList mà chứa quá nhiều phần tử thì sự quay về On
	TỬ Java8 về sau thì java dùng Red-Black Tree để xử lý sự phần tử có trong LinkedList (8 phần tử). Nếu phần tử trong Red-Black Tree xuống còn 6 phần tử thì sự quay trở lại LinkedList để lưu trữ
	Thì cây red-black tree này sẽ tự cân bằng chính nó sao cho cái cây cân bằng này không nghiêng hẳn về một phía. => Điều này thì thao tác truy vấn, thao tác sẽ là Ologn 

5. Tại sao String và Integer thường được dùng làm key trong HashMap?
- Answer: String và Integer đều là immutable nên khi thay đểi giá trị của key thì sự không ảnh hưởng tới key cũ. 
Integer sự tối ưu nhất để generate ra hashValue
String thì phù hợp với những key theo dạng chuỗi 

6. Cách hoạt động của Red-black Tree khi thêm, xoá phần tử?
- Answer: Cách hoạt động Red-Black Tree thì khi thêm một phần tử mới vào thì sẽ so sánh với từng node của của tree bắt đầu từ gốc. Nếu lớn hơn thì sang phải nếu nhỏ hơn thì sang trái.

7. AVL Tree là gì? Có ưu điểm và nhược điểm gì?
- Answer:AVL Tree là cây có độ cân bằng gắt gao hơn Red-Black Tree.
Ưu điểm vì luôn duy trì cây cân bằng mức cao nhất nên mức độ truy cập, thao tác sẽ là Ologn
Nhược điểm vì phải duy trì cây cân bằng liên tục nên mọi thao tác xóa, thêm sẽ phải cân bằng lại cây – tốn tài nguyên

8. B-Tree là gì? Được sử dụng như thế nào?
- Answer: btree cũng là cây tự cân bằng nhưng một node của btree có thể có nhiều key
