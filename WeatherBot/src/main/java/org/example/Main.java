package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    private static final String API_CALL_TEMPLATE = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String API_KEY_TEMPLATE = "&APPID=c5a815e45cdf8cfa1408b6d795d9a082";

    public static void main(String[] args) throws IOException, ParseException, TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        Bot bot = new Bot();
        botsApi.registerBot(new Bot());
    }
}