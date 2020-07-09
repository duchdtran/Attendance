package ultils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.provider.Settings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.prefs.AppPreferencesHelper;
import data.prefs.PreferencesHelper;

public final class CommonUtils {

    private static final String TAG = "CommonUtils";

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressdialog = new ProgressDialog(context);
        progressdialog.setMessage("Please Wait....");;
        return progressdialog;
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isLogut(Context context){
        PreferencesHelper helper=new AppPreferencesHelper(context);
        String token = helper.getToken();
        return token==null;
    }

}

