package com.thoughtworks.leanengine.infra.conf.dbunit;

import static org.awaitility.Awaitility.await;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.collections.CollectionUtils;
import org.apache.groovy.util.Maps;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.DefaultTableIterator;
import org.dbunit.dataset.DefaultTableMetaData;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableIterator;
import org.dbunit.dataset.ITableMetaData;
import org.dbunit.dataset.RowOutOfBoundsException;
import org.dbunit.dataset.datatype.DataType;
import org.ho.yaml.Yaml;
import org.springframework.util.StringUtils;

@SuppressWarnings({"unchecked", "deprecation"})
public class YamlDataSet implements IDataSet {

  private Map<String, MyTable> tables = new HashMap<>();
  private static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd 00:00:00";
  private static final String DATETIME_PREFIX = "now";
  private static final Predicate<String> DATE_PATTERN_PREDICATE =
      Pattern.compile("^now([+-]\\d+[yMdHms])?(\\(.+\\))?$").asPredicate();
  private static final Pattern DATE_FORMAT_PATTERN = Pattern.compile("(\\((.+)\\))");
  private static final Pattern DATE_OFFSET_PATTERN = Pattern.compile("([+-]\\d+)([yMdHms])");
  private static final String EMPTY_STRING = "";

  public YamlDataSet(File file) throws FileNotFoundException {
    Map<String, List<Map<String, Object>>> data =
        (Map<String, List<Map<String, Object>>>) Yaml.load(file);
    for (Map.Entry<String, List<Map<String, Object>>> ent : data.entrySet()) {
      String tableName = ent.getKey();
      List<Map<String, Object>> rows = ent.getValue();
      parseDate(rows);
      createTable(tableName, rows);
    }
  }

  public ITable getTable(String tableName) {
    return tables.get(tableName);
  }

  public ITableMetaData getTableMetaData(String tableName) {
    return tables.get(tableName).getTableMetaData();
  }

  public String[] getTableNames() {
    return tables.keySet().toArray(new String[tables.size()]);
  }

  public ITable[] getTables() {
    return tables.values().toArray(new ITable[tables.size()]);
  }

  public ITableIterator iterator() {
    return new DefaultTableIterator(getTables());
  }

  public ITableIterator reverseIterator() {
    return new DefaultTableIterator(getTables(), true);
  }

  @Override
  public boolean isCaseSensitiveTableNames() {
    return false;
  }

  MyTable createTable(String name, List<Map<String, Object>> rows) {
    MyTable table = buildTable(name, rows);
    tables.put(name, table);
    return table;
  }

  private void parseDate(List<Map<String, Object>> rows) {

    if (CollectionUtils.isEmpty(rows)) {
      return;
    }
    for (Map<String, Object> row : rows) {
      Set<Map.Entry<String, Object>> entries = row.entrySet();
      for (Map.Entry<String, Object> field : entries) {
        Object valueObj = field.getValue();
        if (Objects.nonNull(valueObj)) {
          String value = String.valueOf(valueObj);
          value = parseYamlDateTime(value);
          row.put(field.getKey(), value);
        }
      }
    }
  }

  public static String parseYamlDateTime(String yamlStr) {
    if (StringUtils.isEmpty(yamlStr)) {
      return yamlStr;
    }
    if (DATE_PATTERN_PREDICATE.test(yamlStr)) {
      return parseToDateTimeStr(yamlStr);
    } else {
      return yamlStr;
    }
  }

  private static String parseToDateTimeStr(String dateStr) {
    if (LocalTime.now().isAfter(LocalTime.MAX.minusSeconds(5))) {
      try {
        await().wait(5000L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    String offsetAndFormatStr = dateStr.replaceFirst(DATETIME_PREFIX, EMPTY_STRING);
    String dateFormat = DEFAULT_TIME_FORMAT;
    String offsetStr = offsetAndFormatStr;

    // handle the date format
    Matcher dateFormatMatcher = DATE_FORMAT_PATTERN.matcher(dateStr);
    if (dateFormatMatcher.find()) {
      dateFormat = dateFormatMatcher.group(2);
      offsetStr = dateStr.replaceFirst(dateFormatMatcher.group(1), "");
    }

    // handle the offset num and offset unit
    Matcher offsetNumberMatcher = DATE_OFFSET_PATTERN.matcher(offsetStr);
    int offsetNum = 0;
    String offsetUnit = null;
    if (offsetNumberMatcher.find()) {
      offsetNum = Integer.parseInt(offsetNumberMatcher.group(1));
      offsetUnit = offsetNumberMatcher.group(2);
    }

    Map<String, LocalDateTime> dateUnitMap = getDurationUnitDateTimeMap(offsetNum);
    return Optional.ofNullable(dateUnitMap.get(offsetUnit))
        .orElse(LocalDateTime.now())
        .format(DateTimeFormatter.ofPattern(dateFormat));
  }

  private static Map<String, LocalDateTime> getDurationUnitDateTimeMap(int offsetDuration) {
    return Maps.of(
        "y", LocalDateTime.now().plus(offsetDuration, ChronoUnit.YEARS),
        "M", LocalDateTime.now().plus(offsetDuration, ChronoUnit.MONTHS),
        "d", LocalDateTime.now().plus(offsetDuration, ChronoUnit.DAYS),
        "H", LocalDateTime.now().plus(offsetDuration, ChronoUnit.HOURS),
        "m", LocalDateTime.now().plus(offsetDuration, ChronoUnit.MINUTES),
        "s", LocalDateTime.now().plus(offsetDuration, ChronoUnit.SECONDS));
  }

  private MyTable buildTable(String name, List<Map<String, Object>> rows) {
    if (Objects.isNull(rows)) {
      return new MyTable(name, new ArrayList<>());
    }
    MyTable table = new MyTable(name, rows.size() > 0 ? new ArrayList(rows.get(0).keySet()) : null);
    for (Map values : rows) {
      table.addRow(values);
    }
    return table;
  }

  class MyTable implements ITable {
    private String name;
    private List<Map> data;
    private ITableMetaData meta;

    MyTable(String name, List<String> columnNames) {
      this.name = name;
      this.data = new ArrayList<>();
      meta = createMeta(name, columnNames);
    }

    public int getRowCount() {
      return data.size();
    }

    public ITableMetaData getTableMetaData() {
      return meta;
    }

    public Object getValue(int row, String column) throws DataSetException {
      if (data.size() <= row) {
        throw new RowOutOfBoundsException("" + row);
      }
      return data.get(row).get(column.toUpperCase());
    }

    public void addRow(Map values) {
      data.add(convertMap(values));
    }

    ITableMetaData createMeta(String name, List<String> columnNames) {
      Column[] columns = null;
      if (columnNames != null) {
        columns = new Column[columnNames.size()];
        for (int i = 0; i < columnNames.size(); i++) {
          columns[i] = new Column(columnNames.get(i), DataType.UNKNOWN);
        }
      }
      return new DefaultTableMetaData(name, columns);
    }

    Map convertMap(Map<String, Object> values) {
      Map ret = new HashMap();
      for (Map.Entry<String, Object> ent : values.entrySet()) {
        ret.put(ent.getKey().toUpperCase(), ent.getValue());
      }
      return ret;
    }
  }
}
