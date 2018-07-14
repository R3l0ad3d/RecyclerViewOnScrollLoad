package asthq.net.recyclerviewonscrollload;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MmainActivity extends AppCompatActivity{

    private RecyclerView rvList;
    private RvAdapter adapter;

    private List<String> list;

    private int visibleItemCount,totalItemCount,pastVisiblesItems;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        rvList = findViewById(R.id.rvList);
        adapter = new RvAdapter(this,list);

        final RecyclerView.LayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvList.setLayoutManager(manager);
        rvList.setAdapter(adapter);

        rvList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = manager.getChildCount();
                    totalItemCount = manager.getItemCount();
                    pastVisiblesItems = ((LinearLayoutManager) manager).findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        setData(20);
                        Log.d("TAG", "onScrolled: "+visibleItemCount+" + "+pastVisiblesItems+" = "+totalItemCount);
                    }

                }
            }
        });

        rvList.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {

            }
        });
        setData(50);
    }

    private void setData(int x) {
        for (int i=0;i<x;i++){
            list.add("Joy "+i);
        }
        adapter.notifyDataSetChanged();
    }
}
