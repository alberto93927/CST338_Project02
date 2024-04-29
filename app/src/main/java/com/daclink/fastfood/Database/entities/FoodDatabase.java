package com.daclink.fastfood.Database.entities;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Product.class, Order.class}, version = 1, exportSchema = false)
public abstract class FoodDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "FastFood_Database";
    public static final String userTable = "userTable";
    public static final String productTable = "productTable";
    public static final String orderTable = "orderTable";
    private static volatile FoodDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static FoodDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (FoodDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FoodDatabase.class,
                            DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultValues)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriterExecutor.execute(()-> {
                //User admin = new User("admin2", "admin2", "admin");
                //User testUser1 = new User("testUser1", "testUser1", "user");
                //dao.insert(admin);
                //dao.insert(testUser1);
                Executors.newSingleThreadExecutor().execute(() -> {
                    userDAO userDAO = INSTANCE.foodDAO();
                    productDAO productDAO = INSTANCE.productDAO();
                    userDAO.deleteAll();
                    userDAO.insertUser(new User("admin2", "admin2", "admin"));
                    userDAO.insertUser(new User("testUser1", "testUser1", "user"));
                });
            });
        }
    };

    public abstract userDAO foodDAO();

    public abstract productDAO productDAO();

    public abstract orderDAO orderDAO();
}
