package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.tictactoe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private ActivityMainBinding binding;
    private String name1st;
    private String name2nd;
    private static boolean checkPlayersNum = true;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.mainToolbar);
        getSupportActionBar().setTitle("TicTacToe");
        getSupportActionBar().setSubtitle("ver 1.0");


        binding.startBTN.setOnClickListener(v -> {
            name1st = binding.name1stET.getText().toString();
            name2nd = binding.name2ndET.getText().toString();
            if (checkPlayersNum) {
                if (name1st.isEmpty() || name2nd.isEmpty()) {
                    Toast.makeText(this, "ENTER NAME", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("name1st", name1st);
                    intent.putExtra("name2nd", name2nd);
                    intent.putExtra("checkPlayersNum", checkPlayersNum);
                    startActivity(intent);
                }
            } else {
                if (name1st.isEmpty()) {
                    Toast.makeText(this, "ENTER NAME", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("name1st", name1st);
                    intent.putExtra("checkPlayersNum", checkPlayersNum);
                    startActivity(intent);
                }
            }
        });

        binding.modeToggleBTN.setOnCheckedChangeListener(this);
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            checkPlayersNum = false;
            binding.name2ndET.setText("");
            binding.name2ndET.setHint("");
            binding.name2ndET.setBackgroundColor(android.R.color.transparent);
            binding.name2ndET.setEnabled(false);
            binding.iconPersonIV.setImageResource(R.drawable.one_person);
        } else {
            checkPlayersNum = true;
            binding.name2ndET.setHint("ENTER NAME 2ND PLAYER");
            binding.name2ndET.setTextColor(ContextCompat.getColor(this, R.color.black));
            binding.name2ndET.setBackgroundColor(ContextCompat.getColor(this, R.color.yellowWhite));
            binding.name2ndET.setEnabled(true);
            binding.iconPersonIV.setImageResource(R.drawable.two_person);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.exit) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}