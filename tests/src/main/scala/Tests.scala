package iocikun.juj.lojban.gismu_thesaurus.tests

import iocikun.juj.lojban.gismu_thesaurus._
import junit.framework.Assert._
import _root_.android.test.AndroidTestCase
import _root_.android.test.ActivityInstrumentationTestCase2

class AndroidTests extends AndroidTestCase {
  def testPackageIsCorrect() {
    assertEquals("iocikun.juj.lojban.gismu_thesaurus", getContext.getPackageName)
  }
}

class ActivityTests extends ActivityInstrumentationTestCase2(classOf[LojbanGismuThesaurus]) {
   def testHelloWorldIsShown() {
      val activity = getActivity
      val textview = activity.findView(TR.textview)
      assertEquals(textview.getText, "hello, world!")
    }
}
