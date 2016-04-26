package svalidate.java

import svalidate.{Validator => SValidator}
import svalidate.{Validation => SValidation}
import svalidate.java.{Validator => JValidator}
import svalidate.java.{Validation => JValidation}
import java.util.{List => JList}
import java.util.{Map => JMap}
import java.util.function.Function
import scala.collection.{Seq => SList}
import scala.collection.immutable.{Map => SMap}
import scala.collection.JavaConverters._

private [java] object JavaInterop {

  def javaForm[T](jValidations: JList[JValidation[T,_]]): Function[T, JMap[String,JList[String]]] =
    new Function[T, JMap[String,JList[String]]] {

      private val sValidations: SList[SValidation[T, _]] =
        jValidations.asScala.toSeq.map(v => toScala(v))

      private val form: (T) => SMap[String,SList[String]] =
        svalidate.form[T](sValidations: _*)

      def apply(t: T): JMap[String,JList[String]] = {
        val sResult: svalidate.ValidationResult = form(t)
        val jResult: JMap[String,JList[String]] = sResult.mapValues(_.asJava).asJava
        jResult
      }

    }

  def toScala[T, V](jFunc: Function[T, V]): T => V =
    (e) => jFunc(e)

  def toScala[T](jVal: JValidator[T]): SValidator[T] =
    new SValidator[T] {

      def validate(t: T): SList[String] =
        jVal.validate(t).asScala.toSeq

    }

  def toScala[T, V](jVal: JValidation[T, V]): SValidation[T, V] =
    SValidation(jVal.label, toScala(jVal.get), toScala(jVal.validator))

}

