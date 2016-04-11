package svalidate

import org.specs2.mutable.Specification

import svalidate.common.{PositiveInt, NonEmptyString, MinLengthString, EqualPasswords}

case class TestData(i: Int, s: String)

object ValidationSpec extends Specification {

  "A validation" should {

    "be able to validate a single int property of a case class" in {
      val demoDataValid      = TestData(1, "")
      val demoDataWithError  = TestData(0, "")
      validateSingle(demoDataValid)(
        Validation[TestData, Int]("i", _.i, PositiveInt)
      ) must equalTo("i", Seq.empty)
      validateSingle(demoDataWithError)(
        Validation[TestData, Int]("i", _.i, PositiveInt)
      ) must equalTo(("i", Seq("The %s must be a positive integer")))
    }

    "be able to validate a single string property of a case class" in {
      val demoDataValid      = TestData(1, "content")
      val demoDataWithError  = TestData(1, "")
      validateSingle(demoDataValid)(
        Validation[TestData, String]("s", _.s, NonEmptyString)
      ) must equalTo("s", Seq.empty)
      validateSingle(demoDataWithError)(
        Validation[TestData, String]("s", _.s, NonEmptyString)
      ) must equalTo(("s", Seq("The %s must not be empty")))
    }

    "be able to validate a case class with multiple validators" in {
      val demoDataValid      = TestData(1, "content")
      val demoDataWithErrors = TestData(0, "")
      val myValidator = form[TestData](
        Validation("i", _.i, PositiveInt),
        Validation("s", _.s, NonEmptyString)
      )
      myValidator(demoDataValid) must equalTo(Map.empty)
      myValidator(demoDataWithErrors) must equalTo(Map("i" -> Seq("The %s must be a positive integer"), "s" -> Seq("The %s must not be empty")))
    }

    "be able to validate a single property with multiple validators" in {
      val demoDataValid      = TestData(1, "content")
      val demoDataWithErrors = TestData(0, "")
      var myValidator = form[TestData](
        Validation("i", _.i, PositiveInt),
        Validation("s", _.s, NonEmptyString),
        Validation("s", _.s, MinLengthString(3))
      )
      myValidator(demoDataValid) must equalTo(Map.empty)
      myValidator(demoDataWithErrors) must equalTo(Map("i" -> Seq("The %s must be a positive integer"), "s" -> Seq("The %s must not be empty", "The %s must be at least 3 characters long")))
    }

    "be able to validate two values together" in {
      case class UserForm(username: String, password: String, passwordConfirm: String)
      val userFormValid   = UserForm("jack", "jacktheripper", "jacktheripper")
      val userFormInvalid = UserForm("jack", "jacktheripper", "jackthedipper")
      val myValidator = form[UserForm](
        Validation("username", _.username, MinLengthString(3)),
        Validation("password", (f) => (f.password, f.passwordConfirm), EqualPasswords)
      )
      myValidator(userFormValid) must equalTo(Map.empty)
      myValidator(userFormInvalid) must equalTo(Map("password" -> Seq("The passwords did not match")))
    }

  }

}

