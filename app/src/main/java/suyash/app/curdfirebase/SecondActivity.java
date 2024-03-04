package suyash.app.curdfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class SecondActivity extends AppCompatActivity {

    EditText txtField;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtField=findViewById(R.id.txtField);
        saveBtn=findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt=txtField.getText().toString();

                if(txt.isEmpty()){
                    txtField.setError("Fill the field");
                    return;
                }else {
                    addDBData(txt);
                }
            }
        });
    }
    void addDBData(String data){
        HashMap<String, Object> hashMap=new HashMap<>();
        hashMap.put("Text",data);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference txtRef=database.getReference("Data");

        String key=txtRef.push().getKey();
        hashMap.put("key",key);

        txtRef.child(key).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(SecondActivity.this, "Added", Toast.LENGTH_SHORT).show();
                txtField.getText().clear();
            }
        });
    }
}