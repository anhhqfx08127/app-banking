package decorator

import Transaction

/**
 * Lớp trừu tượng TransactionDecorator
 *
 * 👉 Đây là lớp cơ sở cho tất cả các "Decorator" của Transaction.
 *
 * 💡 Ý tưởng chính của Decorator Pattern:
 * - Cho phép mở rộng hành vi của một đối tượng mà không cần sửa đổi lớp gốc.
 * - Cả lớp gốc và lớp decorator đều tuân theo cùng một interface (ở đây là Transaction).
 *
 * 🧩 Cấu trúc cơ bản:
 *   Transaction (interface)
 *        ↑
 *   TransactionDecorator (abstract class)
 *        ↑
 *   ├── LoggingDecorator
 *   ├── NotificationDecorator
 *   └── TimeDecorator
 */
abstract class TransactionDecorator(
    protected var inner: Transaction   // Giữ tham chiếu đến Transaction gốc (đối tượng được "bọc")
) : Transaction {

    /**
     * Mặc định chỉ ủy quyền thực thi cho đối tượng bên trong.
     *
     * 🔹 Các lớp con có thể override lại hàm này
     *    để thêm hành vi trước hoặc sau khi gọi inner.execute().
     */
    override fun execute() {
        inner.execute()
    }
}
