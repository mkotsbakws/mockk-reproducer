import io.kotest.core.spec.style.FunSpec
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage

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
        xtest("mockk should succeed returning the value class - but actually fails") {
            fooMock.valueBar shouldBeEqualTo aValueBar
        }


        test("instead it throws a class cast exception") {
            invoking {
                fooMock.valueBar shouldBeEqualTo aValueBar
            } shouldThrow ClassCastException::class withMessage
                    "class ValueBar cannot be cast to class java.lang.String " +
                    "(ValueBar is in unnamed module of loader 'app'; " +
                    "java.lang.String is in module java.base of loader 'bootstrap')"
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
