import iterator.TransactionRecord
import strategy.FeeStrategy
import kotlin.math.abs

/**
 * 🧾 Giao diện Transaction — đại diện cho một hành động giao dịch.
 * Mỗi lớp cụ thể (BasicTransaction, Decorator, v.v.) sẽ cài đặt phương thức execute().
 */
interface Transaction {
    fun execute()
}

/**
 * 💸 BasicTransaction — giao dịch cơ bản (nạp hoặc rút tiền).
 *
 * 👉 Áp dụng các mẫu thiết kế:
 * - Strategy: Tính phí giao dịch bằng FeeStrategy (PercentFee, FixedFee, NoFee)
 * - Iterator: Ghi lịch sử vào TransactionHistory của tài khoản (chỉ khi giao dịch thành công)
 */
class BasicTransaction(
    private val account: BankAccount,     // Tài khoản thực hiện giao dịch
    private val amount: Double,           // Số tiền giao dịch (+ là nạp, - là rút)
    private val description: String,      // Mô tả giao dịch
    private val feeStrategy: FeeStrategy  // Chiến lược tính phí
) : Transaction {

    override fun execute() {
        val oldBalance = account.balance           // Ghi nhớ số dư trước giao dịch
        val fee = feeStrategy.fee(amount)          // 💰 Tính phí giao dịch

        // Nếu amount >= 0 → nạp tiền, ngược lại → rút tiền
        if (amount >= 0) {
            account.deposit(amount, fee, description)
        } else {
            account.withdraw(amount, fee, description)
        }

        // 🧾 Chỉ ghi lịch sử nếu số dư thay đổi (giao dịch thực sự xảy ra)
        if (account.balance != oldBalance) {
            account.history.add(
                TransactionRecord(
                    description = description,
                    amount = amount,
                    fee = abs(fee),
                    balanceAfter = account.balance
                )
            )
        } else {
            println("⚠️ Giao dịch '${description}' không được thực hiện do trạng thái tài khoản (${account.state.type}).")
        }
    }
}