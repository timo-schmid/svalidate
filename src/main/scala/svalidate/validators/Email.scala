package svalidate.validators

import svalidate.Validator

object Email extends Validator[String] {

  // Copied from Play! (credit where credit is due)
  private val emailRegex = """^[a-zA-Z0-9\.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$""".r

  def validate(s: String): Seq[String] =
    emailRegex.findFirstIn(s) match {
      case Some(_) => Seq.empty
      case None => Seq(
        "The field %s is not a valid email"
      )
    }

}

