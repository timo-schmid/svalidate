# Welcome

Welcome and thanks for checking out **svalidate**.

## Why svalidate

In scala we already have many possibilities of data validtaion: There's
[Play Forms](https://www.playframework.com/documentation/2.5.x/ScalaForms)
of course if you're working with Play!, which is usually a great choice. The downside
is that it comes with the full Play! Framework as a dependency.

If you decide not to use Play! there's other alternatives:
[Scalaz](https://github.com/scalaz/scalaz) and [Cats](https://github.com/typelevel/cats)
both have Typeclasses for validation - however for beginners it can be difficult to use
these libraries. They are both very powerful, but with the power comes complexity.

I wanted something simple so I created a lightweight alternative: **svalidate**. If you
want composition and such features, you should checkout Scalaz or Cats.

## Scala dependency

To add svalidate to your build, add it to your project's libraryDependencies:

```
libraryDependencies ++= Seq(
  "svalidate" %% "svalidate" % "0.1-SNAPSHOT"
)
```

## Running the examples in a REPL

You can start a console in your project like so:

```
sbt console
```

To run the examples in this tutorial, add this import to your scala file or
REPL session:

```tut
import svalidate._
```

## About the docs

This documentation is written in [tut](https://github.com/tpolecat/tut) - a
documentation system that allows to type-check the examples. This ensures that the
examples shown in this documentation do actually compile and run in the REPL.
If an example doesn't work correctly and the library version is correct, please
raise a bug.

## Next

[Basic Usage](02-BASIC-USAGE.md)

