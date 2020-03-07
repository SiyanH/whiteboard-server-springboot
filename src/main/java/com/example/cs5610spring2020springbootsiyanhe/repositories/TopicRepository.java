package com.example.cs5610spring2020springbootsiyanhe.repositories;

import com.example.cs5610spring2020springbootsiyanhe.models.Topic;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
  @Query("SELECT topic FROM Topic topic")
  List<Topic> findAllTopics();

  @Query("SELECT topic FROM Topic topic where topic.lessonId=:lid")
  List<Topic> findTopicsForLesson(@Param("lid") String lessonId);

  @Query("SELECT topic FROM Topic topic where topic.id=:tid")
  Topic findTopicById(@Param("tid") int topicId);
}
