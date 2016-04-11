package svalidate

trait Validator[V] {

  def validate(value: V): Seq[String]

}

