# Mockito Workshop Starter

Стартовый проект для воркшопа **«Mockito в действии: моки, стабы и spy»**.

Основной код приложения уже готов. На воркшопе нужно писать тесты.

## Как устроена практика

Практические тесты лежат в `src/test/java`.

В них уже есть:

- названия тестовых сценариев;
- подключенные аннотации Mockito и Spring Test;
- `TODO`-комментарии на русском языке;
- подсказки, что именно нужно настроить и проверить.

Практические классы помечены `@Disabled`. На воркшопе включайте их по одному:

```java
// Удалите или закомментируйте @Disabled над классом
@Disabled("Практика для воркшопа...")
class RewardIssuingServiceMockitoTest {
}
```

## Карта практики

- `RewardIssuingServiceMockitoTest` - основной Mockito-практикум:
  - lifecycle моков;
  - stub;
  - matchers;
  - `verify`;
  - `ArgumentCaptor`;
  - void-методы и `doThrow`;
  - `InOrder`.
- `RewardCalculatorSpyTest` - практика по `spy`.
- `RewardControllerWebMvcTest` - `@WebMvcTest` и `@MockBean`.
- `BadMockitoExamplesTest` - плохие тесты для обсуждения, намеренно отключены.
- `RefactoredBadMockitoExamplesTest` - заготовка для исправления плохих тестов.

## Запуск

Проверить, что проект компилируется:

```bash
./mvnw test
```

Запустить приложение:

```bash
./mvnw spring-boot:run
```

Проверить API вручную:

```bash
curl -X POST http://localhost:8080/api/rewards/issue \
  -H "Content-Type: application/json" \
  -d '{"customerId":3,"purchaseAmount":8000.00}'
```

## Эталонное решение

Решенная версия находится рядом в проекте:

```text
mockito-workshop-solution
```
