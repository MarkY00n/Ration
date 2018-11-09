package com.ration.rationstudy.mark;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ration.rationstudy.R;
import com.ration.rationstudy.mark.common.BaseActivity;
import com.ration.rationstudy.mark.common.MarkAdapter;
import com.ration.rationstudy.mark.common.MarkViewHolder;
import com.ration.rationstudy.mark.data.Chapter;
import com.ration.rationstudy.mark.data.Subject;

import java.util.ArrayList;

public class MarkMainActivity extends BaseActivity {

    private RecyclerView rv_chapters;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_main);
        findViews();
        init();
    }

    @Override
    public void findViews() {
        rv_chapters = (RecyclerView) findViewById(R.id.rv_chapters);
    }

    @Override
    public void init() {
        rv_chapters.setLayoutManager(new LinearLayoutManager(this));
        ChapterAdapter chapterAdapter = new ChapterAdapter(this, R.layout.mark_list_chap);
        chapterAdapter.setItemList(getChapters());
        rv_chapters.setAdapter(chapterAdapter);
    }

    public ArrayList<Subject> getChapters() {
        ArrayList<Subject> chapters = new ArrayList<>();

        chapters.add(new Subject(Chapter.Chapter01));
        chapters.add(new Subject(Chapter.Chapter02));
        chapters.add(new Subject(Chapter.Chapter03));

        return chapters;
    }


    class ChapterAdapter extends MarkAdapter<Subject, ChapterViewHolder> {

        public ChapterAdapter(Context context, int resId) {
            super(context, resId);
        }

        @Override
        public ChapterViewHolder viewHolder(View view) {
            return new ChapterViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ChapterViewHolder chapterViewHolder, int i) {
            chapterViewHolder.bindTo(getContext(), getArrayList().get(i));
        }
    }

    class ChapterViewHolder extends MarkViewHolder<Subject> {

        private TextView tvChapter;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChapter = (TextView) itemView.findViewById(R.id.tvChapter);
        }

        @Override
        public void bindTo(Context context, Subject subject) {
            tvChapter.setText(subject.getChapter().getTitle());
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, subject.getClassCode());
                context.startActivity(intent);
            });
        }
    }

}
