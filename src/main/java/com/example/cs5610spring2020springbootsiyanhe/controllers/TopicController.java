package com.example.cs5610spring2020springbootsiyanhe.controllers;

import com.example.cs5610spring2020springbootsiyanhe.models.Topic;
import com.example.cs5610spring2020springbootsiyanhe.services.TopicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TopicController {
  @Autowired
  TopicService service;

  @PostMapping("/api/lessons/{lid}/topics")
  public Topic createTopic(@PathVariable("lid") String lid, @RequestBody Topic topic) {
    return service.createTopic(lid, topic);
  }

  @GetMapping("/api/lessons/{lid}/topics")
  public List<Topic> findTopicsForLesson(@PathVariable("lid") String lid) {
    return service.findTopicsForLesson(lid);
  }

  @PutMapping("/api/topics/{tid}")
  public int updateTopic(@PathVariable("tid") int tid, @RequestBody Topic topic) {
    return service.updateTopic(tid, topic);
  }

  @DeleteMapping("/api/topics/{tid}")
  public int deleteTopic(@PathVariable("tid") int tid) {
    return service.deleteTopic(tid);
  }


  @GetMapping("/api/topics")
  public List<Topic> findAllTopics() {
    return service.findAllTopics();
  }

  @GetMapping("/api/topics/{tid}")
  public Topic findTopicById(@PathVariable("tid") int tid) {
    return service.findTopicById(tid);
  }
}
