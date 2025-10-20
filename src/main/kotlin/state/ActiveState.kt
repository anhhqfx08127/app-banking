package state

import BankAccount
/**
 * Lớp đại diện cho trạng thái "hoạt động" (ACTIVE) của tài khoản ngân hàng.
 *
 * Đây là một phần trong mẫu thiết kế **State Pattern**.
 * Ở trạng thái này, tài khoản có thể:
 *  - Nạp tiền
 *  - Rút tiền
 *  - Chuyển sang trạng thái "đóng băng" hoặc "đóng"
 */
class ActiveState : AccountState {

    // Loại trạng thái hiện tại
    override val type: AccountStateType = AccountStateType.ACTIVE

    /**
     * Thực hiện nạp tiền vào tài khoản.
     *  - Cộng tiền vào số dư.
     *  - Gửi thông báo đến các observer (VD: NotificationService, Logger, v.v.)
     */
    override fun deposit(account: BankAccount, amount: Double, description: String) {
        account.balance += amount
        println("💰 Nạp $amount vào tài khoản ${account.account.accountNumber}, Nội dung: $description")
        account.notifyObservers() // Gọi các observer cập nhật biến động số dư
    }

    /**
     * Thực hiện rút tiền từ tài khoản.
     *  - Nếu số dư không đủ, in thông báo lỗi.
     *  - Nếu đủ, trừ tiền và thông báo biến động số dư.
     */
    override fun withdraw(account: BankAccount, amount: Double, description: String) {
        if (amount > account.balance) {
            println("⚠️  Tài khoản ${account.account.accountNumber} không đủ tiền để rút $amount")
            return
        }
        account.balance -= amount
        println("🏧 Rút $amount từ tài khoản ${account.account.accountNumber}")
        account.notifyObservers()
    }

    /**
     * Chuyển tài khoản sang trạng thái "đóng băng".
     *  - Gán state mới là FrozenState.
     */
    override fun freeze(account: BankAccount) {
        account.state = FrozenState()
        println("🔒 Tài khoản ${account.account.accountNumber} đã bị đóng băng")
    }

    /**
     * Chuyển tài khoản sang trạng thái "đóng".
     *  - Gán state mới là ClosedState.
     */
    override fun close(account: BankAccount) {
        account.state = ClosedState()
        println("🚫 Tài khoản ${account.account.accountNumber} đã bị đóng")
    }
}