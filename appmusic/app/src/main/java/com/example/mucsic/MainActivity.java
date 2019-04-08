package com.example.mucsic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvTitle, tvTimeSong, tvTimeTotal;
    SeekBar sbTime;
    ImageView imgHinh;
    ImageButton btnPrevious, btnPlay, btnNext, btnStop, btnDanhSach;
    int position = 0;
    MediaPlayer mediaPlayer;
    Animation animation;

    ArrayList<Song> arraySong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        AddSong();

        animation = AnimationUtils.loadAnimation(this,R.anim.dia_xoay);

        KhoiTaoMedia();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if(position > arraySong.size() - 1){
                    position =0;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                KhoiTaoMedia();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.ic_pause_black_24dp);
                UpdateTimeSong();
                SetTimeTotal();
                imgHinh.startAnimation(animation);
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if(position < 0){
                    position = arraySong.size() - 1;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                KhoiTaoMedia();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.ic_pause_black_24dp);
                SetTimeTotal();
                UpdateTimeSong();
                imgHinh.startAnimation(animation);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                btnPlay.setImageResource(R.drawable.ic_play);
                KhoiTaoMedia();
                SetTimeTotal();
                UpdateTimeSong();
                imgHinh.setAnimation(null);

            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    // neu dang phat =>pause=>dooi hinh
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.ic_play);
                    imgHinh.setAnimation(null);
                }else{
                    //dang ngung => phat=> doi hinh
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.ic_pause_black_24dp);
                    imgHinh.startAnimation(animation);
                }
                SetTimeTotal();
                UpdateTimeSong();
            }
        });

        btnDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ids = new Intent(MainActivity.this,DanhSachActivity.class);
                startActivity(ids);
            }
        });

        sbTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(sbTime.getProgress());
            }
        });
    }

    private void UpdateTimeSong(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                tvTimeSong.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()));
                //update sbTime
                sbTime.setProgress(mediaPlayer.getCurrentPosition());
                //kiem tra tg bài hát nếu hết next
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if(position > arraySong.size() - 1){
                            position =0;
                        }
                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        KhoiTaoMedia();
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.ic_pause_black_24dp);
                        UpdateTimeSong();
                    }
                });

                handler.postDelayed(this, 500);

            }
        },100);
    }
    private void SetTimeTotal(){
        SimpleDateFormat dingDangGio = new SimpleDateFormat("mm:ss");
        tvTimeTotal.setText(dingDangGio.format(mediaPlayer.getDuration()));
        //gan max cuar sbtime = mediaPlayer.getDuration()
        sbTime.setMax(mediaPlayer.getDuration());
    }
    private void KhoiTaoMedia(){
        mediaPlayer = MediaPlayer.create(MainActivity.this, arraySong.get(position).getFile());
        tvTitle.setText(arraySong.get(position).getTitle());

    }

    private void AddSong() {
            arraySong = new ArrayList<>();
            arraySong.add(new Song("Baby - Guy gon", R.raw.lalalala));
            arraySong.add(new Song("Em gái mưa - Hương Tràm", R.raw.emgaimua));
            arraySong.add(new Song("Đi đi đi", R.raw.didi));
            arraySong.add(new Song("Ngày hạnh phúc - Băng Cường ", R.raw.ngayhanhphuc));
            arraySong.add(new Song("Người lạ ơi - Sơn Rịck ", R.raw.nguoilaoi));
            arraySong.add(new Song("Tâm sự với người lạ - Tiên Coki ", R.raw.tamsuvoinguoila));
            arraySong.add(new Song("Yêu 5 - Physnatis", R.raw.yeu5));
            arraySong.add(new Song("Em gái mưa - Hương Tràm", R.raw.emgaimua));
            arraySong.add(new Song("Đi đi đi", R.raw.didi));
            arraySong.add(new Song("Ngày hạnh phúc - Băng Cường ", R.raw.ngayhanhphuc));
            arraySong.add(new Song("Người lạ ơi - Sơn Rịck ", R.raw.nguoilaoi));
            arraySong.add(new Song("Tâm sự với người lạ - Tiên Coki ", R.raw.tamsuvoinguoila));
            arraySong.add(new Song("Yêu 5 - Physnatis", R.raw.yeu5));
    }

    private void AnhXa() {
        tvTitle     = (TextView) findViewById(R.id.tvTitle);
        tvTimeSong  = (TextView) findViewById(R.id.tvTimeSong);
        tvTimeTotal = (TextView) findViewById(R.id.tvTimeTotal);
        sbTime      = (SeekBar) findViewById(R.id.sbTime);
        btnPrevious = (ImageButton) findViewById(R.id.btPrevious);
        btnPlay     = (ImageButton) findViewById(R.id.btPlay);
        btnNext     = (ImageButton) findViewById(R.id.btNext);;
        btnStop     = (ImageButton) findViewById(R.id.btStop);
        imgHinh     = (ImageView) findViewById(R.id.ivDia);
        btnDanhSach    = findViewById(R.id.btdanhSach);

    }
}
