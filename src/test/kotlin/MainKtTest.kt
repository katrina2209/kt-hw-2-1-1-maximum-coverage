import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calculateFee_Default() {
        val transferSum = 4_000_00.00
        val expectedResult = 0.00

        val actualResult = calculateFee(transferSum = transferSum)

        assertEquals(expectedResult, actualResult, 0.001)

    }

    @Test
    fun calculateFee_MasterCard_Maestro_InLimits() {
        val accountType = "Maestro"
        val previousTransferPerMonth = 20_000_00.00
        val transferSum = 12_000_00.00
        val expectedResult = 0.00

        val actualResult = calculateFee(accountType, previousTransferPerMonth, transferSum)

        assertEquals(expectedResult, actualResult, 0.001)

    }

    @Test
    fun calculateFee_MasterCard_Maestro_OutOfLimits() {
        val accountType = "Maestro"
        val previousTransferPerMonth = 80_000_00.00
        val transferSum = 12_000_00.00
        val expectedResult = 72_00.00

        val actualResult = calculateFee(accountType, previousTransferPerMonth, transferSum)

        assertEquals(expectedResult, actualResult, 0.001)

    }

    @Test
    fun calculateFee_VkPay() {
        val accountType = "VkPay"
        val previousTransferPerMonth = 80_000_00.00
        val transferSum = 4_000_00.00
        val expectedResult = 0.00

        val actualResult = calculateFee(accountType, previousTransferPerMonth, transferSum)

        assertEquals(expectedResult, actualResult, 0.001)

    }

    @Test
    fun calculateFee_Else() {
        val accountType = "MyPaySystem"
        val previousTransferPerMonth = 80_000_00.00
        val transferSum = 4_000_00.00
        val expectedResult = 0.00

        val actualResult = calculateFee(accountType, previousTransferPerMonth, transferSum)

        assertEquals(expectedResult, actualResult, 0.001)

    }

    @Test
    fun calculateFee_Visa_Mir() {
        val accountType = "Visa"
        val previousTransferPerMonth = 80_000_00.00
        val transferSum = 12_000_00.00
        val expectedResult = 90_00.00

        val actualResult = calculateFee(accountType, previousTransferPerMonth, transferSum)

        assertEquals(expectedResult, actualResult, 0.001)

    }

    @Test
    fun areLimitsNotOverrun_VkPay_Not_Overrun() {
        val previousTransferPerDay = 10_000_00.00
        val previousTransferPerMonth = 0.00
        val transferSum = 4_000_00.00
        val accountType = "VkPay"
        val expectedResult = true

        val actualResult =
            areLimitsNotOverrun(previousTransferPerDay, previousTransferPerMonth, transferSum, accountType)

        assertEquals(expectedResult, actualResult)
    }


    @Test
    fun areLimitsNotOverrun_VkPay_OverrunPerMonth() {
        val previousTransferPerDay = 10_000_00.00
        val previousTransferPerMonth = 40_000_00.00
        val transferSum = 4_000_00.00
        val accountType = "VkPay"
        val expectedResult = false

        val actualResult =
            areLimitsNotOverrun(previousTransferPerDay, previousTransferPerMonth, transferSum, accountType)

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun areLimitsNotOverrun_VkPay_OverrunPerSum() {
        val previousTransferPerDay = 10_000_00.00
        val previousTransferPerMonth = 10_000_00.00
        val transferSum = 20_000_00.00
        val accountType = "VkPay"
        val expectedResult = false

        val actualResult =
            areLimitsNotOverrun(previousTransferPerDay, previousTransferPerMonth, transferSum, accountType)

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun areLimitsNotOverrun_Not_VkPay_Not_Overrun() {
        val previousTransferPerDay = 10_000_00.00
        val previousTransferPerMonth = 0.00
        val transferSum = 4_000_00.00
        val accountType = "Visa"
        val expectedResult = true

        val actualResult =
            areLimitsNotOverrun(previousTransferPerDay, previousTransferPerMonth, transferSum, accountType)

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun areLimitsNotOverrun_Not_VkPay_Overrun_Day() {
        val previousTransferPerDay = 150_000_00.00
        val previousTransferPerMonth = 40_000_00.00
        val transferSum = 4_000_00.00
        val accountType = "Maestro"
        val expectedResult = false

        val actualResult =
            areLimitsNotOverrun(previousTransferPerDay, previousTransferPerMonth, transferSum, accountType)

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun areLimitsNotOverrun_Not_VkPay_Overrun_Month() {
        val previousTransferPerDay = 10_000_00.00
        val previousTransferPerMonth = 800_000_00.00
        val transferSum = 4_000_00.00
        val accountType = "Maestro"
        val expectedResult = false

        val actualResult =
            areLimitsNotOverrun(previousTransferPerDay, previousTransferPerMonth, transferSum, accountType)

        assertEquals(expectedResult, actualResult)
    }


}
