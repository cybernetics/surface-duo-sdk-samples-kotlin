/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 *
 */

package com.microsoft.device.display.samples.draganddrop

import android.app.Application
import com.microsoft.device.dualscreen.layout.SurfaceDuoScreenManager

class DragAndDropApp : Application() {
    lateinit var surfaceDuoScreenManager: SurfaceDuoScreenManager

    override fun onCreate() {
        super.onCreate()
        surfaceDuoScreenManager = SurfaceDuoScreenManager.init(this)
    }
}