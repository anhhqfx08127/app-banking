package state

import BankAccount

class ClosedState: AccountState {
    override val type: AccountStateType = AccountStateType.ACTIVE

    override fun deposit(account: BankAccount, amount: Double, description: String) {
        println("Không thể nạp tiền, tài khoản ${account.account.accountNumber} đang bị đóng")
    }

    override fun withdraw(account: BankAccount, amount: Double, description: String) {
        println("Không thể rút tiền, tài khoản ${account.account.accountNumber} đang bị đóng")
    }

    override fun freeze(account: BankAccount) {
        account.state = FrozenState()
        println("Tài khoản ${account.account.accountNumber} đã đóng")
    }

    override fun close(account: BankAccount) {
        println("Tài khoản ${account.account.accountNumber} đã đóng")
    }
}