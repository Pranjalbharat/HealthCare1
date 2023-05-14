package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.concurrent.Phaser;

public class analysis extends AppCompatActivity {

    private Button Yes;
    private Button No;
    private TextView Question;
    private Boolean Response;
    private int i=0;
    private int counter=0;
    private int phase=1;
    private int illness=1;

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
    private Boolean answers[] = new Boolean[10];
    private String symptoms[] = new String[10];
    private int symptoms_index = 0;

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
                counter++;
                Response=true;
                if(counter>10){
                    ResultOut(checkResult(true));
                }
                if(phase==1){
                    saveAnswer();
                    displayNextQuestion();
                }
                if(phase==2){
                    displayMentalIllness();
                }
                if(phase==3){
                    displayPhysicalIllness();
                }
                if(checkResult(false)!=null){
                    ResultOut(checkResult(false));
                }
            }
        });
        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                Response=false;
                if(counter>10){
                    ResultOut(checkResult(true));
                }
                if(phase==1){
                    saveAnswer();
                    displayNextQuestion();
                }
                if(phase==2){
                    displayMentalIllness();
                }
                if(phase==3){
                    displayPhysicalIllness();
                }
                if(checkResult(false)!=null){
                    ResultOut(checkResult(false));
                }
            }
        });


    }


    private void ResultOut(String result_illness) {

        Intent intent = new Intent(getApplicationContext(), Report.class);
        intent.putExtra("report",result_illness);
        intent.putExtra("symptoms",setSymptomString());
        startActivity(intent);
        finish();
    }

    private String setSymptomString() {
        String pass_result="";
        for (int j = 0; j < symptoms.length; j++) {
            if(symptoms[j]==null){
                break;
            }
            else{
                pass_result = pass_result+symptoms[j]+"\n";
            }
        }
        return pass_result;
    }


    private String checkResult(boolean endTest) {
        int max =-1;
        if(endTest){
            int arr[] = {Asthma,Diarrhoea,Artheries,Diabetes,Tuberculosis,Cancer,Malaria,Viral,Depression,Trauma};
            Arrays.sort(arr);
            max = arr[9];
        }
        if(Trauma>3 || Trauma == max){
            return "Trauma";
        }
        if(Depression>3 || Depression==max){
            return "Depression";
        }
        if(Viral>3 || Viral==max){
            return "Viral";
        }
        if(Malaria>3||Malaria==max){
            return "Malaria";
        }
        if(Cancer>3 || Cancer==max){
            return "Cancer";
        }
        if(Tuberculosis>3 || Tuberculosis==max){
            return "Tuberculosis";
        }
        if(Diabetes>3 || Diabetes==max){
            return "Diabetes";
        }
        if(Artheries>3 || Artheries==max){
            return "Artheries";
        }
        if(Asthma>3 || Asthma == max){
            return "Asthma";
        }
        if(Diarrhoea>3 || Diarrhoea==max){
            return "Diarrheoa";
        }
        return null;
    }

    private void displayQuestion() {
        Question.setText(questions[i]);
    }

    private void displayMentalIllness() {
        if(illness == 1){
            if(Depression<Depression_Array.length){
                Question.setText(Depression_Array[Depression]);
                String mew = Depression+"";


            }
            else{

            }
        }
        else if(illness == 2){
            if(Trauma<Trauma_Array.length){
                Question.setText(Trauma_Array[Trauma]);
                String mew = Trauma+"";

            }
            else{

            }
        }
        else{
            illness=0;
            phase = 3;
        }
        savesymptom();
    }

    private void savesymptom() {
        Boolean answer = Response;
        if(answer){
            if(illness==1){
                symptoms[symptoms_index] = Depression_Array[Depression];
                symptoms_index++;
                Depression++;
            }
            if(illness==2){
                symptoms[symptoms_index] = Trauma_Array[Trauma];
                symptoms_index++;
                Trauma++;
            }
        }
        else{
            illness++;
        }
    }



    private void displayPhysicalIllness() {
        if(illness == 1){
            if(Viral<Viral_Array.length){
                Question.setText(Viral_Array[Viral]);
                String mew = Viral+"";


            }
            else{

            }
        }
        else if(illness ==2 ){
            if(Malaria<Malaria_Array.length){
                Question.setText(Malaria_Array[Malaria]);
                String mew = Malaria+"";

            }
            else{

            }
        }
        else if(illness ==3 ){
            if(Diarrhoea<Diarrhoea_Array.length){
                Question.setText(Diarrhoea_Array[Diarrhoea]);
                String mew = Diarrhoea+"";

            }
            else{

            }
        }
        else if(illness ==4 ){
            if(Asthma<Asthma_Array.length){
                Question.setText(Asthma_Array[Asthma]);
                String mew = Asthma+"";

            }
            else{

            }
        }
        else if(illness ==5 ){
            if(Tuberculosis<Tuberculosis_Array.length){
                Question.setText(Tuberculosis_Array[Tuberculosis]);
                String mew = Tuberculosis+"";

            }
            else{

            }
        }
        else if(illness ==6 ){
            if(Diabetes<Diabetes_Array.length){
                Question.setText(Diabetes_Array[Diabetes]);
                String mew = Diabetes+"";

            }
            else{

            }
        }
        else if(illness ==7 ){
            if(Cancer<Cancer_Array.length){
                Question.setText(Cancer_Array[Cancer]);
                String mew = Cancer+"";

            }
            else{

            }
        }
        else if(illness == 8){
            if(Artheries<Artheries_Array.length){
                Question.setText(Artheries_Array[Artheries]);
                String mew = Artheries+"";

            }
            else{

            }
        }
        else{

        }
        savesymptom_physical();

    }

    private void savesymptom_physical() {
        Boolean answer = Response;
        Toast.makeText(this,answer+"",Toast.LENGTH_SHORT).show();
        if(answer){
            if(illness==1){
                symptoms[symptoms_index] = Viral_Array[Viral];
                symptoms_index++;
                Viral++;
            }
            if(illness==2){
                symptoms[symptoms_index] = Malaria_Array[Malaria];
                symptoms_index++;
                Malaria++;
            }
            if(illness==3){
                symptoms[symptoms_index] = Diarrhoea_Array[Diarrhoea];
                symptoms_index++;
                Diarrhoea++;
            }
            if(illness==4){
                symptoms[symptoms_index] = Asthma_Array[Asthma];
                symptoms_index++;
                Asthma++;
            }
            if(illness==5){
                symptoms[symptoms_index] = Tuberculosis_Array[Tuberculosis];
                symptoms_index++;
                Tuberculosis++;
            }
            if(illness==6){
                symptoms[symptoms_index] = Diabetes_Array[Diabetes];
                symptoms_index++;
                Diabetes++;
            }
            if(illness==7){
                symptoms[symptoms_index] = Cancer_Array[Cancer];
                symptoms_index++;
                Cancer++;
            }
            if(illness==8){
                symptoms[symptoms_index] = Artheries_Array[Artheries];
                symptoms_index++;
                Artheries++;
            }
        }
        else{

            illness++;
        }
    }


    private void saveAnswer() {
        Boolean answer = Response;
        answers[i] = answer;
    }
    private void displayNextQuestion() {
        if (i < questions.length - 1) {
            i++;
            displayQuestion();
        } else {
            // All questions have been answered, do something with the answers here
            Question.setText("...");

            setPhase();
        }
    }

    private void setPhase() {
        if(!answers[1] && answers[2]){
            phase = 2; // mental_illness
        }
        if(answers[1] && !answers[2]){
            phase = 3; // mental_illness
        }
        if(answers[1] == answers[2]){
            phase = 2; // mental_illness
        }
    }
}