package ime;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;

import java.awt.im.InputContext;

public class InputMethodUtil {

    public static final String ZH = "zh";
    public static final String EN = "en";

    public interface MyUser32 extends StdCallLibrary {
        MyUser32 INSTANCE = Native.load("User32", MyUser32.class);

        int LoadKeyboardLayoutA(String id, int flags);
    }

    public static boolean checkKeyboardLayout(String name) {
        String language = InputContext.getInstance().getLocale().getLanguage();
        System.out.println(language);
        return name.equals(language);
    }

    public static void switchEnglish() {
        int hkl = MyUser32.INSTANCE.LoadKeyboardLayoutA("04090409", 80);
        extracted(hkl);

    }

    public static void switchChinese() {
        int hkl = MyUser32.INSTANCE.LoadKeyboardLayoutA("08040804", 80);
        extracted(hkl);

    }

    private static void extracted(int hkl) {
        User32.INSTANCE.PostMessage(User32.INSTANCE.GetForegroundWindow(), 0x0050, new WinDef.WPARAM(), new WinDef.LPARAM(hkl));
    }
}
