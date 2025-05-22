# Bài tập tuần 2
## Bài tập về nhà 
Yêu cầu Tạo một ứng dụng Android đơn giản để: Nhập tên và tuổi. Khi nhấn nút kiểm tra thì chương trình sẽ kiểm tra dữ liệu và hiển thị kết quả phân loại độ tuổi (em bé, trẻ em, người lớn, người già).
--- 
Giải thích
trong class này em đang khai báo 4 biến để liên kết các thành phần trong giao diện như: ô nhập tên (edtName), ô nhập tuổi (edtAge), nút kiểm tra (btnCheck), và nơi hiển thị kết quả (txtResult) và dùng lateinit để khai báo trước và gán giá trị sau bằng findViewById, còn private để các biến này chỉ được sử dụng trong lớp MainActivity thôi

![class](https://github.com/user-attachments/assets/b1e3dcbc-7041-4dec-8e54-ac27895299fe)

---
Func này xử lý chức năng khi người dùng nhấn nút, đầu tiên, nó lấy tên và tuổi từ giao diện, nếu như thiếu thông tin hoặc tuổi không hợp lệ thì ứng dụng sẽ báo lỗi bằng Toast.
Sau đó, nếu hợp lệ, em dùng when để kiểm tra tuổi và phân loại đối tượng và kết quả sẽ hiển thị ra màn hình bằng txtResult.text.

![funcBTVN](https://github.com/user-attachments/assets/2157165c-aa9d-4b48-a367-66bc56f98cc5)

---
Kết quả màn hình hiển thị trên LDplayer

![TH2_W2](https://github.com/user-attachments/assets/bb12f568-9f77-4fb0-97c7-50a78e13df55)

---
