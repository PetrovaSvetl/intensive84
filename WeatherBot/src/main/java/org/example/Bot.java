package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {
    private static final String API_CALL_TEMPLATE = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String API_KEY_TEMPLATE = "key";
    public static ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String getBotUsername() {
        return "botUsername";
    }

    @Override
    public String getBotToken() {
        return "botToken";
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();
        System.out.println(user.getFirstName() + " wrote " + msg.getText());
        var id = user.getId();
        sendText(id, parse(msg.getText())+", "+user.getFirstName());
    }
    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what).build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public String parse(String city) {
       
        String cityEng = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String urlString = API_CALL_TEMPLATE + cityEng + API_KEY_TEMPLATE;
        Map<String, String> responseMap = new HashMap<>();
        try {
            JsonNode mainNode = objectMapper.readTree(new URL(urlString)).get("main");
            JsonNode windNode = objectMapper.readTree(new URL(urlString)).get("wind");
            responseMap.put("температура воздуха", String.format("%.1f",mainNode.get("temp").asDouble()-273.15));
            responseMap.put("давление", mainNode.get("pressure").asText());
            responseMap.put("влажность", mainNode.get("humidity").asText());
            responseMap.put("скорость ветра", windNode.get("speed").asText());
        } catch (JsonProcessingException | MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Joiner.on("; ").withKeyValueSeparator("-").join(responseMap);
    }

}
