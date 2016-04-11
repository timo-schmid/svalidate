
package object svalidate {

  type ValidationResult = Map[String, Seq[String]]

  def validateSingle[T, V](t: T)(validation: Validation[T, V]): (String, Seq[String]) =
    (validation.label, validation.validator.validate(validation.get(t)))

  def form[T](validations: Validation[T, _] *): T => ValidationResult =
    (t: T) => validations.foldLeft(Map[String, Seq[String]]()) { (map, validation) =>
      val newValidation = validateSingle(t)(validation)
      map + (
        newValidation._1 -> (map.getOrElse(newValidation._1, Seq.empty) ++ newValidation._2)
      )
    }.filterNot(_._2.isEmpty)

}

