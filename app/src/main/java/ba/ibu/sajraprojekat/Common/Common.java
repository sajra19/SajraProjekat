package ba.ibu.sajraprojekat.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import ba.ibu.sajraprojekat.Model.Food;
import ba.ibu.sajraprojekat.Model.Request;
import ba.ibu.sajraprojekat.Model.User;

public class Common {

    // variable to store current user
    public static User currentUse;
    public static Request currentRequest;
    public static final String DELETE = "Delete";

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null) {
                for (int i=0; i<info.length; i++){
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return false;
    }


}