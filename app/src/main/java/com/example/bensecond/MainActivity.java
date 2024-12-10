package com.example.bensecond;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnDate;
    Button btnSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnDate = findViewById(R.id.dateBtn);
        btnSelect = findViewById(R.id.selectBtn);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                                String selectedDate = selectedDay + "/" + (selectedMonth+1) + "/" + selectedYear;
                                Toast.makeText(MainActivity.this, "Date: " + selectedDate, Toast.LENGTH_SHORT).show();
                            }
                        },
                        year,
                        month,
                        day
                );
                datePickerDialog.show();
            }
       });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] options = {"Hi", "Hello", "Howdy", "YO!"};
                boolean[] selectedItems = {false, false, false, false}; // Tracks selected items

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select Options")
                        .setMultiChoiceItems(options, selectedItems, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                selectedItems[which] = isChecked; // Update the selection
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Collect selected options
                                StringBuilder selectedOptions = new StringBuilder("Selected: ");
                                for (int i = 0; i < options.length; i++) {
                                    if (selectedItems[i]) {
                                        selectedOptions.append(options[i]).append(", ");
                                    }
                                }
                                // Remove trailing comma and space
                                if (selectedOptions.length() > 9) {
                                    selectedOptions.setLength(selectedOptions.length() - 2);
                                } else {
                                    selectedOptions.append("None");
                                }

                                // Show the selected options
                                Toast.makeText(MainActivity.this, selectedOptions.toString(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", null);

                builder.create().show();
            }
        });
    }
}