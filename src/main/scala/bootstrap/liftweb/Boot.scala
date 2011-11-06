package bootstrap.liftweb

import net.liftweb.common.Full
import net.liftweb.http.{Html5Properties, Req, LiftRules}
import net.liftweb.sitemap.{Menu, SiteMap}
import net.liftweb.sitemap.Loc.LocGroup


class Boot {
  def boot: Unit = {

    // where to search snippet
    LiftRules.addToPackages("code")

    // Build SiteMap
    val siteMap = SiteMap(
      Menu.i("Home")   / "index"           >> LocGroup("main"),
      Menu.i("Wizard") / "wizard_onscreen" >> LocGroup("special")
    )

    LiftRules.setSiteMapFunc(() => siteMap)

    // Use jQuery 1.4
    LiftRules.jsArtifacts = net.liftweb.http.js.jquery.JQuery14Artifacts

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)
    
    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))


    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))
  }
}
