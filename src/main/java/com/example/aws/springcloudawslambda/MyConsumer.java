package com.example.aws.springcloudawslambda;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
public class MyConsumer implements Consumer<String> {

    @Override
    public void accept(String s) {
        log.info("Received:  "+s);
    }
}
