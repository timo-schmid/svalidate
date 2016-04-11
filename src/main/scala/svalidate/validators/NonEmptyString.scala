package svalidate.validators

import svalidate.Validator

object NonEmptyString extends Validator[String] {

  def validate(s: String): Seq[String] =
    if(s.isEmpty)
      Seq(
        "The field %s must not be empty"
      )
    else
      Seq.empty

}

