/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 *   
 */

package com.microsoft.device.display.samples.intentsecondscreen

import android.content.Intent
import android.net.Uri
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        second_activity_button.setOnClickListener { startIntentSecondActivity() }
        link_button.setOnClickListener {
            startIntentBrowserApp(resources.getString(R.string.web_link))
        }
        map_button.setOnClickListener {
            startIntentBrowserApp(resources.getString(R.string.map_link))
        }
    }

    /**
     * {@link Intent.FLAG_ACTIVITY_MULTIPLE_TASK} along with android:launchMode="singleInstance"
     * is required to launch a second activity on a second display while still keeping the first
     * activity on the first display(not pausing/stopping it).
     *
     * When using these flags, the activities will have separate tasks. This means that
     * FirstActivity is launched in screen 1 (left) and second activity is launched in
     * screen 2 (right). Then, if you launch other activities from second activity you should
     * not use MULTIPLE_TASK anymore if you want the other activities to be on second
     * screen(the same task as second activity is).
     *
     * You should use {@link Intent.FLAG_ACTIVITY_MULTIPLE_TASK} along with
     * android:launchMode="singleInstance" or for multi-screen purpose (to have 2 separate tasks.
     * one for first screen and one for second screen).
     *
     * You should also use {@link Intent.FLAG_ACTIVITY_SINGLE_TOP} with #MULTIPLE_TASK and
     * "singleInstance" as below if you want to make sure that you don't have multiple instances
     * of the same activity on different tasks open.
     *
     */
    private fun startIntentSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        // Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT is required to launch a second activity
        // on a second display while still keeping the first activity on the first display
        // (not pausing/stopping it)
        intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun startIntentBrowserApp(urlString: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
        intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
