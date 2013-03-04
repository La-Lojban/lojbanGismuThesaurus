package iocikun.juj.lojban.gismu_thesaurus

import _root_.java.io.{BufferedReader, InputStreamReader}
import _root_.scala.xml.{XML, Node}

import _root_.android.app.Activity
import _root_.android.os.Bundle

import _root_.android.content.Intent
import _root_.android.preference.PreferenceManager

import _root_.android.view.View
import _root_.android.view.View.OnClickListener
import _root_.android.view.Window
import _root_.android.view.Menu
import _root_.android.view.MenuItem

import _root_.android.widget.TextView
import _root_.android.widget.LinearLayout

import _root_.android.content.res.AssetManager

import _root_.android.graphics.Typeface

import _root_.android.util.Log

class LojbanGismuThesaurus extends Activity with TypedActivity {
	lazy val top = findView(TR.top)
	lazy val am = getAssets()
	lazy val pm = PreferenceManager getDefaultSharedPreferences this

	override def onCreate(bundle: Bundle) {
		super.onCreate(bundle)
		requestWindowFeature(Window.FEATURE_NO_TITLE)
		setContentView(R.layout.main)

		val file = new BufferedReader(new InputStreamReader(
			am.open("thsrs.xml"), "UTF-8"))
		val xml = XML.load(file)

		for (sec <- xml \ "section") {
			val test = addSection(sec, "")
			top.addView(test)
		}
	}

	def addSection(xml : Node, idnt : String): LinearLayout = {
		val top = new LinearLayout(this)
		val tv = new TextView(this)
		val ll = new LinearLayout(this)
		var added = false

		top.setOrientation(LinearLayout.VERTICAL)
		tv.setText(idnt + (xml \ "@title").toString)
		tv.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)
		tv.setTextSize(20.0f)
		ll.setOrientation(LinearLayout.VERTICAL)

		top.addView(tv)
		for (w <- xml \ "item") {
			val wll = new LinearLayout(this)
			val wtv = new TextView(this)
			val wentv = new TextView(this)
			wtv.setText(idnt + "  " + (w \ "@title").toString)
			wtv.setTypeface(Typeface.MONOSPACE, Typeface.BOLD)
			wentv.setText("  " + (w \ "@description").toString)
			wll.addView(wtv)
			wll.addView(wentv)
			ll.addView(wll)
		}
		for (s <- xml \ "section") ll.addView(addSection(s, idnt + "    "))

		tv.setOnClickListener(new View.OnClickListener() {
			def onClick(_v: View) {
				if (added) {
					top.removeView(ll)
					added = false
				} else {
					top.addView(ll)
					added = true
				}
			}
		})

		top
	}

	override def onResume {
		super.onResume
		if (pm.contains("source_xml_file")) {
			Log.d("LojbanGismuThesaurus", "source file: " + pm.getString("source_xml_file", ""))
		}
	}

	override def onCreateOptionsMenu(menu: Menu): Boolean = {
		menu.add(Menu.NONE, 0, 0, "settings")
		return super.onCreateOptionsMenu(menu)
	}

	override def onOptionsItemSelected(item: MenuItem): Boolean = {
		item.getItemId() match {
		case 0 =>
			Log.d("LojbanGismuThesaurus", "selected")
			val intent = new Intent(this, classOf[Preference])
			Log.d("LojbanGismuThesaurus", "before startActivity")
			startActivity(intent)
		}
		return true
	}
}
