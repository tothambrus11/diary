package hu.ambrusweb11.diary;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public static PostDao posts;
    public static LocalDB db;
    private RecyclerView postListRV;
    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    setTitle(new SimpleDateFormat("yyyy.MM.dd.", Locale.getDefault()).format(new Date()));
                });
            }
        }, 0, 60000);

        findViewById(R.id.newPost).setOnClickListener(v -> {
            // TODO start the edit activity with the extras:  title, content, date, id = ""
            posts.insert(new Post((String) getTitle(), "Ez a bejegyzés szövege.. s  dada dasd a  dg sdgsdg ds dfgfds gsdfgdfgs dfsg sf gfgs", "Ez a bejegyzés címe"));
            postAdapter.setPosts(posts.fetchAll());
        });
        postListRV = findViewById(R.id.postListRV);

        db = Room.databaseBuilder(getApplicationContext(), LocalDB.class, "local_db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        posts = db.postsDao();

        List<Post> postList = posts.fetchAll();

        postAdapter = new PostAdapter(postList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        postListRV.setLayoutManager(layoutManager);
        postListRV.setAdapter(postAdapter);
        postListRV.setItemAnimator(new DefaultItemAnimator());
        Log.d("DIARY", postList.toString());
    }

}
