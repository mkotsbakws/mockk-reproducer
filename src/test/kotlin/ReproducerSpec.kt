import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage

object ReproducerSpec : FunSpec({

    val aValueBar = ValueBar("aValueBar")

    context("Reproduce issue") {
        test("with a mocked interface returning a value class from getValue") {
            // Arrange
            val expectedResult = Result.success(aValueBar)
            val fooMock = mockk<Foo>()
            coEvery { fooMock.getValueBar() } returns Result.success(aValueBar)

            // Act
            val result = fooMock.getValueBar()
            // Assert
            result.isSuccess shouldBe true
            result.getOrNull()?.id shouldBeEqualTo aValueBar.id
            result shouldBeEqualTo expectedResult
        }
    }
})

class Foo {
    suspend fun getValueBar(): Result<ValueBar> = TODO()
}


@JvmInline
value class ValueBar(val id: String)
