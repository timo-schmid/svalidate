package svalidate.validators

import svalidate.Validator

object EqualStrings extends Validator[(String,String)] {

  override def validate(bothStrings: (String, String)): Seq[String] =
    if(bothStrings._1.equals(bothStrings._2))
      Seq.empty
    else
      Seq(
        "The strings were not equal"
      )

}

