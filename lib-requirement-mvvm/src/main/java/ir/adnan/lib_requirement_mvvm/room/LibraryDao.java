package ir.adnan.lib_requirement_mvvm.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Adnan on 8/28/2017.
 */

@Dao
public abstract interface LibraryDao {


//    @Insert(onConflict = REPLACE)
//    void addData (Class <T>)
}
