package com.rasol.training001;

import com.rasol.training001.controller.userController.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CreateUserTests.class,
        GetBookDetailTests.class,
        GetBooksTests.class,
        GetHistoryTests.class,
        GetPopularKeywordsTests.class,
        LoginUserTests.class
})
public class Training001ApplicationTests {

    @Test
    public void contextLoads() {
    }

}
