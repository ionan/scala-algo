package com.ionan.scalaalgo.geometry

import org.scalatest.FunSpec

class Point2DSpec extends FunSpec {
    val p1 = new Point2D(10,56)
    val p2 = new Point2D(20,70)

    describe("sum of two points") {
        it("should be equal to the sum of their coordinates"){
            assert(p1 + p2 == Point2D(30,126))
        }
    }
    describe("centroid of two points") {
        it("should be equal to the sum of their coordinates"){
            assert((p1 + p2) / 2 == Point2D(15,63))
        }
    }
    describe("dividing a point by zero") {
        it("should trigger an IllegalArgumentException"){
            intercept[IllegalArgumentException] {
                p1 / 0
            }
        }
    }
}
