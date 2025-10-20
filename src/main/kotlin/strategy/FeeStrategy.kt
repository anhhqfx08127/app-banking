package strategy
/**
 * Giao diện (interface) định nghĩa chiến lược tính phí giao dịch.
 *
 * Đây là phần cốt lõi của **Strategy Pattern**:
 * Cho phép thay đổi thuật toán tính phí (fixed, phần trăm, hoặc miễn phí)
 * mà không cần sửa đổi lớp sử dụng.
 */
interface FeeStrategy {
    /**
     * Hàm tính phí giao dịch.
     * @param amount Số tiền giao dịch.
     * @return Mức phí tương ứng.
     */
    fun fee(amount: Double): Double
}

/**
 * Chiến lược tính phí cố định (Fixed Fee)
 *
 * Ví dụ: mọi giao dịch đều mất 10.000 VNĐ phí.
 */
class FixedFee(private val fixed: Double) : FeeStrategy {
    override fun fee(amount: Double): Double {
        return fixed
    }
}

/**
 * Chiến lược tính phí theo phần trăm (Percent Fee)
 *
 * Ví dụ: phí là 2% giá trị giao dịch.
 */
class PercentFee(private val percent: Double) : FeeStrategy {
    override fun fee(amount: Double): Double {
        return percent * amount
    }
}

/**
 * Chiến lược miễn phí giao dịch (No Fee)
 *
 * Dành cho các loại tài khoản đặc biệt như lương hoặc VIP.
 */
class NoFee : FeeStrategy {
    override fun fee(amount: Double): Double {
        return 0.0
    }
}
