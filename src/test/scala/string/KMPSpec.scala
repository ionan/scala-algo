package com.ionan.scalaalgo.string

import org.scalatest.FunSpec

class KMPSpec extends FunSpec {
    val p = "SEVENTY SEVEN"
    val t1 = "I DO NOT LIKE SEVENTY SEV BUT SEVENTY SEVENTY SEVEN"
    val t2 = "COGITO ERGO SUM"
    
    val kmp = new KMP(p)
    
    describe("backtable for pattern '" + p + "'") {
      it("should equal Array(-1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5)"){
          assert(kmp.backTable.deep == Array(-1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5).deep)
      }
    }
    
    describe("for text '" + t1 + "'") {
      it("should find the pattern 'SEVENTY SEVEN' in indices 30 and 38"){
          assert(kmp.search(t1) == List(30,38))
      }
    }
    
    describe("when the text equals the pattern") {
      it("should find the pattern at index 0"){
          assert(kmp.search(p) == List(0))
      }
    }
}