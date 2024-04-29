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

    public static Intent newShoppingCartActivityIntent(Context context){
        return new Intent(context, ShoppingCartActivity.class);
    }

    public static Intent newCheckoutPageActivityIntent(Context context){
        return  new Intent(context, CheckoutActivity.class);
    }

    public static Intent newOrderConfirmationActivityIntent(Context context){
        return new Intent(context, OrderConfirmationActivity.class);
    }

    public static Intent newCreateAccountActivityIntent(Context context){
        return new Intent(context, CreateAccountActivity.class);
    }

}
