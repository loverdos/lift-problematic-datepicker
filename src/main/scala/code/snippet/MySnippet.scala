package code
package snippet

import model._
import scala.xml.{Group, NodeSeq, Text}
import net.liftweb.util.{Helpers, AltXML, CssSel}
import net.liftweb.util.Helpers._
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.jquery.JqJsCmds.ModalDialog
import net.liftweb.http.{S, SHtml}
import net.liftweb.wizard.Wizard

object MySnippet {
  def DialogWithBlockUI(): JsCmd = {
    ModalDialog(<lift:TestWizard ajax="true"/>)
  }

  def render: CssSel = {
    "@action_dialog_blockUI [onclick]"  #> SHtml.ajaxInvoke(DialogWithBlockUI)
  }
}

object TestWizard extends Wizard {
  object ThePerson extends WizardVar(Person.create)
  
  val screen1 = new Screen {
    val firstName = field(ThePerson.get.firstName)
    val lastName  = field(ThePerson.get.lastName)
    val dateOfBirth = field(ThePerson.get.dateOfBirth)
  }

  val screen2 = new Screen {
    val email = field(ThePerson.get.email)
  }


  protected def finish() = {}
}

