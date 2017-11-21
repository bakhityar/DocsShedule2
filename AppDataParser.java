package com.teamtreehouse;

import java.util.Arrays;

public class AppDataParser {

    public static void main(String[] args) {
      //Создаем и инициируем исходный массив
	String[][] data = {
            {"Иванов", "11.00-15.00", "10.00-12.00", "08.00-15.00", "12.00-19.00", "09.00-13.00", "11.00-15.00", "12.00-13.00"},
            {"Петров", "10.00-12.00", "10.00-12.00", "", "", "07.00-11.00", "12.00-14.00", "12.00-16.00"},
            {"Иванов", "10.00-14.00", "13.00-18.00", "10.00-17.00", "", "10.00-12.00", "08.00-15.00", "10.00-11.00"},
            {"Сидоров", "11.00-15.00", "10.00-12.00", "06.00-12.00", "08.00-11.00", "16.00-19.00", "", ""},
            {"Петров", "09.00-12.00", "10.00-12.00", "", "10.00-14.00", "13.00-18.00", "10.00-17.00", "07.00-11.00"}
        };
	//Создаем массив длинною в исходный массив
	String[][] result = new String[data.length][0];

	//Первую запись полностью копируем в новый массив
	result[0] = data[0].clone();

	int result_length = 1; //Количество записей в новом массиве
        boolean yes = false;

        //Переменной name присваиваем имя врача следующей записи
	for (int i = 1; i< data.length; i++) {
	  String name = data[i][0];
	  //Проверяем есть ли новом массиве данный врач
	  for(int j = 0; j< result_length; j++) {
	    yes = result[j][0].contains(name);
	    //Врач есть, корректируем его расписание
            if ((yes)) {
              for(int k = 1; k< data[0].length; k++)
              {   //Если одно из полей пустое, просто присваиваем его без всяких условий
                if (data[i][k].equals("") || result[j][k].equals("")){
                  result[j][k] = data[i][k] + result[j][k];
                } else {
                  //Разделяем строку формата ЧЧ:ММ-ЧЧ:ММ в две строки ЧЧ:ММ и ЧЧ:ММ. Разделитель - черточка
                  String begin_new1 = data[i][k].split("-")[0];
                  String end_new1 = data[i][k].split("-")[1];
                  String begin_old1 = result[j][k].split("-")[0];
                  String end_old1 = result[j][k].split("-")[1];
                  //Проходимся по всем доступным вариантам сравнения
                  if (begin_new1.compareTo(end_old1) > 0) {
                    result[j][k] = String.format("%s %s", result[j][k], data[i][k]);
                  } else if (end_new1.compareTo(begin_old1) < 0) {
                    result[j][k] = String.format("%s %s", data[i][k], result[j][k]);
                  } else if (begin_new1.compareTo(begin_old1) <= 0 && end_new1.compareTo(end_old1) >= 0) {
                    result[j][k] = String.format("%s-%s", begin_new1, end_new1);
                  } else if (begin_new1.compareTo(begin_old1) > 0 && end_new1.compareTo(end_old1) < 0) {
                    result[j][k] = String.format("%s-%s", begin_old1, end_old1);
                  } else if (begin_new1.compareTo(begin_old1) <= 0 && end_new1.compareTo(end_old1) < 0) {
                    result[j][k] = String.format("%s-%s", begin_new1, end_old1);
                  } else if (begin_new1.compareTo(begin_old1) > 0 && end_new1.compareTo(end_old1) >= 0) {
                    result[j][k] = String.format("%s-%s", begin_old1, end_new1);
                  }

                  //Тот же метод, но немного сложнее. Делается путем перевода Строки в переменную Double.
                  // При записи в массив локацию поменял на English, дабы разделитель в Double был точкой а не запятой.

                  /*                  double begin_new = Double.parseDouble(data[i][k].split("-")[0]);
                  double end_new = Double.parseDouble(data[i][k].split("-")[1]);
                  double begin_old = Double.parseDouble(result[j][k].split("-")[0]);
                  double end_old = Double.parseDouble(result[j][k].split("-")[1]);

                  //Усоловия по объединению временных промежутков
                  if (begin_new > end_old) {
                    result[j][k] = String.format("***%s, %s****", result[j][k], data[i][k]);
                  } else if (begin_new <= begin_old && end_new >= end_old) {
                    result[j][k] = String.format(Locale.ENGLISH,"***%.2f-%.2f**", begin_new, end_new);
                  } else if (begin_new > begin_old && end_new < end_old) {
                    result[j][k] = String.format(Locale.ENGLISH,"***%.2f-%.2f***", begin_old, end_old);
                  } else if (begin_new <= begin_old && end_new < end_old) {
                    result[j][k] = String.format(Locale.ENGLISH,"***%.2f-%.2f**", begin_new, end_old);
                  } else if (begin_new > begin_old && end_new >= end_old) {
                    result[j][k] = String.format(Locale.ENGLISH,"***%.2f-%.2f**", begin_old, end_new);
                  } else result[j][k] = String.format("***%s, %s**", result[j][k], data[i][k]);*/
                }
              }
              break;
            }
          }
          //Если записи нет в новом массиве, добавляем ее туда.
          if (!yes) {
            result[result_length] = data[i].clone();
            result_length++;
          }
        }
        //Вывод нового массива в консоль для проверки
        for (String[] row : result) {
          System.out.println(Arrays.toString(row));
        }

    }
}
