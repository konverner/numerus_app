package com.example.numerus;

import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.PopupWindow;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.opencsv.CSVReader;

import java.io.ObjectInputStream;
import java.util.List;
import java.util.Random;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView captionsView;
    private TextView responseView;
    private EditText inputText;
    String fileName = "data_en.csv";

    Random random_generator = new Random();

    CSVReader reader;

    List<String[]> lines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextAppearance(this, R.style.RobotoBoldTextAppearance);

        captionsView = findViewById(R.id.captions);
        responseView = findViewById(R.id.response);
        inputText = findViewById(R.id.edit_text_input);
        Button buttonRepeat = findViewById(R.id.button_repeat);
        Button buttonCheck = findViewById(R.id.button_check);

        // Set click listener on the Toolbar title
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
        
        toolbar.setOverflowIcon(ContextCompat.getDrawable(getApplicationContext(),R.drawable.language));

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        try {
            reader = new CSVReader(new InputStreamReader(getAssets().open(fileName)));
            lines = reader.readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youtubePlayer) {

                int randomIndex = random_generator.nextInt(lines.size());
                String[] currentLine = lines.get(randomIndex);

                if (currentLine == null)
                    onReady(youtubePlayer);

                String videoId = currentLine[1];
                String startTime = currentLine[2];
                String captions = currentLine[3];
                float startTimeFloat = Float.parseFloat(startTime);
                youtubePlayer.loadVideo(videoId, startTimeFloat);
                captionsView.setText(captions);

                buttonRepeat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        youtubePlayer.seekTo(startTimeFloat);
                    }
                });

                buttonCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (buttonCheck.getText().equals("Check")) {
                            captionsView.setVisibility(View.VISIBLE);
                            String inputTextValue = String.valueOf(inputText.getText());

                            String numberPattern = "\\b"+inputTextValue+"\\b";

                            Pattern pattern = Pattern.compile(numberPattern);
                            Matcher matcher = pattern.matcher(captions);

                            if (matcher.find() & !inputTextValue.isEmpty()) {
                                responseView.setTextColor(getResources().getColor(R.color.green));
                                responseView.setText(R.string.correct);
                            }
                            else {
                                responseView.setTextColor(getResources().getColor(R.color.red));
                                responseView.setText(R.string.incorrect);
                            }
                            responseView.setVisibility(View.VISIBLE);
                            buttonCheck.setText(R.string.next);
                        }
                        else{
                            captionsView.setVisibility(View.GONE);
                            responseView.setVisibility(View.GONE);
                            buttonCheck.setText(R.string.check);
                            inputText.setText("");
                            onReady(youtubePlayer);
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void showPopupWindow() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View popupView = inflater.inflate(R.layout.info_popup, null);

        PopupWindow popupWindow = new PopupWindow(popupView, Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(toolbar, 0, 0, Gravity.END);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_english) {
            Toast.makeText(this, "English Language Selected", Toast.LENGTH_SHORT).show();
            fileName = "data_en.csv";
        }

        if (id == R.id.menu_french) {
            Toast.makeText(this, "French Language Selected", Toast.LENGTH_SHORT).show();
            fileName = "data_fr.csv";
        }

        if (id == R.id.menu_russian) {
            Toast.makeText(this, "Russian Language Selected", Toast.LENGTH_SHORT).show();
            fileName = "data_ru.csv";
        }

        if (id == R.id.menu_german) {
            Toast.makeText(this, "German Language Selected", Toast.LENGTH_SHORT).show();
            fileName = "data_de.csv";
        }

        if (id == R.id.menu_spanish) {
            Toast.makeText(this, "Spanish Language Selected", Toast.LENGTH_SHORT).show();
            fileName = "data_es.csv";
        }

        try {
            reader = new CSVReader(new InputStreamReader(getAssets().open(fileName)));
            lines = reader.readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return super.onOptionsItemSelected(item);
    }
}
