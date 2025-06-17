package org.example.shorturl.utils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

public class TelegramAppender extends AppenderBase<ILoggingEvent> {

    private static final String botToken = "7910391557:AAHkyvD4ZIOIJr1i88yzMRG6cMsvSOeIkaM";

    private static final String chatID = "518471586";
    private TelegramBot telegramBot;



    @Override
    public void start() {
        if (botToken == null || chatID == null) {
            addError("TelegramAppender: botToken yoki chatID null");
            return;
        }
        this.telegramBot = new TelegramBot(botToken);
        super.start();
    }

    public TelegramAppender() {
        addFilter(new Filter<ILoggingEvent>() {
            @Override
            public FilterReply decide(ILoggingEvent event) {
                return event.getLevel().isGreaterOrEqual(Level.ERROR) ? FilterReply.ACCEPT : FilterReply.DENY;
            }
        });
    }

    @Override
    protected void append(ILoggingEvent event) {
        if (telegramBot == null) return;

        String message = "*ERROR Log:*\n\n" +
                "`" + event.getFormattedMessage() + "`";

        SendMessage sendMessage = new SendMessage(chatID, message)
                .parseMode(ParseMode.Markdown);

        telegramBot.execute(sendMessage);
    }
}