package com.daclink.fastfood;

import static org.junit.Assert.assertEquals;

import android.os.Looper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;
import org.robolectric.Shadows;
import org.mockito.Mock;

import androidx.lifecycle.MutableLiveData;

import org.junit.Rule;
import org.mockito.Mockito;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.daclink.fastfood.Database.entities.User;

import java.util.Collections;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {34})
public class CreateAccountActivityTest {

    private CreateAccountActivity activity;

    @Mock
    private CreateAccountViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        activity = Robolectric.buildActivity(CreateAccountActivity.class).create().start().resume().visible().get();
        activity.createAccountViewModel = viewModel;

        MutableLiveData<List<User>> defaultLiveData = new MutableLiveData<>();
        defaultLiveData.setValue(Collections.emptyList()); // Assume no user exists by default
        Mockito.when(viewModel.checkUsernameExists(Mockito.anyString())).thenReturn(defaultLiveData);

        Shadows.shadowOf(Looper.getMainLooper()).idle();
    }

    @Test
    public void testUsernameFieldNotEmpty() {
        activity.binding.EnterUserNameEditText.setText("");
        activity.binding.EnterPasswordEditText.setText("password");
        activity.binding.SubmitButton.performClick();
        assertEquals("Please fill out all the fields.", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testPasswordFieldNotEmpty() {
        activity.binding.EnterUserNameEditText.setText("username");
        activity.binding.EnterPasswordEditText.setText("");
        activity.binding.SubmitButton.performClick();
        assertEquals("Please fill out all the fields.", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testUsernameAlreadyTaken() {
        MutableLiveData<List<User>> userExistsLiveData = new MutableLiveData<>();
        userExistsLiveData.setValue(Collections.singletonList(new User("user1", "password", "user")));

        Mockito.when(viewModel.checkUsernameExists("user1")).thenReturn(userExistsLiveData);

        activity.binding.EnterUserNameEditText.setText("user1");
        activity.binding.EnterPasswordEditText.setText("password");
        activity.binding.SubmitButton.performClick();

        assertEquals("Username already taken. Pick a different Username.", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testSuccessfulAccountCreation() {
        MutableLiveData<List<User>> emptyUserLiveData = new MutableLiveData<>();
        emptyUserLiveData.setValue(Collections.emptyList());

        Mockito.when(viewModel.checkUsernameExists("newuser123")).thenReturn(emptyUserLiveData);

        activity.binding.EnterUserNameEditText.setText("newuser123");
        activity.binding.EnterPasswordEditText.setText("password123");
        activity.binding.SubmitButton.performClick();

        assertEquals("Account created successfully!", ShadowToast.getTextOfLatestToast());
    }

}
