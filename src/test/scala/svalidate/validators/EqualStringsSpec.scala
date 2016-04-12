package svalidate

import org.specs2.mutable.Specification

import svalidate.validators.EqualStrings

object EqualStringsSpec extends Specification {

  "The EqualStrings validator" should {

    "validate equal strings correctly" in {
      EqualStrings.validate(("a", "b"))  must equalTo(Seq("The strings were not equal"))
    }

    "validate unequal strings correctly" in {
      EqualStrings.validate(("abababa", "abababa")) must equalTo(Seq.empty)
    }

  }

}

