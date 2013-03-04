package iocikun.juj.lojban.gismu_thesaurus

import _root_.android.os.Bundle
import _root_.android.preference.PreferenceActivity
import _root_.android.content.SharedPreferences

import _root_.android.util.Log

class Preference extends PreferenceActivity
	with SharedPreferences.OnSharedPreferenceChangeListener {

	override def onCreate(savedInstanceState: Bundle) {
		Log.d("LojbanGismuThesaurus", "Preference.onCreate start ")
		super.onCreate(savedInstanceState)
		addPreferencesFromResource(R.xml.pref)
	}

	override def onResume {
		super.onResume
		getPreferenceScreen.getSharedPreferences.
			registerOnSharedPreferenceChangeListener(this)
	}

	override def onSharedPreferenceChanged(
		sharedPreferences: SharedPreferences, key: String) {
// //		Etc19.handler.post(Etc19.runnable);
	}

}
