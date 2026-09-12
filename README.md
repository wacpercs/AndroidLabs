# Лабораторная работа № 1: Знакомство с Android Studio

Данный репозиторий содержит выполнение всех трёх практических заданий лабораторной работы № 1 «Знакомство с Android Studio».

---

## Структура проектов

| Проект | Папка | Описание |
| :--- | :--- | :--- |
| **First App** | [`FirstApp/`](file:///d:/Antigravity/Android/FirstApp) | Шаблонное приложение с экраном приветствия (`Hello World!`) и базовой разметкой. |
| **LinearLayout** | [`LinearLayout/`](file:///d:/Antigravity/Android/LinearLayout) | Вертикальная линейная компоновка (`LinearLayout`) с тремя компонентами `TextView` (данные студента, четверостишье на русском, четверостишье на английском через строковый ресурс). |
| **Components** | [`Components/`](file:///d:/Antigravity/Android/Components) | Работа с компонентами `EditText`, `ImageView` (дефолтная иконка) и `Button` («ОК») в вертикальном `LinearLayout`. |

---

## 1. Проект "First App" (Часть 1)
- **Цель**: Создание базового проекта из шаблона *Empty Activity*, знакомство со структурой проекта и запуск приложения.
- **Исходный код**:
  - Activity: [`MainActivity.kt`](file:///d:/Antigravity/Android/FirstApp/app/src/main/java/com/example/firstapp/MainActivity.kt)
  - Разметка: [`activity_main.xml`](file:///d:/Antigravity/Android/FirstApp/app/src/main/res/layout/activity_main.xml)
  - Ресурсы: [`strings.xml`](file:///d:/Antigravity/Android/FirstApp/app/src/main/res/values/strings.xml), [`themes.xml`](file:///d:/Antigravity/Android/FirstApp/app/src/main/res/values/themes.xml)
- **Сборка**: APK успешно собран: `FirstApp/app/build/outputs/apk/debug/app-debug.apk`.

---

## 2. Проект "LinearLayout" (Часть 2)
- **Цель**: Освоение линейной компоновки `LinearLayout` с вертикальной ориентацией (`android:orientation="vertical"`) и компонента `TextView`.
- **Выполненные требования**:
  1. Корневой контейнер сконвертирован в `<LinearLayout android:orientation="vertical">`.
  2. **Первый `TextView`**: содержит хардкодную строку с ФИО и группой студента (`Иванов Иван ПИ-9000`).
  3. **Второй `TextView`**: содержит хардкодное четверостишье на русском языке:
     ```
     Если б мишки были пчелами,
     То они бы нипочём
     Никогда и не подумали
     Так высоко строить дом!
     ```
  4. **Третий `TextView`**: четверостишье, переведённое на английский язык, вынесено в строковый ресурс `res/values/strings.xml` (`@string/bears_poem_en`):
     ```xml
     <string name="bears_poem_en">If bears were bees\nThen they would not care\nNever thought\nBuilding a house so high!</string>
     ```
- **Исходный код**:
  - Activity: [`MainActivity.kt`](file:///d:/Antigravity/Android/LinearLayout/app/src/main/java/com/example/linearlayout/MainActivity.kt)
  - Разметка: [`activity_main.xml`](file:///d:/Antigravity/Android/LinearLayout/app/src/main/res/layout/activity_main.xml)
  - Ресурсы: [`strings.xml`](file:///d:/Antigravity/Android/LinearLayout/app/src/main/res/values/strings.xml)
- **Сборка**: APK успешно собран: `LinearLayout/app/build/outputs/apk/debug/app-debug.apk`.

---

## 3. Проект "Components" (Часть 3)
- **Цель**: Использование компонентов `EditText`, `ImageView`, `Button` в `LinearLayout` с вертикальной ориентацией.
- **Выполненные требования**:
  1. Разметка построена на вертикальном `LinearLayout`.
  2. **`EditText`**: в качестве текста по умолчанию указана строка `Иванов Иван ПИ-9000` в виде ссылки на ресурс `@string/student_info`.
  3. **`ImageView`**: отображает дефолтную иконку приложения (`@mipmap/ic_launcher_round`), центрирован по горизонтали (`android:layout_gravity="center_horizontal"`).
  4. **`Button`**: полноширинная кнопка с текстом «ОК» (`@string/button_ok`).
- **Исходный код**:
  - Activity: [`MainActivity.kt`](file:///d:/Antigravity/Android/Components/app/src/main/java/com/example/components/MainActivity.kt)
  - Разметка: [`activity_main.xml`](file:///d:/Antigravity/Android/Components/app/src/main/res/layout/activity_main.xml)
  - Ресурсы: [`strings.xml`](file:///d:/Antigravity/Android/Components/app/src/main/res/values/strings.xml)
- **Сборка**: APK успешно собран: `Components/app/build/outputs/apk/debug/app-debug.apk`.

---

## Инструкция по запуску в Android Studio

1. Откройте **Android Studio**.
2. В главном окне выберите **Open** (или в меню `File` → `Open...`).
3. Укажите папку любого из трех проектов:
   - `d:\Antigravity\Android\FirstApp`
   - `d:\Antigravity\Android\LinearLayout`
   - `d:\Antigravity\Android\Components`
4. Дождитесь синхронизации Gradle (Gradle Sync).
5. Для сборки нажмите `Ctrl + F9` (меню `Build` → `Make Project`).
6. Для запуска нажмите зелёную кнопку **Run** (`Shift + F10`) и выберите устройство/эмулятор (AVD).

## Сборка через командную строку (CLI)

В папке любого проекта выполните:
```powershell
.\gradlew.bat assembleDebug
```
Собранный файл будет находиться по пути:
`app/build/outputs/apk/debug/app-debug.apk`
