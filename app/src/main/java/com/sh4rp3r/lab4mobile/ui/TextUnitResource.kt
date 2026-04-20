package com.sh4rp3r.lab4mobile.ui

import androidx.annotation.DimenRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit

@Composable
fun textUnitResource(@DimenRes id: Int): TextUnit {
    val resources = LocalContext.current.resources
    val px = resources.getDimension(id)
    return with(LocalDensity.current) {
        px.toSp()
    }
}
