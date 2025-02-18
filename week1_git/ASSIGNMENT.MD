
# Week 1: Git

1. Git thực sự lưu trữ dữ liệu như thế nào? Các đối tượng (objects) như blob, tree, commit, và tag có quan hệ gì với nhau trong hệ thống lưu trữ phân tán của Git?

- Answer: Git lưu trữ bằng cách đánh dấu snapshot – trạng thái của project. Mỗi commit thì git sẽ snapshot lại những sự thay đổi của project thành một phiên bản
• Mỗi khi mình commit là sẽ tạo ra blob, tree, commit và tag tương ứng với commit đó.
• Khi một file mới được thêm vào hoặc bị thay đổi thì sẽ tạo mới một blob mới + mã SHA-1 định danh cho blob. Blob này sẽ chứa data của file bị thay đổi
• tree sẽ là nơi để lưu trữ cây thư mục + các file của project + tree (tức là cây thư mục con trong thư mục mẹ) và mỗi node trên cây thư mục cũng đều được đánh mã SHA-1 để trỏ tới blob, tree tương ứng
• Commit thì lưu trữ metadata (thông tin người commit bao gồm email, username, time commit, message) và cũng được đánh mã SHA-1 tương ứng với commit. Mỗi commit cũng sẽ reference tới parent commit vì cơ thế lưu cây commit theo dạng graph. Và commit cũng lưu trữ ref tới tree và từ tree lưu trữ tới ref blob
• Tag thì dùng để đánh dấu commit vd như là đánh dấu phiên bản commit này là phiên bản quan trọng

2. Khi ta di chuyển giữa các commit, Git làm gì dưới lớp vỏ bọc của nó? HEAD thực chất là gì và nó tác động thế nào đến trạng thái hiện tại của repository?

- Answer: Khi ta di chuyển giữa các commit thì thực chất là sự thay đổi ref trong file .git/HEAD
• Tùy trường hợp mình checkout sang một commit hay một branch thì git sẽ làm việc khác nhau
• Khi checkout sang một branch thì .git/HEAD sẽ chỉnh sửa nội dung lưu trữ thành ref:refs/heads/”branch trỏ tới”
• Khi checkout sang một commit thì .git/HEAD sẽ gọi là detached HEAD tức là mình đang không ở trên branch cụ thể nào. Nếu muốn lưu sự thay đổi khi mình checkout thì phải tạo ra một branch mới. Khi tạo branch mới thì .git/HEAD cũng sẽ được cập nhật lại thành branch mới được tạo

3. Git có thể tạo hàng nghìn nhánh (branches) gần như tức thì mà không tốn tài nguyên. Điều này hoạt động ra sao về mặt kỹ thuật, và tại sao việc hợp nhất (merge) đôi khi lại dẫn đến xung đột?

- Answer: Thực ra mỗi branch trong git thì đều chỉ là một file mà file đó chứa mã SHA-1 của một commit nào đó vậy nên suy ra hàng nghìn nhánh thì cũng tạo ra hàng nghìn con trỏ chứ không phải là mỗi branch là mỗi phiên bản code của project. Và branch sẽ được lưu trữ trong .git/refs/heads/”list branch”
• Việc merge code đôi khi conflig xảy ra khi mà trên 2 branchs cùng chỉnh sửa một file – thì git sẽ không biết phải lấy sự thay đổi của branch nào nên git sẽ ném ra conflig để ltv tự lựa chọn branch nào là hợp lý

4. Khi ta thêm file vào staging area, thực chất Git làm gì? Điều gì thực sự thay đổi khi ta chạy lệnh git commit?

- Answer: Khi ta thêm file vào staging area tức là ta đã tracking được những file thay đổi và sau câu lệnh git add tức là đã đưa những file tracking này vào vùng nhớ staging area. Những file này là những file được chuẩn bị để cho lần commit tiếp theo
• Git sẽ tạo ra đối tượng blob và lưu trữ những data được thay đổi vào. Xong sẽ thêm blob đó vào .git/index. Đây là nơi lưu trữ tất cả file đã được thêm vào staging area
• Khi ta git commit thì sẽ lấy ra những file trong staging area ra và tạo ra một commit hoàn chỉnh bao gôm commit – tree – blob. Sau đó sẽ cập nhật .git/HEAD trỏ tới commit vừa được tạo
5. Git phân biệt working directory, staging area và repository như thế nào? Tại sao có những thay đổi được theo dõi nhưng chưa được commit?

