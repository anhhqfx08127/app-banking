package iterator

/**
 * 💾 TransactionRecord: Lưu thông tin của một giao dịch cụ thể.
 *
 * @param description Mô tả giao dịch (VD: "Rút tiền ATM", "Nộp tiền")
 * @param amount Số tiền giao dịch (âm nếu là rút, dương nếu là nạp)
 * @param balanceAfter Số dư sau khi giao dịch hoàn tất
 * @param timestamp Thời điểm thực hiện giao dịch (mặc định là thời gian hiện tại, tính theo milliseconds)
 */
data class TransactionRecord(
    val description: String,
    val amount: Double,
    val balanceAfter: Double,
    val timestamp: Long = System.currentTimeMillis()
)

/**
 * 📜 TransactionHistory: Lưu danh sách các giao dịch của tài khoản.
 *
 * 👉 Áp dụng **Iterator Pattern**
 * - `Iterable<TransactionRecord>` giúp lớp này có thể được duyệt bằng `for (record in history)`
 * - Che giấu cấu trúc lưu trữ nội bộ (danh sách `transactions`) khỏi người dùng bên ngoài.
 */
class TransactionHistory : Iterable<TransactionRecord> {
    // Danh sách lưu trữ các giao dịch
    private val transactions = mutableListOf<TransactionRecord>()

    /**
     * ➕ Thêm một bản ghi giao dịch mới vào lịch sử.
     */
    fun add(record: TransactionRecord) = transactions.add(record)

    /**
     * 🔁 Trả về Iterator cho phép duyệt qua các TransactionRecord.
     * → Giúp có thể dùng: `for (tx in history)`
     */
    override fun iterator(): Iterator<TransactionRecord> = transactions.iterator()
}