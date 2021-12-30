package com.github.adon92.ideaswitchpinyin.configurable

import com.intellij.openapi.options.Configurable
import ui.SwitchPinyinConfigureUI
import javax.swing.JComponent

class SwitchPinyinConfigure : Configurable {

    var settingPanel: SwitchPinyinConfigureUI? = null
    override fun createComponent(): JComponent? {
        settingPanel = SwitchPinyinConfigureUI()
        return settingPanel!!.create()
    }

    override fun isModified(): Boolean {
        return false
    }

    override fun apply() {
        TODO("Not yet implemented")
    }

    override fun getDisplayName(): String {
        return "Auto Switch Input Method"
    }
}