- Answer: working directory là project mà mình đang làm việc trực tiếp lên nó
• Staging area là nơi trung gian giữa working directory và repository. Nơi này là nơi lưu trữ những thay đổi của các file trong lần commit tiếp theo. 
• Repository chính là .git – là nơi lưu trữ tất cả những gì liên quan đến project. Mỗi lần commit tức là ta đã tạo ra một phiên bản snapshot lưu trữ lại phiên bản mới nhất của project
• Có những thay đổi được theo dõi nhưng chưa được commit thì 
• Một là giai đoạn các file ở working directory có sự thay đổi mà mình vẫn chưa cho vào staging area. Mình có thể git status để check những file untracked này. Lý do là những file này git sẽ không cần phải tracking có sự thay đổi nào hay không. Git sẽ mặc kệ toàn bộ những file này
• Hai là giai đoạn ở staging area nhưng vẫn chưa commit đây là những staged files mà mình đánh dấu cho git theo dõi. Mình có thể commit chúng lên repository
• Có trạng thai modified files tức là có những files đã được commit trước đấy rồi và trong working directory có thay đổi – nếu muốn commit nó lên repo thì ta cần phải cho nó vào staging area trước
6. Git lưu trữ thay đổi của ta ở đâu? Làm thế nào Git có thể khôi phục những thay đổi đã bị "stash" một cách chính xác?

- Answer: Git lưu trữ thay đổi của ta trong .git/objects bao gồm blob, tree, commit mỗi cái đều là có một SHA-1 riêng. Đây là git lưu trữ khi ta commit
• Khi git add thì git cũng tạo ra blob và đưa blob này vào .git/index
• Git có thể khôi phục những thay đổi đã bị git stash trong quá khứ là tại vì git stash thì mình cũng đã tạo ra blob và tree cho các thay đổi đó nhưng sẽ không tạo ra commit mà lưu những thay đổi đó trong .git/refs/stash rồi. Khi mình muốn lấy lại thì có thể git stash pop hoặc git stash apply là lấy lại được những gì thay đổi

7. Khi xảy ra xung đột hợp nhất (merge conflict), tại sao Git không thể tự động giải quyết? Git sử dụng cơ chế nào để xác định sự khác biệt và yêu cầu sự can thiệp của lập trình viên?

- Answer: Git không thể tự động giải quyết bởi vì git không biết phải chọn sự thay đổi của branch nào khi có nhiều branch cùng tham gia chỉnh sửa vào một file.Git sử dụng cơ thế 3-way merge
• Base version tức là node commit chung của 2 branch
• Current version tức là node commit của mình
• Incoming version: commit của cái branch mình muốn merge tới
• Git sẽ lấy điểm chung từ Base version mà cả 2 branch đều có và từ đó so sánh những điểm khác biệt giữa 2 branch.
• Nếu 2 branch đều sửa cùng một file nhưng khác dòng thì git sẽ tự động hợp nhất. Còn nếu là cùng một dòng thì sẽ ném ra conflig cho ltv tự xử lý

8.  Git duy trì lịch sử commit như thế nào? Cấu trúc cây (tree structure) của Git giúp tối ưu hóa việc theo dõi và truy xuất lịch sử ra sao?

- Answer: Git duy trì lịch sử commit thông qua các commit trong repo. Mỗi commit đại diện cho từng phiên bản project. .git/object sẽ lưu trữ commit – tree – blob, .git/refs lưu trữ branch của local repo cùng với global repo

• Git lưu trữ toàn bộ commit theo kiểu graph có hướng. Mỗi commit đều trỏ tới commit cha của nó. Việc lưu trữ lịch sử commit này giúp tối ưu hóa việc theo dõi git vì việc lưu trữ theo dạng cây này thì git không phải duyệt tuần tự từ đầu.

9. Những lệnh này khác nhau như thế nào về bản chất? Tại sao git reset --hard có thể gây mất dữ liệu nhưng git revert thì không?

- Answer:git reset –-hard “commit” thì sẽ lùi HEAD tới commit đó và xóa toàn bộ commit con của commit đó.
• Git revert commit thì sẽ tạo ra một commit’ và hủy bỏ những thay đổi của commit gốc đó. Nhưng sẽ không xóa commit gôc đó ra khỏi lịch sử commit.
• Vd A B C D khi ta git revert C thì -> A B C C’ D: git sẽ lấy những thứ thay đổi của commit C’ thay vì của commit C

10. Khi dùng git commit --amend, Git thực sự làm gì? Nó có chỉnh sửa commit cũ không, hay tạo ra một commit hoàn toàn mới?

- Answer: git commit –amend là tạo ra commit mới thay thế cho commit gần nhất. Và commit cũ đó sẽ không nằm trong branch nữa
