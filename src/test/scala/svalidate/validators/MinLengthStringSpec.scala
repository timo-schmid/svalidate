package svalidate

import org.specs2.mutable.Specification

import svalidate.validators.MinLengthString

object MinLengthStringSpec extends Specification {

  "The MinLengthString validator" should {

    val minLength1 = MinLengthString(1)
    val minLength3 = MinLengthString(3)

    "return an error for a string that is too short" in {
      minLength1.validate("")    must equalTo(Seq("The field %s must be at least 1 characters long"))
      minLength3.validate("a")   must equalTo(Seq("The field %s must be at least 3 characters long"))
    }

    "return no error for a string that is long enough" in {
      minLength1.validate("a")   must equalTo(Seq.empty)
      minLength3.validate("abc") must equalTo(Seq.empty)
    }

  }

}

