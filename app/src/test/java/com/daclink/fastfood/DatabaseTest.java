package com.daclink.fastfood;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.daclink.fastfood.Database.entities.FoodDatabase;
import com.daclink.fastfood.Database.entities.User;
import com.daclink.fastfood.Database.entities.userDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.List;

//Have issues with gradle, these tests are currently failing due to those issues.

@RunWith(JUnit4.class)
public class DatabaseTest {
    private userDAO userDao;
    private FoodDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, FoodDatabase.class).build();
        userDao = db.foodDAO();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void addAndVerifyUser() throws Exception {
        User user = new User("dave", "pass", "user");
        userDao.insertUser(user);
        LiveData<List<User>> byName = userDao.findUserByName("dave");
        assertThat(byName.getValue().get(0).getName(), equalTo(user.getName()));
    }

    @Test
    public void verifyDefaultUser() throws Exception {
        LiveData<List<User>> admin = userDao.findUserByName("admin2");
        assertThat(admin.getValue().get(0).getName(), equalTo("admin2"));
    }
}
