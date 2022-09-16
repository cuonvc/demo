package com.example.demo.event;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Function;

@Configuration
@Slf4j
public class MachineProductMapEvent {

    @Autowired
    private StudentService studentService;

//    @Bean
//    public Function<Message<Student>, String> reduceMachineProductMap() {
//        log.info("Received Order and try to reduce product map");
//        return (message) -> {
//            studentService.getStudent(message.getPayload().getFullName());
//            return "test return message from consumer";
//        };
//    }
}
