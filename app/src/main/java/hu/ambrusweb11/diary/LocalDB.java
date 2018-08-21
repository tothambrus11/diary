package hu.ambrusweb11.diary;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Post.class}, version = 1, exportSchema = false)
public abstract class LocalDB extends RoomDatabase {
    public abstract PostDao postsDao();
}