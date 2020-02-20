package com.example.cs5610spring2020springbootsiyanhe.services;

import com.example.cs5610spring2020springbootsiyanhe.models.Widget;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class WidgetService {
  private static WidgetService service = null;
  private List<Widget> widgetList;

  private WidgetService() {
    this.widgetList = new ArrayList<>();
  }

  public static WidgetService getInstance() {
    if (service == null) {
      service = new WidgetService();
    }
    return service;
  }

  /**
   * Create a new Widget instance and add it to the existing collection of widgets for a topic whose
   * ID is tid. Return new widget with a unique identifier.
   */
  public Widget createWidget(String tid, Widget widget) {
    widget.setId(UUID.randomUUID().toString());
    widget.setTopicId(tid);
    widgetList.add(widget);
    return widget;
  }

  /**
   * Return collection of all widgets for a topic whose ID is tid.
   */
  public List<Widget> findWidgetsForTopic(String tid) {
    return widgetList.stream().filter(w -> w.getTopicId().equals(tid)).collect(Collectors.toList());
  }

  /**
   * Update widget whose id is wid encoded as JSON in HTTP body. Returns 1 if successful, 0
   * otherwise.
   */
  public int updateWidget(String wid, Widget widget) {
    for (int i = 0; i < widgetList.size(); i++) {
      if (widgetList.get(i).getId().equals(wid)) {
        widgetList.set(i, widget);
        return 1;
      }
    }
    return 0;
  }

  /**
   * Remove widget whose id is wid. Returns 1 if successful, 0 otherwise.
   */
  public int deleteWidget(String wid) {
    List<Widget> newWidgetList = widgetList.stream()
            .filter(w -> !w.getId().equals(wid)).collect(Collectors.toList());

    if (newWidgetList.size() < widgetList.size()) {
      widgetList = newWidgetList;
      return 1;
    }
    return 0;
  }

  /**
   * Returns collection of all widgets.
   */
  public List<Widget> findAllWidgets() {
    return widgetList;
  }

  /**
   * Return a single widget instance whose id is equal to wid.
   */
  private Widget findWidgetById(String wid) {
    for (Widget w : widgetList) {
      if (w.getId().equals(wid)) {
        return w;
      }
    }
    return null;
  }
}
