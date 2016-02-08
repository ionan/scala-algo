package com.ionan.scalaalgo.classification.kmeans

import com.ionan.scalaalgo.geometry._
import scala.util.control.Breaks._

class Clusterer (numClusters : Int){
    private var rnd = new scala.util.Random
    var iter = -1

    def cluster(data : List[Point2D]) = {
        // Assign points to clusters randomly
        var clustering = data.groupBy(_ => rnd.nextInt(numClusters))

        // Update centroids
        var centroids = updateCentroids(clustering)

        breakable {
            // Loop until clustering stays the same
            // Limit max executions to avoid infinite loops
            for (i <- 0 to data.length * 10) {
                val newClustering = data.groupBy(x => closestCentroid(x,centroids))
                if (clustering == newClustering){
                    iter = i
                    break
                }
                clustering = newClustering
                centroids = updateCentroids(clustering)
            }
        }
        clustering
    }

    private def closestCentroid(p : Point2D, centroids : Iterable[Point2D]) =
        centroids.zipWithIndex.reduceLeft((x,y) => if (p.distance(x._1) < p.distance(y._1)) x else y )._2

    private def updateCentroids(clustering : Map[Int,List[Point2D]]) =
        clustering map { case (_,value) => value.reduceLeft(_ + _) / value.length }
}
