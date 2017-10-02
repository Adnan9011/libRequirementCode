package ir.baselibrary

import android.os.Bundle

import ir.adnan.lib_requirement_code.core.Log
import ir.adnan.lib_requirement_code.core.Static
import ir.adnan.lib_requirement_code.main.LibraryActivity

class MainActivity : LibraryActivity() {

    companion object {

        private val ID_FRAGMENT = R.id.fragment
        private val ID_TOOLBAR = R.id.toolbar
        private val ID_TOOLBAR_TITLE = R.id.toolbar_title
        private val ID_IMAGE_NAVIGATION = R.id.toolbar_navigation
        private val ID_TOOLBAR_IMAGE = R.id.toolbar_menu
        private val ID_DRAWER = R.id.drawer_layout
        private val ID_RETRY_NETWORK = R.id.retry_network
        private val ID_COORDINATE_LAYOUT = R.id.coordinator_layout
    }

    private val TAG = "MainActivity"
    private val THIS = this
    private val THIS_ACTIVITY = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        launchPushe(THIS)
        launchFirebaseAnalytics(THIS)
        setView(THIS_ACTIVITY, ID_FRAGMENT, ID_TOOLBAR, ID_TOOLBAR_TITLE, ID_IMAGE_NAVIGATION, ID_TOOLBAR_IMAGE, ID_DRAWER, ID_RETRY_NETWORK, ID_COORDINATE_LAYOUT)
        /*
         Write Your Code
         */
        testRetrofit()
        testSnackBar()
    }

    /*
     * Test
     */
    private fun testSnackBar() {
        Static.snackbarWithAction(THIS, libraryActivityView.coordinateLayout, "تست اسنک بار با اکشن ")
    }

    private fun testRetrofit() {
        Log.e(TAG, "Test Retrofit")
    }
}
