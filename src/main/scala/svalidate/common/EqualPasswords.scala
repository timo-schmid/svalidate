package svalidate.common

import svalidate.Validator

object EqualPasswords extends Validator[(String,String)] {

  override def validate(passwords: (String, String)): Seq[String] =
    if(passwords._1.equals(passwords._2))
      Seq.empty
    else
      Seq(
        "The passwords did not match"
      )

}

