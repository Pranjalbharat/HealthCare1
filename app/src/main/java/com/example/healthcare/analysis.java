package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class analysis extends AppCompatActivity {

    private Button Yes;
    private Button No;
    private TextView Question;
    private Boolean Response;
    private int i=0;

    private String Question_current;
    private int Health_Score=10;

    private String Trauma_Array[] = {"anxiety","Flashbacks","nightmares", "avoidance of triggers", "feeling numb or disconnected", "difficulty sleeping or concentrating"};
    private int Trauma = 0;

    private String Depression_Array[] = {"Fatigue","Sleep disturbances","Changes in appetite or weight","Decreased interest or pleasure in activities","Feelings of worthlessness or guilt","Difficulty concentrating or making decisions","Recurrent thoughts of death or suicide"};
    private int Depression = 0;

    private String Cancer_Array[] = {"Persistent cough or hoarseness" ,"Persistent changes in bowel or bladder habits" ,"Persistent indigestion or discomfort after eating" ,"Unexplained weight loss","Unusual bleeding or discharge","Pain or discomfort that does not go away" ,"Changes in the appearance of a mole or wart"};
    private int Cancer = 0;

    private String Viral_Array[] = {"Fever" ,"Headache" ,"Muscle aches and pains" ,"Fatigue" ,"Cough" ,"Sore throat" ,"Runny or stuffy nose" ,"Body or joint aches" ,"Vomiting or diarrhea"};
    private int Viral = 0;

    private String Asthma_Array[] = {"Shortness of breath or wheezing Chest tightness or pain" ,"Chronic cough" ,"Feeling tired or weak during exercise or physical activity" ,"Rapid heartbeat" ,"Difficulty sleeping due to coughing", "wheezing", "or shortness of breath"};
    private int Asthma = 0;

    private String Diarrhoea_Array[] = {"Frequent loose", "watery stools","Abdominal cramps and pain" ,"Nausea and vomiting" ,"Bloating and gas" ,"Urgent need to have a bowel movement" ,"Fever" ,"Dehydration (if diarrhea is severe or prolonged)"};
    private int Diarrhoea = 0;

    private String Diabetes_Array[] = {"Increased thirst and urination" ,"Blurred vision" ,"Slow-healing sores or cuts" ,"Tingling or numbness in hands or feet"};
    private int Diabetes = 0;

    private String Tuberculosis_Array[] = {"Persistent cough" ,"Fever" ,"Night sweats" ,"Loss of appetite" ,"Unintentional weight loss"};
    private int Tuberculosis = 0;

    private String Malaria_Array[] = {"High fever (often with shaking chills)","Headache" ,"Sweating" ,"Fatigue or weakness" ,"Muscle or joint pain" ,"Nausea and vomiting" ,"Diarrhea" ,"Abdominal pain" ,"Anemia (low red blood cell count)"};
    private int Malaria = 0;

    private String Artheries_Array[] = {"Joint pain and stiffness", "which is often worse in the morning or after periods of inactivity" ,"Swelling and tenderness in the joints" ,"Reduced range of motion in the affected joints" ,"Warmth and redness around the joints" ,"Fatigue" ,"Fever" ,"Weight loss"};
    private int Artheries = 0;

    private String questions[] = {"is Your Mood Off?","Are you experiencing any physical symptoms such as pain, fever, cough, or fatigue?","Are you experiencing persistent feelings of sadness, hopelessness, or emptiness that interfere with your daily life?"};
    private Boolean response_array_1[] = new Boolean[10];
    private Boolean response_array_2[] = new Boolean[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        Question = findViewById(R.id.question_text);
        Question.setText(questions[i]);

        Yes = findViewById(R.id.my_button);
        No = findViewById(R.id.my_button2);


        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response = true;
                analyse(Response);
            }
        });
        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response = false;
                analyse(Response);
            }
        });


    }

    void analyse(Boolean Response) {
        response_array_1[i]= Response;
        Question = findViewById(R.id.question_text);
        if(Response){
            Health_Score--;
        }

        if(i==3){
            Question.setText(".....");
            i=0;


            // mood factor
            if(response_array_1[0]){
                Health_Score--;
            }
            else{
                Health_Score++;
            }


            if(response_array_1[1]==response_array_1[2]){
                // mixture of physical and mental illness
            }

            if(response_array_1[1]!=response_array_1[2]){
                if(response_array_1[1]){
                    //physical illness
                    physical_illness();
                }
                else{
                    //mental illness
                    mental_illness();
                }
            }


        }


        i++;
        Question.setText(questions[i]);
    }

    void physical_illness(){
        Question = findViewById(R.id.question_text);
        Question.setText(Viral_Array[i]);
    }

    void mental_illness(){
        Question = findViewById(R.id.question_text);
        Question.setText(Trauma_Array[i]);
//
//        Yes = findViewById(R.id.my_button);
//        No = findViewById(R.id.my_button2);
//        Yes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                response_array_2[i] = true;
//            }
//        });
//        No.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                response_array_2[i] = true;
//            }
//        });



    }
}