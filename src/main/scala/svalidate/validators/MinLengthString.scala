package svalidate.validators

import svalidate.Validator

class MinLengthString(minLength: Int) extends Validator[String] {

  def validate(s: String): Seq[String] =
    if(s.length < minLength)
      Seq(
        s"The field %s must be at least $minLength characters long"
      )
    else
      Seq.empty

}

object MinLengthString {

  def apply(minLength: Int): MinLengthString =
    new MinLengthString(minLength)

}

