package code.model

import java.text.SimpleDateFormat
import scala.xml.{Null, NodeSeq, UnprefixedAttribute}
import net.liftweb.http.SHtml
import net.liftweb.util.Helpers
import net.liftweb.common.{Full, Box}
import net.liftweb.mapper._

object Person extends Person with LongKeyedMetaMapper[Person]

class Person extends LongKeyedMapper[Person] with IdPK {
  def getSingleton = Person

  object firstName extends MappedString(this, 50) {
    override def validations = valMinLen(3, "Minimum length of first name is 3") _ :: super.validations
  }
  object lastName  extends MappedString(this, 50) {
    override def validations = valMinLen(3, "Minimum length of last name is 3") _ :: super.validations
  }
  object dateOfBirth extends MappedNullableLong(this) {
    import java.util.Date

    private[this] def theDateFormat = new SimpleDateFormat("MM/dd/yyyy")

    override def displayName = "Date of Birth"
    override def toString = theDateFormat.format( new Date(is openOr Helpers.millis) )

    override def toForm: Box[NodeSeq] = {
      import net.liftweb.util._

      def getStringValue = theDateFormat.format(is openOr Helpers.millis)

      def setDate(in: String): Unit = { set(Helpers.tryo { theDateFormat.parse(in) }.map(_.getTime)) }

      import net.liftweb._
      import http.js._
      import JsCmds._
      import JE._

      val cssClass = Helpers.nextFuncName

      val elem = SHtml.text(getStringValue, setDate _) % new UnprefixedAttribute("class", cssClass, Null)

      Full(elem ++
        Script(
          OnLoad(
            JsRaw("try{jQuery('."+cssClass+"').datepicker({changeYear: true, changeMonth: true, yearRange: '-50:+50', maxDate: '+2y', minDate: '-100y'});} catch(e) {alert(e);}"))))
    }
  }

  object email extends MappedEmail(this, 100) {
    override def displayName = "Email"
    override def validations = valMinLen(1, "Email required") _ :: super.validations
  }
}