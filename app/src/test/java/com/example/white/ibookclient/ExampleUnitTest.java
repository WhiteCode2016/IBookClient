package com.example.white.ibookclient;

import com.example.white.ibookclient.http.HttpDataManager;
import com.example.white.ibookclient.http.HttpIBook;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void httpTest() {
        HttpIBook httpIBook = new HttpIBook();
        System.out.println(httpIBook.getHttpAllIBookInfo());
    }

    @Test
    public void jsonTest() {
        HttpIBook httpIBook = new HttpIBook();
        System.out.println(HttpDataManager.getIBookInfos(httpIBook.getHttpAllIBookInfo()));
    }
}