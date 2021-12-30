package com.github.adon92.ideaswitchpinyin.services

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.event.CaretEvent
import com.intellij.openapi.editor.event.CaretListener
import com.intellij.openapi.editor.event.DocumentEvent
import com.intellij.openapi.editor.event.DocumentListener
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.PsiEditorUtil
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.PsiUtilBase
import com.intellij.psi.util.elementType
import ime.InputMethodUtil

class MyProjectService(project: Project) {

    var enableStr: Boolean = false;

    init {

        EditorFactory.getInstance().eventMulticaster.addCaretListener(object : CaretListener {
            override fun caretPositionChanged(event: CaretEvent) {
                checkCaret(event.editor)
            }
        }, project)
        EditorFactory.getInstance().eventMulticaster.addDocumentListener(object : DocumentListener {
            override fun beforeDocumentChange(event: DocumentEvent) {
                println("sdfsdfsdf")
            }
        }, project)

    }

    private fun checkCaret(editor: Editor) {
        val caretModel = editor.caretModel
        val primaryCaret = caretModel.primaryCaret
        val offset = primaryCaret.offset
        val file = PsiEditorUtil.getPsiFile(editor)
        val element = PsiUtilBase.getElementAtOffset(file, offset - 1)
        if (element is PsiComment && !InputMethodUtil.checkKeyboardLayout(InputMethodUtil.ZH)) {
            println("comment")
            InputMethodUtil.switchChinese()
        } else if (element.elementType!!.debugName.equals("STRING_LITERAL") && enableStr && !InputMethodUtil.checkKeyboardLayout(InputMethodUtil.ZH)) {
            println("string")
            InputMethodUtil.switchChinese()
        } else {
            println("code")
            if (InputMethodUtil.checkKeyboardLayout(InputMethodUtil.EN)) return
            InputMethodUtil.switchEnglish()
        }

    }
}
