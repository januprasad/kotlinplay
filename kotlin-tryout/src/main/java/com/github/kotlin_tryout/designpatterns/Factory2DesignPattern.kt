package com.github.kotlin_tryout.designpatterns

sealed class Dialog {
    object ChatDialog : Dialog()

    object TextDialog : Dialog()

    object ConfirmationDialog : Dialog()
}

object DialogFactory {
    fun createDialog(type: DialogType): Dialog =
        when (type) {
            DialogType.Chat -> Dialog.ChatDialog
            DialogType.Text -> Dialog.TextDialog
            DialogType.Confirmation -> Dialog.ConfirmationDialog
        }
}

enum class DialogType {
    Chat,
    Text,
    Confirmation,
}

fun main() {
    DialogFactory.createDialog(DialogType.Chat)
}
