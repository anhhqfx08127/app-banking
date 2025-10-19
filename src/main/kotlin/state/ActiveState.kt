package state

import BankAccount

class ActiveState: AccountState {
    override val type: AccountStateType = AccountStateType.ACTIVE

    override fun deposit(account: BankAccount, amount: Double, description: String) {
        account.balance += amount
        println("Nạp $amount tới số tài khoản ${account.account.accountNumber}")
        account.notifyObservers()
    }

    override fun withdraw(account: BankAccount, amount: Double, description: String) {
        if(amount > account.balance) {
            println("Tài khoản không đủ tiền để rút")
            return
        }
        account.balance -= amount
        println("Rút $amount từ số tài khoản ${account.account.accountNumber}")
        account.notifyObservers()
    }

    override fun freeze(account: BankAccount) {
        account.state = FrozenState()
        println("Tài khoản ${account.account.accountNumber} bị đóng băng")
    }

    override fun close(account: BankAccount) {
        account.state = ClosedState()
        println("Tài khoản ${account.account.accountNumber} bị đóng")
    }
}