package moe.protasis.tomlyn.util;

import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@UtilityClass
public class Util {
    public static String ReadToStringAutoClose(InputStream is) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String ret = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            is.close();
            return ret;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static CompletableFuture<Void> RunAsync(Runnable func) {
        return CompletableFuture.runAsync(() -> {
            try {
                func.run();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });
    }
}