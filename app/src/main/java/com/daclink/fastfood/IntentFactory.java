package com.daclink.fastfood;

import android.content.Context;
import android.content.Intent;

public class IntentFactory {

    public static Intent newMainActivityIntent(Context context){
        return new Intent(context, MainActivity.class);
    }

    public static Intent newLoginActivityIntent(Context context){
        return new Intent(context, LoginActivity.class);
    }

    public static Intent newLandingPageIntent(Context context){
        return new Intent(context, LandingPage.class);
    }

    public static Intent newLandingPageIntent(Context context, String username){
        Intent intent = new Intent(context, LandingPage.class);
        intent.putExtra("USERNAME_KEY", username);
        return intent;
    }

}
