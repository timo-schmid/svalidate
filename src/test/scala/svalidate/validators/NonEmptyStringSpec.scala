package svalidate

import org.specs2.mutable.Specification

import svalidate.validators.NonEmptyString

object NonEmptyStringSpec extends Specification {

  "The NonEmptyString validator" should {

    "return an error for an empty string" in {
      NonEmptyString.validate("") must equalTo(Seq("The field %s must not be empty"))
    }

    "return no error for an nonempty string" in {
      NonEmptyString.validate("abc") must equalTo(Seq.empty)
    }

  }

}

