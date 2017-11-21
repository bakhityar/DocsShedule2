package com.teamtreehouse;

import java.util.Arrays;

public class AppDataParser {

    public static void main(String[] args) {
	String[][] arr = {
            {"Alla", "11.00-15.00", "10.00-12.00", "8.00-15.00", "12.00-19.00"},
            {"Bella", "10.00-12.00", "10.00-12.00", "", ""},
            {"Alla", "10.00-14.00", "13.00-18.00", "10.00-12.00", ""},
            {"Cilla", "10.00-12.00", "10.00-12.00", "", ""},
            {"Bella", "10.00-12.00", "10.00-12.00", "", ""}
        };
	//Создаем массив длинною в исходный массив
	String[][] res = new String[arr.length][0];

	//Первую запись полностью копируем в новый массив
	res[0] = arr[0].clone();

	int res_length = 1; //Количество записей в новом массиве
        boolean yes = false;

        //Переменной name присваиваем имя врача следующей записи
	for (int i=1; i<arr.length; i++) {
	  String name = arr[i][0];
	  //Проверяем есть ли новом массиве данный врач
	  for(int j=0; j<res_length; j++) {
	    yes = res[j][0].contains(name);
	    //Врач есть, корректируем его расписание
            if ((yes)) {
              for(int k=1; k<arr[0].length; k++)
              {
                if (arr[i][k].equals("") || res[j][k].equals("")){
                  res[j][k] = arr[i][k] + res[j][k];
                  break;
                } else {
                  double begin_new = Double.parseDouble(arr[i][k].split("-")[0]);
                  double end_new = Double.parseDouble(arr[i][k].split("-")[1]);
                  double begin_old = Double.parseDouble(res[j][k].split("-")[0]);
                  double end_old = Double.parseDouble(res[j][k].split("-")[1]);


                  //Усоловия по объединению временных промежутков
                  if (begin_new > end_old) {
                    res[j][k] = String.format("***%s, %s****", res[j][k], arr[i][k]);
                  } else if (begin_new <= begin_old && end_new >= end_old) {
                    res[j][k] = String.format("***%.2f-%.2f**", begin_new, end_new);
                  } else if (begin_new > begin_old && end_new < end_old) {
                    res[j][k] = String.format("***%.2f-%.2f***", begin_old, end_old);
                  } else if (begin_new <= begin_old && end_new < end_old) {
                    res[j][k] = String.format("***%.2f-%.2f**", begin_new, end_old);
                  } else if (begin_new > begin_old && end_new >= end_old) {
                    res[j][k] = String.format("***%.2f-%.2f**", begin_old, end_new);
                  } else res[j][k] = String.format("***%s, %s**", res[j][k], arr[i][k]);
                }

              }

              System.out.printf("%s yes in spisok %n", name);
              break;
            }
          }
          if (!yes) {
            res[res_length] = arr[i].clone();
            res_length++;
          }
        }
      System.out.println(Arrays.deepToString(res));
    }
}
