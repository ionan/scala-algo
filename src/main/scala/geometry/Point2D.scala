package com.ionan.scalaalgo.geometry

import scala.math

case class Point2D (x : Double, y : Double) {

    def + (p : Point2D) : Point2D = new Point2D(this.x + p.x,this.y + p.y)

    def / (d : Int) : Point2D = {
        require(d > 0, "d must be more than zero")
        new Point2D(this.x / d, this.y / d)
    }

    def distance(p: Point2D) : Double = {
        math.sqrt(math.pow(this.x - p.x,2) + math.pow(this.y - p.y,2))
    }
}
