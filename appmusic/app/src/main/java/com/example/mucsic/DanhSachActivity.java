package com.example.mucsic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DanhSachActivity extends AppCompatActivity {
    RecyclerView rvDanhSach;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach);

        cardView = findViewById(R.id.cardView);

        // Initializing list view with the custom adapter
        final ArrayList<Song> itemList = new ArrayList<Song>();

        ItemArrayAdapter itemArrayAdapter = new ItemArrayAdapter(R.layout.item_music, itemList);
        rvDanhSach = findViewById(R.id.my_recycler_view);
        rvDanhSach.setLayoutManager(new LinearLayoutManager(this));
        rvDanhSach.setItemAnimator(new DefaultItemAnimator());
        rvDanhSach.setAdapter(itemArrayAdapter);

        itemList.add(new Song("Baby - Guy gon", R.raw.didi));
        itemList.add(new Song("Em gái mưa - Hương Tràm", R.raw.emgaimua));
        itemList.add(new Song("Đi đi đi", R.raw.didi));
        itemList.add(new Song("Ngày hạnh phúc - Băng Cường ", R.raw.ngayhanhphuc));
        itemList.add(new Song("Người lạ ơi - Sơn Rịck ", R.raw.nguoilaoi));
        itemList.add(new Song("Tâm sự với người lạ - Tiên Coki ", R.raw.tamsuvoinguoila));
        itemList.add(new Song("Yêu 5 - Physnatis", R.raw.yeu5));
        itemList.add(new Song("Em gái mưa - Hương Tràm", R.raw.emgaimua));
        itemList.add(new Song("Đi đi đi", R.raw.didi));
        itemList.add(new Song("Ngày hạnh phúc - Băng Cường ", R.raw.ngayhanhphuc));
        itemList.add(new Song("Người lạ ơi - Sơn Rịck ", R.raw.nguoilaoi));
        itemList.add(new Song("Tâm sự với người lạ - Tiên Coki ", R.raw.tamsuvoinguoila));
        itemList.add(new Song("Yêu 5 - Physnatis", R.raw.yeu5));


    }
}
