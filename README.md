# svalidate - lightweight validation for scala

## Basic usage

```scala
import svalidate._
import svalidate.validator.NonEmptyString

case class UserData(id: Int, email: String, firstName: String, lastName: String)

val nameValidation = form[UserData](
  Validation("firstName", _.firstName, NonEmptyString),
  Validation("lastName",  _.lastName,  NonEmptyString)
)

// no errors
val jSparrow = UserData(1, "jack@blackpearl.sea", "Jack", "Sparrow")
val res1 = nameValidation(jSparrow)
// returns:
// Map() 

// some errors
val invalidUserData = UserData(0, "", "", "")
val res2 = nameValidation(invalidUserData)
// Map(
//   firstName -> List(The field %s must not be empty),
//   lastName -> List(The field %s must not be empty)
// )

```

## Build-dependency

```
libraryDependencies ++= Seq(
  "svalidate" %% "svalidate" % "0.3"
)
resolvers += Resolver.bintrayRepo("timo-schmid", "maven")
```

## Documentation

[Read the docs](http://svalidate.readthedocs.org/en/latest/)

## Running the tests

`sbt ~test`

## Generating the docs

`sbt tut`

