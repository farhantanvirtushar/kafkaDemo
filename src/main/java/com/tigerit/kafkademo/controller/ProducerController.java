package com.tigerit.kafkademo.controller;

import com.tigerit.kafkademo.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @RequestMapping(value = "/produce", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity produce(@RequestBody Message message){
        try {
            kafkaTemplate.send("farhan-topic",message.getBody());
        }catch (Exception e){
            log.debug("Kafka sending exception : {}",e);
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }

        return ResponseEntity.ok("message sent");

    }
}
