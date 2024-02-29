import io.kotest.core.spec.style.FunSpec
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo

object ReproducerSpec : FunSpec({

    val aRegularBar = RegularBar("aRegularBar")
    val aValueBar = ValueBar("aValueBar")

    context("with a mocked interface returning a regular class") {
        val fooMock = mockk<Foo> {
            every { regularBar } returns aRegularBar
        }
        test("mockk should succeed returning the regular class") {
            fooMock.regularBar shouldBeEqualTo aRegularBar
        }
    }

    context("with a mocked interface returning a value class") {
        val fooMock = mockk<Foo> {
            every { valueBar } returns aValueBar
        }
        test("mockk should succeed returning the value class") {
            fooMock.valueBar shouldBeEqualTo aValueBar
        }
    }
})

interface Foo {
    val regularBar: RegularBar
    val valueBar: ValueBar
}

class RegularBar(val id: String)

@JvmInline
value class ValueBar(val id: String)
