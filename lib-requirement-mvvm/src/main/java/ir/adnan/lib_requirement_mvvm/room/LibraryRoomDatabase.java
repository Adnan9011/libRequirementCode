package ir.adnan.lib_requirement_mvvm.room;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Adnan on 8/28/2017.
 */

public abstract class LibraryRoomDatabase extends RoomDatabase {
    protected static LibraryRoomDatabase INSTANCE;

    public static LibraryRoomDatabase getDatabase(Context context, String nameOfDatabase) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(
                            context.getApplicationContext(),
                            LibraryRoomDatabase.class,
                            nameOfDatabase
                    )
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    /* @TODO : Add Dao Interface Like This :
    public abstract BorrowModelDao itemAndPersonModel();
    */
}
