package state

import BankAccount

class FrozenState: AccountState {
    override val type: AccountStateType = AccountStateType.ACTIVE

    override fun deposit(account: BankAccount, amount: Double, description: String) {
        println("Không thể nạp tiền, tài khoản ${account.account.accountNumber} đang bị đóng băng")
    }

    override fun withdraw(account: BankAccount, amount: Double, description: String) {
        println("Không thể rút tiền, tài khoản ${account.account.accountNumber} đang bị đóng băng")
    }

    override fun freeze(account: BankAccount) {
        println("Tài khoản ${account.account.accountNumber} đã đóng băng")
    }

    override fun close(account: BankAccount) {
        account.state = ClosedState()
        println("Tài khoản ${account.account.accountNumber} bị đóng")
    }
}