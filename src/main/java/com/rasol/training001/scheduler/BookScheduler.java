package com.rasol.training001.scheduler;

import com.rasol.training001.constant.Constants;
import com.rasol.training001.repository.BookRepository;
import com.rasol.training001.util.TimeUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BookScheduler {
    private final BookRepository bookRepository;

    public BookScheduler(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Scheduled(fixedDelay = 3600000)
    public void scheduleFixedRateTask() {
        String cachingTimeOver = TimeUtils.getCurrentISO8601MilliPlusTimeMinusMilli(Constants.BOOK_CACHING_TIME);
        bookRepository.deleteAll(bookRepository.findAllByModifiedDateBefore(cachingTimeOver));
    }
}
