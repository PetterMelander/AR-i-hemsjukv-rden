package com.example.android.glass.cardsample.data;


import java.io.Serializable;
import java.util.HashMap;

public class Patient implements Serializable {

    private HashMap<String, String> map = new HashMap();

    private String[] patientData;
    private String[] conditions;
    private String[] checklist;
    private String[] test;

    public Patient(String[] patientData, String[] conditions, String[] checklist, String[] test){
        this.patientData = patientData;
        this.conditions = conditions;
        this.checklist = checklist;
        this.test = test;

        for (int i = 0; i < test.length; i++){
            map.put(test[i], patientData[i]);
        }
    }

    public String getName(){
        return map.get("Name:");
    }

    public String getAge(){
        return map.get("Age:");
    }

    public String get(String key){
        return map.get(key);
    }

    public String getData(){
        StringBuilder stringBuilder = new StringBuilder();

        for (String key: map.keySet()) {
            if (!key.equals("Name:")){
                stringBuilder.append(key + " " + map.get(key) + "\n");
            }
        }
        return stringBuilder.toString();
    }

    public String[] getChecklist(){
        return checklist;
    }

}
