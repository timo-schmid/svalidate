# svalidate - easy case class validation

## Why svalidate

In scala we already have many possibilities of data validtaion: There's **Play Form** if you're working with Play! - however if you decide not to use Play! there's other alternatives:
Scalaz and Cats both have Typeclasses for validation - however for beginners it can be confusing to use these libraries - so I created a lightweight alternative: **svalidate**.

To run the examples in this tutorial, first add this import to your scala file / REPL session:

```tut
import svalidate._
```

## Basic usage

The library is built around case classes - so lets define one for our user data:

```tut
case class UserData(id: Int, email: String, firstName: String, lastName: String)
```

**svalidate** comes with a few built-in validations that are fairly common. A common example is the NonEmptyValidator:

```tut
import svalidate.common.NonEmptyString
```

Let's use the *NonEmptyString* validator to make sure that the first name and last name are not empty:

```tut
val nameValidation = form[UserData](
  Validation("firstName", _.firstName, NonEmptyString),
  Validation("lastName",  _.lastName,  NonEmptyString)
)
```

In the above example we create a so-called *form* we can use it many times to validate a *UserData*. We passed 2 *Validation* objects to the form:
A validation is not more than a case class. It takes 3 arguments:
* **label** The label: Validations errors will be collected under this label. This doesn't have to be the same as the field name.
* **get** The getter to get the value from the case class. In the first example, we return the property *firstName* of the UserData.
* **validator** An implementation of the *Validator* trait. The type of the Validator has to match the type we return in the getter.

So, for example if we want to validate the *firstName* we return a String, which can be validated using the *NonEmptyString* validator (because it extends *Validator[String]*).

Now that we created the validation, we can try it on an object instance.

```tut
val jSparrow = UserData(1, "jack@blackpearl.sea", "Jack", "Sparrow")
val res1 = nameValidation(jSparrow)
```

That was boring - lets see what happens when we violate some rules:

```tut
val invalidUserData = UserData(0, "", "", "")
val res2 = nameValidation(invalidUserData)
```

Aha - we lets see what we got:

```tut:silent
Map(
  "firstName" -> List("The %s must not be empty"),
  "lastName"  -> List("The %s must not be empty")
)
```

