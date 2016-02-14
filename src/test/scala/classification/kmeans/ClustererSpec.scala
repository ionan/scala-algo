package com.ionan.scalaalgo.classification.kmeans

import org.scalatest.FunSpec
import com.ionan.scalaalgo.geometry._
import scala.io.Source

class ClustererSpec extends FunSpec {
    val points = Source.fromFile("src/resources/k_means_sample_100.txt", "utf-8")
                         .getLines
                         .map(toPoint2D(_))

    val cl = new Clusterer(3)
    val clusters = cl.cluster(points.toList)

    new ScatterPlot("Test",clusters)

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

    def toPoint2D(xy : String) : Point2D = {
        val x = xy.split("\t")(0).toDouble
        val y = xy.split("\t")(1).toDouble
        Point2D(x,y)
    }
}
