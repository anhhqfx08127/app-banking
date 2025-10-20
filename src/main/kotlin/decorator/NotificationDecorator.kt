package decorator

import BankAccount
import Transaction

/**
 * NotificationDecorator
 *
 * 🎯 Mục đích:
 *  - Là một "Decorator" của Transaction.
 *  - Thêm hành vi gửi thông báo (notification) sau khi giao dịch hoàn tất.
 *
 * 💡 Đây là ví dụ rõ ràng cho mẫu thiết kế Decorator Pattern:
 *  - Không thay đổi mã nguồn của Transaction gốc (BasicTransaction).
 */
class NotificationDecorator(
    private val account: BankAccount,   // Tài khoản cần gửi thông báo
    inner: Transaction                   // Giao dịch gốc (hoặc decorator khác)
) : TransactionDecorator(inner) {

    /**
     * Thực thi giao dịch với hành vi mở rộng:
     *  1️⃣ Gọi inner.execute() để thực hiện logic gốc.
     *  2️⃣ Sau đó gửi thông báo biến động số dư.
     */
    override fun execute() {
        inner.execute()  // Gọi đến giao dịch thực tế
        println("📩 Đã gửi thông báo tới ${account.account.accountNumber}")
    }
}
