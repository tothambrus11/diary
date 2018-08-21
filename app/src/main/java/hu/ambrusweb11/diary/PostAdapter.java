package hu.ambrusweb11.diary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_POST = 1;
    private List<Post> posts;
    private Context context;
    private LayoutInflater inflater;

    public PostAdapter(List<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new HeaderHolder(inflater.inflate(R.layout.list_header, parent, false));
        } else if (viewType == TYPE_POST) {
            return new PostHolder(inflater.inflate(R.layout.postlist_item, parent, false));
        } else {
            throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder_, int position) {
        if (holder_ instanceof HeaderHolder) {
            HeaderHolder holder = (HeaderHolder) holder_;
        } else if (holder_ instanceof PostHolder) {
            position--;
            PostHolder postHolder = (PostHolder) holder_;

            final Post currentPost = posts.get(position);

            postHolder.title.setText(currentPost.getTitle());
            postHolder.date.setText(currentPost.getDate());
            postHolder.content.setText(currentPost.getContent());

            postHolder.editButton.setOnClickListener((View v) -> {
                // TODO create edit post activity and start an intent
                Toast.makeText(context, "TODO Edit post" + currentPost.getId(), Toast.LENGTH_SHORT).show();
            });
            postHolder.deleteButton.setOnClickListener((View v) -> {
                new AlertDialog.Builder(context)
                        .setTitle("Delete post")
                        .setMessage(context.getString(R.string.confirm_delete) + " '" + currentPost.getTitle() + "'?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                            MainActivity.posts.delete(currentPost.getId());
                            setPosts(MainActivity.posts.fetchAll());
                        })
                        .setNegativeButton(android.R.string.no, null).show();

            });
        }
    }

    @Override
    public int getItemCount() {
        return posts.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_POST;
    }

    private class HeaderHolder extends RecyclerView.ViewHolder {

        public HeaderHolder(@NonNull View itemView) {
            super(itemView);
        }

    }

    private class PostHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView date;
        private TextView content;
        private Button deleteButton;
        private Button editButton;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.postListPostTitle);
            date = itemView.findViewById(R.id.postListPostDate);
            content = itemView.findViewById(R.id.postListPostContent);
            deleteButton = itemView.findViewById(R.id.removePostButton);
            editButton = itemView.findViewById(R.id.editPostButton);

        }

    }
}
