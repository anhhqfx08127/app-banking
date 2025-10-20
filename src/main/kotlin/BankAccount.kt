import factory.Account
import observer.AccountObserver
import state.AccountState
import state.ActiveState

/**
 * Lớp BankAccount đại diện cho một tài khoản ngân hàng.
 *
 * 👉 Áp dụng nhiều mẫu thiết kế:
 * - Factory: tạo đối tượng Account thông qua AccountFactory
 * - Observer: thông báo khi số dư thay đổi
 * - State: thay đổi hành vi tùy theo trạng thái tài khoản (Active, Frozen, Closed)
 */
class BankAccount(
    val account: Account,                // Thông tin cơ bản về tài khoản (được tạo bởi AccountFactory)
    var balance: Double = 0.0,           // Số dư hiện tại
    var state: AccountState = ActiveState() // Trạng thái hiện tại của tài khoản
) {
    // Danh sách observer đang theo dõi tài khoản này
    private val observers = mutableListOf<AccountObserver>()

    /**
     * Đăng ký một observer để nhận thông báo khi số dư thay đổi.
     * (Ví dụ: NotificationService)
     */
    fun addObserver(observer: AccountObserver) = observers.add(observer)

    /**
     * Hủy đăng ký observer.
     */
    fun removeObserver(observer: AccountObserver) = observers.remove(observer)

    /**
     * Gửi thông báo đến tất cả observer khi có thay đổi số dư.
     */
    fun notifyObservers() = observers.forEach { it.onBalanceChanged(account, balance) }

    /**
     * Nạp tiền vào tài khoản.
     * Hành vi phụ thuộc vào trạng thái hiện tại (Active / Frozen / Closed).
     */
    fun deposit(amount: Double, description: String) {
        state.deposit(this, amount, description)
    }

    /**
     * Rút tiền khỏi tài khoản.
     * Hành vi cũng phụ thuộc vào trạng thái hiện tại.
     */
    fun withdraw(amount: Double, description: String) {
        state.withdraw(this, amount, description)
    }

    /**
     * Đóng băng tài khoản (chuyển trạng thái sang FrozenState).
     */
    fun freeze() = state.freeze(this)

    /**
     * Đóng tài khoản (chuyển trạng thái sang ClosedState).
     */
    fun close() = state.close(this)
}