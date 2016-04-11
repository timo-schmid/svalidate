package svalidate

case class Validation[T, V](label: String, get: T => V, validator: Validator[V])

