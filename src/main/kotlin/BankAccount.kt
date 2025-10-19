import factory.Account
import observer.AccountObserver
import state.AccountState
import state.ActiveState

class BankAccount(
    val account: Account,
    var balance: Double = 0.0,
    var state: AccountState = ActiveState()
) {
    private val observers = mutableListOf<AccountObserver>()

    fun addObserver(observer: AccountObserver) = observers.add(observer)
    fun removeObserver(observer: AccountObserver) = observers.remove(observer)
    fun notifyObservers() = observers.forEach { it.onBalanceChanged(account, balance) }

    fun deposit(amount: Double, description: String){
        state.deposit(this, amount, description)
    }
    fun withdraw(amount: Double, description: String){
        state.withdraw(this, amount, description)
    }

    fun freeze() = state.freeze(this)

    fun close () = state.close(this)
}