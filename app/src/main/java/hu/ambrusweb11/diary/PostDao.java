package hu.ambrusweb11.diary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PostDao {
    @Insert
    void insert(Post post);

    @Query("DELETE FROM posts WHERE id = :id")
    void delete(int id);

    @Update
    void update(Post post);

    @Query("SELECT * FROM posts ORDER BY date DESC")
    List<Post> fetchAll();
}
