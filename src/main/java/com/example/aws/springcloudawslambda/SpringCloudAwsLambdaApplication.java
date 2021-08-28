package com.example.aws.springcloudawslambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.example.aws.springcloudawslambda.entity.Student;
import com.example.aws.springcloudawslambda.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class SpringCloudAwsLambdaApplication {

	@Autowired
	private StudentRepository studentRepository;

	@Bean
	public Supplier<List<Student>> students(){
		return ()->studentRepository.studentList();
	}

	@Bean
	public Function<String, List<Student>> findByName(){
		return (input)->studentRepository.studentList().stream().
				filter(student -> student.getName().equals(input)).collect(Collectors.toList());
	}

	@Bean
	public MyConsumer myConsumerBean(){
		return new MyConsumer();
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringCloudAwsLambdaApplication.class, args);
	}
}
