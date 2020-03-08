package com.example.cs5610spring2020springbootsiyanhe.repositories;

import com.example.cs5610spring2020springbootsiyanhe.models.Widget;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {
  @Query("SELECT widget FROM Widget widget")
  List<Widget> findAllWidgets();

  @Query("SELECT widget FROM Widget widget WHERE widget.topic.id=:tid ORDER BY widget.order ASC")
  List<Widget> findWidgetsForTopic(@Param("tid") int topicId);

  @Query("SELECT widget FROM Widget widget WHERE widget.id=:wid")
  Widget findWidgetById(@Param("wid") int widgetId);
}
