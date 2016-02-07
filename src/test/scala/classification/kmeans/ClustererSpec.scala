package com.ionan.scalaalgo.classification.kmeans

import org.scalatest.FunSpec
import com.ionan.scalaalgo.geometry._

class ClustererSpec extends FunSpec {
    val points = List(
        new Point2D(73,72.6),
        new Point2D(61,54.4),
        new Point2D(67,99.9),
        new Point2D(68,97.3),
        new Point2D(62,59.0),
        new Point2D(75,81.6),
        new Point2D(74,77.1),
        new Point2D(66,97.3),
        new Point2D(68,93.3),
        new Point2D(61,59.0)
    )

    new ScatterPlot("Test",points)

    val cl = new Clusterer(3)
    cl.cluster(points)
  /*describe("array for 3 clusters / 10 tuples") {
    it("should equals 10"){
        assert(arr.length == 10)
    }
  }
  describe("values for array of 3 clusters") {
    it("should be less than or equal to 2"){
        assert(arr.filter(_ < 3).length == arr.length)
    }
    it("should be bigger than or equal to 0"){
        assert(arr.filter(_ >= 0).length == arr.length)
    }
}*/
}
