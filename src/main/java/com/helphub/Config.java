package com.helphub;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
  private static final String CONFIG_FILE = "config.properties";

  private static int BASE_SCREEN_WIDTH = 1600;
  private static int BASE_SCREEN_HEIGHT = 900;
  public static int FPS = 60;

  // Variables de configuración
  public static int volume;
  public static boolean debugMode;
  public static boolean showFPS;
  public static int screenWidth;
  public static int screenHeight;

  // Guardar configuraciones
  public static void save() {
    Properties properties = new Properties();

    // Colocar las propiedades en el mapa y guardarlas como cadenas
    properties.setProperty("volume", String.valueOf(volume));
    properties.setProperty("debugMode", String.valueOf(debugMode));
    properties.setProperty("showFPS", String.valueOf(showFPS));
//    properties.setProperty("maxFPS", String.valueOf(FPS));
    properties.setProperty("screenWidth", String.valueOf(screenWidth));
    properties.setProperty("screenHeight", String.valueOf(screenHeight));

    try (FileOutputStream output = new FileOutputStream(CONFIG_FILE)) {
      properties.store(output, null);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Cargar configuraciones
  public static void load() {
    Properties properties = new Properties();

    try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
      properties.load(input);
    } catch (IOException e) {
      e.printStackTrace();
    }

    volume = Integer.parseInt(properties.getProperty("volume", "80"));
    debugMode = Boolean.parseBoolean(properties.getProperty("debugMode", "false"));
    showFPS = Boolean.parseBoolean(properties.getProperty("showFPS", "false"));
//    FPS = Integer.parseInt(properties.getProperty("maxFPS", "60"));
    screenWidth = Integer.parseInt(properties.getProperty("screenWidth", "1280"));
    screenHeight = Integer.parseInt(properties.getProperty("screenHeight", "720"));

    save();
  }

  public static String getResolution() {
    return String.format("%sx%s", screenWidth, screenHeight);
  }

  public static void setResolution(String resolutionString) {
    String width = resolutionString.split("x")[0];
    String height = resolutionString.split("x")[1];
    Config.screenWidth = Integer.parseInt(width);
    Config.screenHeight = Integer.parseInt(height);
  }

  public static int scaleByX(int value) {
    double scaleX = (double) screenWidth / BASE_SCREEN_WIDTH;
    return (int) (value * scaleX);
  }

  public static int scaleByY(int value) {
    double scaleY = (double) screenHeight / BASE_SCREEN_HEIGHT;
    return (int) (value * scaleY);
  }
}
