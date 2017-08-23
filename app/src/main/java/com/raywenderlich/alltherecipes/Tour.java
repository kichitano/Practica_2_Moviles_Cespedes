/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.raywenderlich.alltherecipes;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class Tour {

  public String titulo;
  public String descripcion;
  public String imagen;
  public String Url;
  public String persona;
  public String dias;
  public String precio;

  public static ArrayList<Tour> getToursFromFile(String filename, Context context){
    final ArrayList<Tour> tourList = new ArrayList<>();

    try {
      // Load data
      String jsonString = loadJsonFromAsset("tours.json", context);
      JSONObject json = new JSONObject(jsonString);
      JSONArray tours = json.getJSONArray("tours");

      // Get Tour objects from data
      for(int i = 0; i < tours.length(); i++){
        Tour tour = new Tour();

        tour.titulo = tours.getJSONObject(i).getString("titulo");
        tour.descripcion = tours.getJSONObject(i).getString("descripcion");
        tour.imagen = tours.getJSONObject(i).getString("imagen");
        tour.Url = tours.getJSONObject(i).getString("url");
        tour.persona = tours.getJSONObject(i).getString("personas");
        tour.dias = tours.getJSONObject(i).getString("dias");
        tour.precio = tours.getJSONObject(i).getString("precio");

        tourList.add(tour);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return tourList;
  }

  private static String loadJsonFromAsset(String filename, Context context) {
    String json = null;

    try {
      InputStream is = context.getAssets().open(filename);
      int size = is.available();
      byte[] buffer = new byte[size];
      is.read(buffer);
      is.close();
      json = new String(buffer, "UTF-8");
    }
    catch (java.io.IOException ex) {
      ex.printStackTrace();
      return null;
    }

    return json;
  }

}
