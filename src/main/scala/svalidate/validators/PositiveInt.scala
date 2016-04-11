package svalidate.validators

import svalidate.Validator

object PositiveInt extends Validator[Int] {

  def validate(i: Int): Seq[String] =
    if(i <= 0)
      Seq(
        "The field %s must be a positive integer"
      )
    else
      Seq.empty

}

