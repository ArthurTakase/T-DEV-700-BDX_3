import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.javalin.http.Context

class AccountHasMoneyWebHandler(val accountRepository: AccountRepository) : WebHandler() {
    override fun call(ctx: Context) {
        val id: Int = ctx.pathParam("id").toInt()
        val objectMapper = jacksonObjectMapper()
        val amount: Double = objectMapper.readTree(ctx.body()).get("amount").toString().toDouble()
        val account = accountRepository.get(id)

        if (account == null) {
            ctx.status(404).json("Account not found")
        } else {
            if (amount < account.sold) {
                accountRepository.purchase(id, amount)
                ctx.status(200).json("ok")
            } else {
                ctx.status(406)
            }
        }
    }
}
