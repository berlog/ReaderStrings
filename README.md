>## Задача:
>>Предположим, существует некая реализация метода `getNextText()` интерфейса `IReader`. При его вызове метод возвращает строки, при этом строки могут дублироваться. В случае отсутствия строк метод возвращает **null**.
Вывести "топ 5" часто встречаемых строк.

>## Решение:
>>Сформировать коллекцию `HashMap<String, Integer>` в качестве ключа которого выступает строка, возвращаемая методом `getNextText()`, а в качестве значения количество повторений этой строки.
Отсортировать коллекцию по значению и вывести первых 5 строк.

```java
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Reader reader = new Reader();
		Map<String, Integer> unsortedMap = getMap(reader);
		//System.out.println(unsortedMap);

		//Sort the collection and display the "top 5" of frequently encountered string
		unsortedMap.entrySet().stream()
				.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
				.limit(5)
				.forEach(e -> System.out.println(e.getKey()));
	}
	
	
	/**
	 * Method returns the collection of strings and their counters
	 * 
	 * @param reader the instance of Reader object for get some next text
	 * @return the map of strings and their counters
	*/
	public static Map<String, Integer> getMap(Reader reader) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		while(true) {
			String str = reader.getNextText();
			
			if (str != null) {
				Integer i = map.get(str);
				if (i != null) {
					++i;
					map.put(str, i);
				} else {
					map.put(str, 1);
				}
			} else {
				break;
			}
		}
		
		return map;
	}
}
```


>## Альтернативное решение:
>>Если есть такая возможность, то можно реализовать это на стороне бекенда, в качестве **SQL запроса**.

>>Предположим у нас есть таблица (user) с полем name:

| name |
| ------ |
| Василий |
| Алим |
| Ким |
| Макар |
| Василий |
| Василий |
| Владимир |
| Алим |
| Владимир |
| Генрих |
| Тагир |
| Василий |
| Генрих |
| Василий |
| Владимир |
| Ким |
| Генрих |
| Генрих |
| Владимир |
| Василий |
| Ким |
| Василий |
| Владимир |


```sql
--sqlite code 
SELECT name, count(name) AS count_user
FROM user
GROUP BY name
ORDER BY count(name) DESC LIMIT 5
```

### Результат запроса:

| name | count_user |
| ------ | ------ |
| Василий | 7 |
| Владимир | 5 |
| Генрих | 4 |
| Ким | 3 |
| Алим | 2 |


P.S. Все необходимые файлы, в том числе [sqlite база](https://github.com/berlog/ReaderStrings/blob/master/db_user.db), а так же полный исходный код программы распаложен в корне [проекта](https://github.com/berlog/ReaderStrings/) на github.

P.S.S. Для просмотра [исходной базы](https://github.com/berlog/ReaderStrings/blob/master/db_user.db) **sqlite** и выполнение скриптов можно воспользоваться 
[DB Browser for SQLite](https://sqlitebrowser.org/)
