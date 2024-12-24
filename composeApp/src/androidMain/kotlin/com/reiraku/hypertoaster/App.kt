package com.reiraku.hypertoaster

import android.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.reiraku.hypertoaster.compose.InfoCard
import com.reiraku.hypertoaster.utils.AndroidPlatform
import com.reiraku.hypertoaster.utils.HyperToastUtil
import com.reiraku.hypertoaster.utils.getPlatform
import org.jetbrains.compose.ui.tooling.preview.Preview
import top.yukonga.miuix.kmp.basic.ButtonDefaults
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.LazyColumn
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.basic.TextField
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.extra.SuperArrow
import top.yukonga.miuix.kmp.extra.SuperDialog
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.darkColorScheme
import top.yukonga.miuix.kmp.theme.lightColorScheme
import top.yukonga.miuix.kmp.utils.MiuixPopupUtil.Companion.dismissDialog
import top.yukonga.miuix.kmp.utils.getWindowSize

@Composable
@Preview
fun App() {
    val context = LocalContext.current
    val showDialog = remember { mutableStateOf(false) }
    val dialogTextFieldValue = remember { mutableStateOf("") }
    val duration = remember { mutableStateOf(1000L) }
    val showDialog2 = remember { mutableStateOf(false) }
    val dialogTextFieldValue2 = remember { mutableStateOf("一个额头") }
    MiuixTheme(
        colors = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    ) {
        Scaffold(
            modifier = Modifier.height(getWindowSize().height.dp),
            topBar = {
                TopAppBar(title = "HyperToaster")
            },
            bottomBar = {}
        ) { padding ->
            LazyColumn(
                modifier = Modifier.height(getWindowSize().height.dp).padding(top = 20.dp),
                contentPadding = PaddingValues(top = padding.calculateTopPadding()),
            ) {
                val platform: AndroidPlatform = getPlatform()
                val hyperToastUtil = HyperToastUtil()
                item{
                    SmallTitle(text = stringResource(R.string.string_system_info_subtitle))
                }
                item {
                    InfoCard(platform)
                }

                item {
                    SmallTitle(text = stringResource(R.string.string_strong_toast_subtitle))
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                    ) {
                        Row {
                            SuperArrow(
                                title = stringResource(R.string.string_send_strong_toast_title),
                                summary = stringResource(R.string.string_send_strong_toast_content),
                                onClick = {
                                    hyperToastUtil.showStringToast(
                                        context, dialogTextFieldValue2.value,
                                        Color.rgb(255, 255, 255),
                                        duration.value
                                    )
                                }
                            )
                        }
                        Row {
                            SuperArrow(
                                title = stringResource(R.string.string_strong_toast_random_color_title),
                                summary = stringResource(R.string.string_strong_toast_random_color_content),
                                onClick = {
                                    hyperToastUtil.showStringToast(
                                        context, dialogTextFieldValue2.value,
                                        hyperToastUtil.getRandomColor(),
                                        duration.value
                                    )
                                }
                            )
                        }
                        Row {
                            SuperArrow(
                                title = stringResource(R.string.string_strong_toast_without_text_title),
                                summary = stringResource(R.string.string_strong_toast_without_text_content),
                                onClick = {
                                    hyperToastUtil.showStringToast(
                                        context, "",
                                        hyperToastUtil.getRandomColor(),
                                        duration.value,
                                        true
                                    )
                                }
                            )
                        }
                    }
                }

                item {
                    SmallTitle(text = stringResource(R.string.string_advanced_settings_subtitle))
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                    ) {
                        Row {
                            SuperArrow(
                                title = "显示时长",
                                summary = "更改灵动脑门在屏幕上的显示时间 当前设置: ${duration.value}ms",
                                onClick = {
                                    showDialog.value = true
                                }
                            )
                        }
                        Row {
                            SuperArrow(
                                title = "显示文字",
                                summary = "更改灵动脑门在屏幕上的显示文字 当前设置: ${dialogTextFieldValue2.value}",
                                onClick = {
                                    showDialog2.value = true
                                }
                            )
                        }
                    }
                }


            }

        }
        Dialog(showDialog, dialogTextFieldValue) { newDuration ->
            duration.value = newDuration * 1000 // 更新 duration
        }
        Dialog2(showDialog2, dialogTextFieldValue2)
    }
}


@Composable
fun Dialog(
    showDialog: MutableState<Boolean>,
    dialogTextFieldValue: MutableState<String>,
    onDurationConfirmed: (Long) -> Unit // 添加回调函数来传递新的 duration
) {
    SuperDialog(
        title = "持续时间",
        summary = "灵动额头的停留时间",
        show = showDialog,
        onDismissRequest = {
            dismissDialog(showDialog)
        },
    ) {
        TextField(
            modifier = Modifier.padding(bottom = 16.dp),
            value = dialogTextFieldValue.value,
            maxLines = 1,
            onValueChange = { dialogTextFieldValue.value = it }
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                text = "Cancel",
                onClick = {
                    dismissDialog(showDialog)
                },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(20.dp))
            TextButton(
                text = "Confirm",
                onClick = {
                    // 尝试将输入的文本转换为 Long 类型
                    val newDuration =
                        dialogTextFieldValue.value.toLongOrNull() ?: 1 // 如果转换失败，使用默认值 1000L
                    onDurationConfirmed(newDuration) // 调用回调函数传递新的 duration
                    dismissDialog(showDialog)
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.textButtonColorsPrimary()
            )
        }
    }
}

@Composable
fun Dialog2(
    showDialog: MutableState<Boolean>,
    dialogTextFieldValue: MutableState<String>,
) {
    SuperDialog(
        title = "显示文字",
        show = showDialog,
        onDismissRequest = {
            dismissDialog(showDialog)
        },
    ) {
        TextField(
            modifier = Modifier.padding(bottom = 16.dp),
            value = dialogTextFieldValue.value,
            maxLines = 1,
            onValueChange = { dialogTextFieldValue.value = it }
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                text = "Cancel",
                onClick = {
                    dismissDialog(showDialog)
                },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(20.dp))
            TextButton(
                text = "Confirm",
                onClick = {
                    dialogTextFieldValue.value=dialogTextFieldValue.value
                    dismissDialog(showDialog)
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.textButtonColorsPrimary()
            )
        }
    }
}


