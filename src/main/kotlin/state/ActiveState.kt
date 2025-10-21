package state

import BankAccount
import kotlin.math.abs

/**
 * Lớp đại diện cho **trạng thái "Hoạt động" (Active)** của tài khoản ngân hàng.
 *
 * 💡 Đây là một phần trong mẫu thiết kế **State Pattern**.
 *
 * Ở trạng thái này, tài khoản **được phép thực hiện hầu hết các giao dịch**:
 *  - Nạp tiền (deposit)
 *  - Rút tiền (withdraw)
 *  - Chuyển sang trạng thái "Đóng băng" (Frozen)
 *  - Đóng tài khoản (Closed)
 *
 * 👉 Mọi giao dịch đều có thể kèm **phí giao dịch (fee)**,
 * và phí này sẽ **bị trừ trực tiếp vào số dư tài khoản**.
 */
class ActiveState : AccountState {

    // Loại trạng thái hiện tại
    override val type: AccountStateType = AccountStateType.ACTIVE

    /**
     * Nạp tiền vào tài khoản.
     *
     * ✅ Hành vi:
     *  - Cộng `amount` vào số dư.
     *  - Trừ `fee` (phí giao dịch) khỏi số dư.
     *  - Gửi thông báo biến động số dư đến các observer.
     *
     * ⚠️ Nếu phí lớn hơn **số dư hiện tại**, giao dịch vẫn hợp lệ (vì tiền nạp đến từ ngoài),
     * miễn là sau khi cộng và trừ phí thì số dư không âm.
     *
     * @param account   Tài khoản đang hoạt động.
     * @param amount    Số tiền nạp.
     * @param fee       Phí giao dịch (trừ vào tài khoản).
     * @param description  Mô tả giao dịch (nội dung chuyển khoản, ghi chú,...)
     */
    override fun deposit(account: BankAccount, amount: Double, fee: Double, description: String) {
        val netAmount = amount - fee
        val newBalance = account.balance + netAmount

        if (newBalance < 0) {
            println("⚠️ Phí giao dịch ($fee) quá cao khiến số dư âm sau nạp. Giao dịch bị hủy.")
            return
        }

        account.balance = newBalance

        println("💰 Nạp $amount (phí: $fee) → +${netAmount} vào tài khoản ${account.account.accountNumber}.")
        println("📝 Nội dung: $description")

        account.notifyObservers()
    }

    /**
     * Rút tiền khỏi tài khoản.
     *
     * ✅ Hành vi:
     *  - Cho phép truyền `amount` âm (đại diện cho dòng tiền ra).
     *  - Kiểm tra số dư.
     *  - Nếu đủ, trừ `abs(amount) + abs(fee)` khỏi số dư.
     *  - Gửi thông báo biến động số dư.
     *
     * ⚠️ Nếu số dư không đủ, in cảnh báo và không trừ tiền.
     */
    override fun withdraw(account: BankAccount, amount: Double, fee: Double, description: String) {
        // Tổng tiền thực sự cần trừ khỏi tài khoản
        val total = abs(amount) + abs(fee)

        if (total > account.balance) {
            println("⚠️  Tài khoản ${account.account.accountNumber} không đủ tiền để rút ${abs(amount)} (phí: $fee).")
            return
        }

        account.balance -= total

        println("🏧 Rút ${abs(amount)} (phí: $fee) → -$total từ tài khoản ${account.account.accountNumber}.")
        println("📝 Nội dung: $description")

        account.notifyObservers()
    }

    /**
     * Chuyển tài khoản sang trạng thái **Đóng băng (Frozen)**.
     *
     * - Không thể nạp hoặc rút tiền trong trạng thái mới này.
     * - Vẫn có thể đóng tài khoản.
     */
    override fun freeze(account: BankAccount) {
        account.state = FrozenState()
        println("🔒 Tài khoản ${account.account.accountNumber} đã bị đóng băng.")
    }

    /**
     * Chuyển tài khoản sang trạng thái **Đóng (Closed)**.
     *
     * - Không thể thực hiện bất kỳ giao dịch nào sau khi đóng.
     */
    override fun close(account: BankAccount) {
        account.state = ClosedState()
        println("🚫 Tài khoản ${account.account.accountNumber} đã bị đóng.")
    }
}