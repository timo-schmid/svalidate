# Your own Validators

If you haven't already done so in the last chapter, import the svalidate classes:

```tut
import svalidate._
```

When it comes to validation, everyone has their own requirements:
* Your webshop accepts only m/f for gender, while a medical software might accept several other options
* A zip in Switzerland is 4 digits long, while it's 5 digits in most other countries
* Phone number layouts differ from country to country
* Usually you want to customize the error messages generated from your validation layer

Thus, **svalidate** does not impose any restrictions on *what* or *how* you validate.

## The Validator trait

Let's have a closer look at the `Validator` trait:

```tut
trait Validator[V] {

  def validate(value: V): Seq[String]

}
```

As we can see, it has a type parameter `T`: If we implement it, we always implement it for a certain type. It's type is the
type of the property we validate.

There's a single method we implement: *validate*. It takes a value of our Type `T` and returns a `Seq[String]`,
which is the list of errors.

Lets look at an example, the `NonEmptyString` validator.

```tut
object NonEmptyString extends Validator[String] {

  def validate(s: String): Seq[String] =
    if(s.isEmpty)
      Seq(
        "The field %s must not be empty"
      )
    else
      Seq.empty

}
```

In the above example, we can see that the property type of the `NonEmptyString` validator is `String`. 
It's validate method thus takes a single string as it's argument. In the implementation, we just return
a single error if the string is empty or no error if the string is not empty.

