package svalidate

import org.specs2.mutable.Specification

import svalidate.validators.PositiveInt

object PositiveIntSpec extends Specification {

  "The PositiveInt validator" should {

    "return an error for -1, -10, -100" in {
      PositiveInt.validate(-1)   must equalTo(Seq("The field %s must be a positive integer"))
      PositiveInt.validate(-10)  must equalTo(Seq("The field %s must be a positive integer"))
      PositiveInt.validate(-100) must equalTo(Seq("The field %s must be a positive integer"))
    }

    "return an error for 0" in {
      PositiveInt.validate(0)    must equalTo(Seq("The field %s must be a positive integer"))
    }

    "return no error 1, 10, 100" in {
      PositiveInt.validate(1)    must equalTo(Seq.empty)
      PositiveInt.validate(10)   must equalTo(Seq.empty)
      PositiveInt.validate(100)  must equalTo(Seq.empty)
    }

  }

}

