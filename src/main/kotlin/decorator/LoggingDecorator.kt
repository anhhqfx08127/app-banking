package decorator

import Transaction

/**
 * Lớp LoggingDecorator — triển khai Decorator Pattern
 *
 * 👉 Mục đích:
 *   - Thêm chức năng "ghi log giao dịch" mà KHÔNG cần sửa code của lớp BasicTransaction.
 *   - Bọc (wrap) một đối tượng Transaction khác để mở rộng hành vi.
 *
 * 🔹 Đây chính là Decorator Pattern:
 *   + Có cùng interface với đối tượng gốc (Transaction)
 *   + Giữ tham chiếu đến đối tượng Transaction thật (inner)
 *   + Thêm logic trước hoặc sau khi gọi inner.execute()
 */
class LoggingDecorator(inner: Transaction) : TransactionDecorator(inner) {

    /**
     * Ghi log trước và sau khi thực thi giao dịch thật.
     */
    override fun execute() {
        println("🧾 [Log] Bắt đầu giao dịch...")   // Thêm hành vi trước khi thực hiện
        inner.execute()                            // Gọi thực thi giao dịch gốc
        println("🧾 [Log] Giao dịch hoàn thành.")  // Thêm hành vi sau khi thực hiện
    }
}