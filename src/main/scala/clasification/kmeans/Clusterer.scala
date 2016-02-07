package com.ionan.scalaalgo.classification.kmeans

import com.ionan.scalaalgo.geometry._

class Clusterer (numClusters : Int){
    private var rnd = new scala.util.Random

    def cluster(data : List[Point2D]) {
        // Assign points to clusters randomly
        var clustering = data.groupBy(_ => rnd.nextInt(numClusters))

        //println("Clustering => " + clustering)

        // Update centroids
        val centroids = clustering map {
                             case (_,value) => value.reduceLeft(_ + _) / value.length
                         }

        //println("Centroids => " + centroids)
    }
}
