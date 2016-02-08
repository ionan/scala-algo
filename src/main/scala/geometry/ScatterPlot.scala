package com.ionan.scalaalgo.geometry

import scala.Array._
import javax.swing.JFrame
import java.awt.RenderingHints
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.NumberAxis
import org.jfree.chart.plot.FastScatterPlot
import org.jfree.ui.ApplicationFrame
import org.jfree.ui.RefineryUtilities
import java.awt.Graphics2D
import java.awt.Paint
import java.awt.Color
import java.awt.geom.Rectangle2D
import org.jfree.chart.plot.CrosshairState
import org.jfree.chart.plot.PlotRenderingInfo
import org.jfree.ui.RectangleEdge

class ScatterPlot(title : String, points : List[Point2D], colors : List[Int]) extends JFrame(title : String) {
    private val colorList = List(Color.red, Color.blue, Color.green, Color.yellow, Color.cyan, Color.magenta, Color.orange,
                                 Color.pink, Color.darkGray, Color.gray, Color.lightGray, Color.white, Color.black)
    private val data = ofDim[Float](2,points.length)
    populateData
    drawPlot

    def this(title : String, points : List[Point2D]) {
        this(title, points, List.fill(points.length)(0))
    }

    def this(title : String, clusters : Map[Int,List[Point2D]]) {        
        this(title, clusters.values.toList.flatten, clusters.foldLeft(List() : List[Int])((l,kv) => l ++ List.fill(kv._2.length)(kv._1)))
    }

    private def drawPlot(){
        val domainAxis = new NumberAxis("X")
        domainAxis.setAutoRangeIncludesZero(false)
        val rangeAxis = new NumberAxis("Y")
        rangeAxis.setAutoRangeIncludesZero(false)
        val plot = new MyFastScatterPlot(data, domainAxis, rangeAxis, colors.map(this.colorList(_)))
        val chart = new JFreeChart(title, plot)
        chart.getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        val panel = new ChartPanel(chart, true)
        setContentPane(panel)
        this.pack
        this.setLocationRelativeTo(null)
        this.setVisible(true)
    }

    private def populateData {
        for (i <- 0 until points.length){
            data(0)(i) = points(i).x.asInstanceOf[Float]
            data(1)(i) = points(i).y.asInstanceOf[Float]
        }
    }

    class MyFastScatterPlot(data : Array[Array[Float]], domainAxis : NumberAxis, rangeAxis : NumberAxis, colors : List[Color])
    extends FastScatterPlot(data : Array[Array[Float]], domainAxis : NumberAxis, rangeAxis : NumberAxis) {
        override def render(g2 : Graphics2D, dataArea : Rectangle2D, info : PlotRenderingInfo, crosshairState : CrosshairState) {
           if (getData() != null) {
               for (i <- 0 until getData()(0).length) {
                   val x = getData()(0)(i)
                   val y = getData()(1)(i)
                   val transX = this.getDomainAxis().valueToJava2D(x, dataArea, RectangleEdge.BOTTOM).asInstanceOf[Int]
                   val transY = this.getRangeAxis().valueToJava2D(y, dataArea, RectangleEdge.LEFT).asInstanceOf[Int]
                   g2.setPaint(this.colors(i))
                   g2.fillOval(transX, transY, 5, 5)
               }
           }
       }
    }
}
