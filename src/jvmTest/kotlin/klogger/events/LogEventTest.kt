package klogger.events

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.shouldBe
import klogger.context.logContext
import klogger.logger
import kotlinx.coroutines.launch
import klogger.savedEvents
import klogger.waitForDispatch

class LogEventTest : DescribeSpec({
    describe("Constructing logging events") {
        describe("with context items") {
            it("does not include context items if there are none") {
                val saved = savedEvents()
                val logger = logger("LogEventTest")
                logger.info("Test message")
                waitForDispatch()
                saved.first().items.size shouldBe 0
            }
            it("includes any items from the coroutine log context") {
                launch(logContext("colour" to "white")) {
                    val saved = savedEvents()
                    val logger = logger("LogEventTest")
                    logger.info("Test message")
                    waitForDispatch()
                    saved.first().items shouldContain ("colour" to "white")
                }
            }
        }
    }
})