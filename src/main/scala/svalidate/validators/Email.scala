package svalidate.validators

import svalidate.Validator

object Email extends Validator[String] {

  // Copied from Play! (credit where credit is due)
  private val emailRE = """^[a-zA-Z0-9\.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$""".r

  def validate(s: String): Seq[String] =
    s match {
      case `emailRE` => Seq.empty
      case _ => Seq(
        "The field %s is not a valid email"
      )
    }

}

