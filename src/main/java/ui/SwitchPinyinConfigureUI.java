package ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import java.awt.im.InputContext;
import java.util.Locale;

public class SwitchPinyinConfigureUI {

    static final String ASSERTJ_SWING_KEYBOARD_LOCALE = "assertj.swing.keyboard.locale";
    private static Locale locale;
    static {
        String propertyLocale = System.getProperty(ASSERTJ_SWING_KEYBOARD_LOCALE);
        System.out.println("system property >" + ASSERTJ_SWING_KEYBOARD_LOCALE + "<: " + propertyLocale);
        if (propertyLocale != null) {
            locale = Locale.forLanguageTag(propertyLocale);
            System.out.println("using locale from system property: " + locale);
        }
        if (locale == null) {
            locale = InputContext.getInstance().getLocale();
            System.out.println("using locale from input context: " + locale.getLanguage());
        }
        if (locale == null) {
            locale = Locale.getDefault();
            System.out.println("using default locale: " + locale);
        }
        boolean zh_cn = InputContext.getInstance().selectInputMethod(Locale.ENGLISH);
        InputContext.getInstance().setCompositionEnabled(false);
        System.out.println(zh_cn);
    }
    /**
     * 注释
     */
    private JLabel commentLable;
    private JComboBox commentCbx;
    /**
     * 代码
     */
    private JLabel codeLable;
    private JComboBox codeCbx;

    private JPanel settingJPanel;

    public JPanel create() {
        if (settingJPanel != null) {
            settingJPanel.repaint();
            return settingJPanel;
        }

        settingJPanel = new JPanel();
        settingJPanel.setLayout(new GridLayoutManager(3, 2, JBUI.emptyInsets(), -1, -1));
        final Spacer spacer1 = new Spacer();
        settingJPanel.add(spacer1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));

        commentLable = new JLabel("Comment Input Method:");
        codeLable = new JLabel("Coding Input Method:");
        settingJPanel.add(commentLable, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        settingJPanel.add(codeLable, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        commentCbx = new JComboBox();
        codeCbx = new JComboBox();
        return settingJPanel;

    }
}
