package com.reiraku.hypertoaster.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.reiraku.hypertoaster.R
import com.reiraku.hypertoaster.utils.AndroidPlatform
import org.jetbrains.compose.ui.tooling.preview.Preview
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Text

@Composable
@Preview
fun InfoCard(platform: AndroidPlatform) {
    val cardPaddingValue = PaddingValues(
        start = 12.dp,
        end = 12.dp
    )
    val rowPaddingValue = PaddingValues(
        horizontal = 18.dp,
        vertical = 20.dp
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(cardPaddingValue)
    ) {
        Column {
            Row {
                BasicComponent(
                    title = stringResource(R.string.string_android_version),
                    summary = platform.androidVersion
                )
            }
            Row {
                BasicComponent(
                    title = stringResource(R.string.string_os_version),
                    summary = platform.osVersion
                )
            }
            Row {
                BasicComponent(
                    title = stringResource(R.string.string_sdk_version),
                    summary = platform.sdkVersion
                )
            }
        }
    }
}