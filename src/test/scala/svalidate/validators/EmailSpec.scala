package svalidate

import org.specs2.mutable.Specification

import svalidate.validators.Email

object EmailSpec extends Specification {

  "The email validator" should {

    "return an error for an invalid email" in {
      Email.validate("not an email")  must equalTo(Seq("The field %s is not a valid email"))
    }

    "return no error for a valid email" in {
      Email.validate("foo@localhost") must equalTo(Seq.empty)
      Email.validate("foo@bar.com")   must equalTo(Seq.empty)
    }

  }

}

