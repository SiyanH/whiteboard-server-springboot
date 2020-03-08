package com.example.cs5610spring2020springbootsiyanhe.services;

import com.example.cs5610spring2020springbootsiyanhe.models.Topic;
import com.example.cs5610spring2020springbootsiyanhe.repositories.TopicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
  @Autowired
  TopicRepository topicRepository;

  /**
   * Create a new Topic instance and save it to a topics table for a lesson whose ID is lid.
   * Return the new topic inserted in the database.
   */
  public Topic createTopic(String lid, Topic topic) {
    topic.setLessonId(lid);
    return topicRepository.save(topic);
  }

  /**
   * Return collection of all topics for a lesson whose ID is lid.
   */
  public List<Topic> findTopicsForLesson(String lid) {
    return topicRepository.findTopicsForLesson(lid);
  }

  /**
   * Update a topic whose id is tid with the new values in the topic parameter.
   * Return 1 if successful, 0 otherwise.
   */
  public int updateTopic(int tid, Topic topic) {
    if (topicRepository.existsById(tid)) {
      topicRepository.save(topic);
      return 1;
    }
    return 0;
  }

  /**
   * Remove topic whose id is tid. Return 1 if successful, 0 otherwise.
   */
  public int deleteTopic(int tid) {
    try {
      topicRepository.deleteById(tid);
      return 1;
    } catch (Exception e) {
      return 0;
    }
  }

  /**
   * Return collection of all topic.
   */
  public List<Topic> findAllTopics() {
    return topicRepository.findAllTopics();
  }

  /**
   * Return a single topic instance whose id is equal to tid.
   */
  public Topic findTopicById(int tid) {
    return topicRepository.findTopicById(tid);
  }
}
