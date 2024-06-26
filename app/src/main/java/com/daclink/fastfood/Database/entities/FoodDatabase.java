package com.daclink.fastfood.Database.entities;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.time.LocalDateTime;
import java.util.HashMap;
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
                    orderDAO orderDAO = INSTANCE.orderDAO();
                    userDAO.deleteAll();
                    userDAO.insertUser(new User("admin2", "admin2", "admin"));
                    userDAO.insertUser(new User("testUser1", "testUser1", "user"));
                    productDAO.insertProduct(new Product("Milk", "Milky", 5, 5, 2));
                    productDAO.insertProduct(new Product("Eggs", "Eggy", 3, 10, 3));
                    productDAO.insertProduct(new Product("Bacon", "Burnt", 10, 15, 1.5));
                    productDAO.insertProduct(new Product("Cheese", "Cheesy", 3.5, 20, .5));
                    productDAO.insertProduct(new Product("Mountain Dew", "Baja Blast", 2.5, 25, 2));
                    productDAO.insertProduct(new Product("Potatoes", "Poh-ta-to", 2.35, 35, 1.5));
                    productDAO.insertProduct(new Product("Beer", "Modelo", 9.99, 22, 4.5));
                    productDAO.insertProduct(new Product("Orange Juice", "99% Sugar", 3.49, 19, 1.7));
                    productDAO.insertProduct(new Product("Ground Beef", "Moo", 7.99, 24, 1.5));
                    productDAO.insertProduct(new Product("Sausage", "Pork", 6.99, 9, 2.0));
                    productDAO.insertProduct(new Product("Lettuce", "Moldy", 3.99, 32, 1.25));
                    productDAO.insertProduct(new Product("Bread", "Sourdough", 4.99, 22, 1.4));
                    productDAO.insertProduct(new Product("Cereal", "90% Sugar", 5.30, 43, 1.3));
                    productDAO.insertProduct(new Product("Onions", "Red", 1.39, 19, .5));
                    productDAO.insertProduct(new Product("Apples", "Green", 1.49, 17, .5));
                    productDAO.insertProduct(new Product("Mango", "Not ripe", 2.39, 13, .7));
                    productDAO.insertProduct(new Product("Everclear", "clear", 19.99, 27, .9));
                    HashMap<Integer, Integer> productIDs = new HashMap<>();
                    productIDs.put(1, 3);
                    productIDs.put(2, 5);
                    productIDs.put(5, 4);
                    Cart cart = new Cart(1, productIDs);
                    orderDAO.insertOrder(new Order(1, cart, LocalDateTime.now(), 150, 12));
                });
            });
        }
    };

    public abstract userDAO foodDAO();

    public abstract productDAO productDAO();

    public abstract orderDAO orderDAO();
}
