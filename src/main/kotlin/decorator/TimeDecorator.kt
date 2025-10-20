package decorator

import Transaction
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * TimeDecorator
 *
 * 🎯 Mục đích:
 *  - Thêm thông tin thời gian thực hiện giao dịch.
 *  - Thời gian được hiển thị theo định dạng: dd/MM/yyyy HH:mm:ss
 *
 * 💡 Là một lớp Decorator cho Transaction:
 *    - Gọi inner.execute() để thực hiện giao dịch gốc.
 *    - Sau đó in ra thời điểm giao dịch được thực hiện.
 */
class TimeDecorator(inner: Transaction) : TransactionDecorator(inner) {

    override fun execute() {
        inner.execute()  // Thực hiện giao dịch gốc

        // Lấy thời gian hiện tại
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        val timestamp = LocalDateTime.now().format(formatter)

        println("⏰ [Thời gian giao dịch] $timestamp")
    }
}
