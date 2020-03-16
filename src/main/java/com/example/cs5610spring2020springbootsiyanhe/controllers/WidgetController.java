package com.example.cs5610spring2020springbootsiyanhe.controllers;

import com.example.cs5610spring2020springbootsiyanhe.exceptions.NotFoundException;
import com.example.cs5610spring2020springbootsiyanhe.models.Widget;
import com.example.cs5610spring2020springbootsiyanhe.services.WidgetService;

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
public class WidgetController {
  @Autowired
  WidgetService service;

  @PostMapping("/api/topics/{tid}/widgets")
  public Widget createWidget(@PathVariable("tid") int tid, @RequestBody Widget widget) {
    return service.createWidget(tid, widget);
  }

  @GetMapping("/api/topics/{tid}/widgets")
  public List<Widget> findWidgetsForTopic(@PathVariable("tid") int tid) {
    return service.findWidgetsForTopic(tid);
  }

  @PutMapping("/api/widgets/{wid}")
  public int updateWidget(@PathVariable("wid") int wid, @RequestBody Widget widget) {
    return service.updateWidget(wid, widget);
  }

  @DeleteMapping("/api/widgets/{wid}")
  public int deleteWidget(@PathVariable("wid") int wid) {
    return service.deleteWidget(wid);
  }

  @GetMapping("/api/widgets")
  public List<Widget> findAllWidgets() {
    return service.findAllWidgets();
  }

  @GetMapping("/api/widgets/{wid}")
  public Widget findWidgetById(@PathVariable("wid") int wid) {
    Widget widget = service.findWidgetById(wid);

    if (widget == null) {
      throw new NotFoundException("No widget found");
    }

    return widget;
  }

  @PutMapping("/api/widgets")
  public int saveAllWidgets(@RequestBody List<Widget> widgets) {
    return service.saveAllWidgets(widgets);
  }
}
