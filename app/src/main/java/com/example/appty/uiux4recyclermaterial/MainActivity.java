package com.example.appty.uiux4recyclermaterial;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    static String titles[];
    static TypedArray images;
    ArrayList<LanguageClass> languageClassArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titles = getResources().getStringArray(R.array.Languages);
        images = getResources().obtainTypedArray(R.array.LanguagesImg);

        RecyclerView recyclerView = findViewById(R.id.recycler);

        if (savedInstanceState == null) {
            for (int i = 0; i < titles.length; i++) {
                languageClassArrayList.add(new LanguageClass(titles[i], images.getResourceId(i, 0)));
            }
        }
        else {
            languageClassArrayList = savedInstanceState.getParcelableArrayList("list");
        }

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, span());

        //try disabling this
//        recyclerView.setItemAnimator(new DefaultItemAnimator());


        recyclerView.setLayoutManager(layoutManager);


        final RecyclerView.Adapter adapter = new CardAdapter(languageClassArrayList, this);

        ItemTouchHelper.Callback callbacks = new ItemTouchHelper.Callback() {
            // Try reducing the movement
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.DOWN | ItemTouchHelper.UP
                        | ItemTouchHelper.START | ItemTouchHelper.END);
            }

            // Try applying different logic
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                if (from < to) {
                    for (int i = from; i < to; i++) {
                        Collections.swap(languageClassArrayList, i, i + 1);
                    }

                } else {

                    for (int i = from; i > to; i--) {
                        Collections.swap(languageClassArrayList, i, i- 1);
                    }
                }

                adapter.notifyItemMoved(from, to);

                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callbacks);

        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private int span() {

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

        float cardWidth = getResources().getDimension(R.dimen.cardview);

        return (int) Math.floor(screenWidth / cardWidth);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("list", languageClassArrayList );
    }
}
