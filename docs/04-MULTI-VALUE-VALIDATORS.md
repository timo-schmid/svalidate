# Validators for complex data types

## Validating multiple fields of a class at once

If you haven't already done so in the last chapter, import the svalidate classes:

```scala
scala> import svalidate._
import svalidate._
```

Sometimes it's useful to validate two fields in a class together. Such an example would be to validate
whether the user typed his password correct when siging up. This is possible, since we can use a validator
of any type - for example tuple:

```scala
scala> object EqualStrings extends Validator[(String,String)] {
     | 
     |   override def validate(strings: (String, String)): Seq[String] =
     |     if(strings._1.equals(strings._2))
     |       Seq.empty
     |     else
     |       Seq(
     |         "The strings were not equal"
     |       )
     | 
     | }
defined module EqualStrings
```

This validator takes a `(String, String)` as his type parameter, so the validate method can actually handle
two values.

## Using a multi-value validator

```scala
scala> case class UserData(username: String, password: String, passwordConfirm: String)
defined class UserData

scala> val userData = UserData("jack", "jacktheripper", "jackthedipper")
userData: UserData = UserData(jack,jacktheripper,jackthedipper)

scala> val multiValidator = form[UserData](
     |   Validation("passwordConfirm", (f) => (f.password, f.passwordConfirm), EqualStrings)
     | )
multiValidator: UserData => svalidate.ValidationResult = <function1>

scala> multiValidator(userData)
res0: svalidate.ValidationResult = Map(passwordConfirm -> List(The strings were not equal))
```


