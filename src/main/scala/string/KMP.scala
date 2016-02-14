package com.ionan.scalaalgo.string

import scala.collection.mutable.ListBuffer

class KMP (pattern : String) {
    private val m = pattern.length
    var backTable = new Array[Int](m + 1)
    
    preprocess()
    
    private def preprocess(){
        var i = 0
        var j = -1
        backTable(0) = -1
        while (i < m){
            while (j >= 0 && pattern(i) != pattern(j)) j = backTable(j)
            i = i + 1
            j = j + 1
            backTable(i) = j
        }
    }
    
    def search(text : String) : List[Int] = {
        var i = 0
        var j = 0
        var res = new ListBuffer[Int]()
        while (i < text.length) {
            while (j >= 0 && text(i) != pattern(j)) j = backTable(j);
            i = i + 1
            j = j + 1
            if (j == m) {
                res += (i - j)
                j = backTable(j);
            } 
        }
        res.toList
    }
}