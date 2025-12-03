package com.github.kotlin_tryout.intenttypes

fun main() {
    /**
     *
     * This is implicit intent as we don't know the app going to use this intent , use specification
     val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.example.com"))
     startActivity(intent)

     actions -> ACTION_VIEW, ACTION_SEND, ACTION_DIAL.
     */

    /**
     * explicit intent we know the which app/activity going to use
     * val intent = Intent(this, SecondActivity::class.java)
     * startActivity(intent)
     */

    /**
     * fun sendStickyBroadcast(context: Context, message: String) {
     *     val intent = Intent("MY_STICKY_ACTION")
     *     intent.putExtra("message", message)
     *     context.sendStickyBroadcast(intent)
     * }
     *
     * depreciated
     */
}
