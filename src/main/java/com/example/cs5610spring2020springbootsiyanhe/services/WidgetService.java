package com.example.cs5610spring2020springbootsiyanhe.services;

import com.example.cs5610spring2020springbootsiyanhe.models.Topic;
import com.example.cs5610spring2020springbootsiyanhe.models.Widget;
import com.example.cs5610spring2020springbootsiyanhe.repositories.TopicRepository;
import com.example.cs5610spring2020springbootsiyanhe.repositories.WidgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WidgetService {
  @Autowired
  WidgetRepository widgetRepository;
  @Autowired
  TopicRepository topicRepository;

  /**
   * Create a new Widget instance and save it to a widgets table for a topic whose ID is tid.
   * Return new widget inserted in the database.
   */
  public Widget createWidget(int tid, Widget widget) {
    Topic topic = topicRepository.findTopicById(tid);
    widget.setTopic(topic);
    widget.setOrder(findWidgetsForTopic(tid).size());
    return widgetRepository.save(widget);
  }

  /**
   * Return collection of all widgets for a topic whose ID is tid.
   */
  public List<Widget> findWidgetsForTopic(int tid) {
    return widgetRepository.findWidgetsForTopic(tid);
  }

  /**
   * Update widget whose id is wid encoded as JSON in HTTP body.
   * Return 1 if successful, 0 otherwise.
   */
  public int updateWidget(int wid, Widget widget) {
    Optional<Widget> w = widgetRepository.findById(wid);
    if (w.isPresent()) {
      widget.setTopic(w.get().getTopic());
      widgetRepository.save(widget);
      return 1;
    }
    return 0;
  }

  /**
   * Remove widget whose id is wid. Return 1 if successful, 0 otherwise.
   */
  public int deleteWidget(int wid) {
    Optional<Widget> optionalWidget = widgetRepository.findById(wid);
    if (optionalWidget.isPresent()) {
      Widget widget = optionalWidget.get();

      List<Widget> widgets = widgetRepository.findWidgetsForTopic(widget.getTopic().getId());
      widgets.forEach(w -> {
        if (w.getOrder() > widget.getOrder()) {
          w.setOrder(w.getOrder() - 1);
          widgetRepository.save(w);
        }
      });

      widgetRepository.deleteById(wid);
      return 1;
    }
    return 0;
  }

  /**
   * Return collection of all widgets.
   */
  public List<Widget> findAllWidgets() {
    return widgetRepository.findAllWidgets();
  }

  /**
   * Return a single widget instance whose id is equal to wid.
   */
  public Widget findWidgetById(int wid) {
    return widgetRepository.findWidgetById(wid);
  }

  /**
   * Replace the widget list with the given widget list. Return 1 if successful, 0 otherwise.
   */
  public int saveAllWidgets(List<Widget> widgetList) {
    try {
      widgetRepository.deleteAll();
      widgetRepository.saveAll(widgetList);
      return 1;
    } catch (Exception e) {
      return 0;
    }
  }
}
