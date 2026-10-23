# Репозиторий лабораторных работ по Android

Данный репозиторий содержит выполнение лабораторных работ по разработке под Android в Android Studio с использованием XML Views и Jetpack Compose (Material 3).

---

## Структура проектов

| Лабораторная | Проект | Папка | Описание |
| :--- | :--- | :--- | :--- |
| **№ 1** | **First App** | [`FirstApp/`](FirstApp) | Шаблонное приложение с экраном приветствия (`Hello World!`) и базовой разметкой. |
| **№ 1** | **LinearLayout** | [`LinearLayout/`](LinearLayout) | Вертикальная линейная компоновка (`LinearLayout`) с компонентами `TextView` (данные студента, четверостишье на русском и английском). |
| **№ 1** | **Components** | [`Components/`](Components) | Работа с компонентами `EditText`, `ImageView` и `Button` («ОК») в вертикальном `LinearLayout`. |
| **№ 4** | **Affirmations** | [`Affirmations/`](Affirmations) | Создание списков в **Jetpack Compose**: `LazyColumn` (практическая часть) и двухколоночная сетка `LazyVerticalGrid` (контрольное задание: Topics). |

---

## Лабораторная работа № 4: Создание списков (Jetpack Compose)

- **Цель**: Изучить возможности создания производительных прокручиваемых списков и сеток с ленивой загрузкой (`LazyColumn`, `LazyVerticalGrid`) в Jetpack Compose с использованием Material 3.
- **Проект**: [`Affirmations/`](Affirmations)
- **Методические указания**: [`Лабораторная работа № 4.pdf`](Лабораторная%20работа%20№%204.pdf)

### 1. Практическая часть (Affirmations)
- **Модель данных**: [`Affirmation`](Affirmations/app/src/main/java/com/example/affirmations/model/Affirmation.kt) — класс данных со строковым ресурсом (`@StringRes`) и ресурсом изображения (`@DrawableRes`).
- **Источник данных**: [`Datasource`](Affirmations/app/src/main/java/com/example/affirmations/data/Datasource.kt) — список из 10 мотивирующих утверждений.
- **Компоненты интерфейса**:
  - `AffirmationCard`: карточка `Card` с изображением `Image` (`height(194.dp)`, `ContentScale.Crop`) и текстом `Text` (`MaterialTheme.typography.headlineSmall`, отступ `16.dp`).
  - `AffirmationList`: вертикальный список на базе `LazyColumn` с элементами через `items()` и отступами `8.dp`.
  - `AffirmationsApp`: корневой компонент с безопасными системными отступами (`WindowInsets.safeDrawing`).

### 2. Контрольное задание для самопроверки (Topics Grid)
- **Модель данных**: [`Topic`](Affirmations/app/src/main/java/com/example/affirmations/model/Topic.kt) — класс данных темы курса (`name`, `availableCourses`, `imageRes`).
- **Источник данных**: `object DataSource` со списком из 14 категорий курсов (Architecture, Design, Culinary, Photography и др.).
- **Компоненты интерфейса**:
  - `TopicCard`: карточка темы с фиксированным изображением `68.dp x 68.dp`, названием (`bodyMedium`), иконкой `ic_grain` и бейджем количества курсов (`labelMedium`).
  - `TopicGrid`: двухколоночная сетка на базе `LazyVerticalGrid(columns = GridCells.Fixed(2))` с отступами `8.dp` между карточками и краями экрана.

### 3. Дополнительно:
- В [`MainActivity.kt`](Affirmations/app/src/main/java/com/example/affirmations/MainActivity.kt) добавлен переключатель вкладок (`TabRow`), позволяющий мгновенно переключаться между обеими частями работы.
- Предусмотрены аннотации `@Preview` для предпросмотра каждого элемента в Android Studio (вкладка Split / Разделение).

---

## Лабораторная работа № 1: Знакомство с Android Studio

- **Методические указания**: [`Лабораторная работа № 1.pdf`](Лабораторная%20работа%20№%201.pdf)

### 1. Проект "First App" (Часть 1)
- **Цель**: Создание базового проекта из шаблона *Empty Activity*, знакомство со структурой проекта и запуск приложения.
- **Исходный код**:
  - Activity: [`MainActivity.kt`](FirstApp/app/src/main/java/com/example/firstapp/MainActivity.kt)
  - Разметка: [`activity_main.xml`](FirstApp/app/src/main/res/layout/activity_main.xml)
  - Ресурсы: [`strings.xml`](FirstApp/app/src/main/res/values/strings.xml), [`themes.xml`](FirstApp/app/src/main/res/values/themes.xml)

### 2. Проект "LinearLayout" (Часть 2)
- **Цель**: Освоение линейной компоновки `LinearLayout` с вертикальной ориентацией (`android:orientation="vertical"`) и компонента `TextView`.
- **Выполненные требования**:
  1. Корневой контейнер сконвертирован в `<LinearLayout android:orientation="vertical">`.
  2. **Первый `TextView`**: содержит строку с ФИО и группой студента.
  3. **Второй `TextView`**: содержит четверостишье на русском языке.
  4. **Третий `TextView`**: четверостишье, переведённое на английский язык, вынесено в строковый ресурс `res/values/strings.xml` (`@string/bears_poem_en`).
- **Исходный код**:
  - Activity: [`MainActivity.kt`](LinearLayout/app/src/main/java/com/example/linearlayout/MainActivity.kt)
  - Разметка: [`activity_main.xml`](LinearLayout/app/src/main/res/layout/activity_main.xml)
  - Ресурсы: [`strings.xml`](LinearLayout/app/src/main/res/values/strings.xml)

### 3. Проект "Components" (Часть 3)
- **Цель**: Использование компонентов `EditText`, `ImageView`, `Button` в `LinearLayout` с вертикальной ориентацией.
- **Выполненные требования**:
  1. Разметка построена на вертикальном `LinearLayout`.
  2. **`EditText`**: ссылка на ресурс `@string/student_info`.
  3. **`ImageView`**: отображает дефолтную иконку приложения (`@mipmap/ic_launcher_round`), центрирован по горизонтали (`android:layout_gravity="center_horizontal"`).
  4. **`Button`**: полноширинная кнопка с текстом «ОК» (`@string/button_ok`).
- **Исходный код**:
  - Activity: [`MainActivity.kt`](Components/app/src/main/java/com/example/components/MainActivity.kt)
  - Разметка: [`activity_main.xml`](Components/app/src/main/res/layout/activity_main.xml)
  - Ресурсы: [`strings.xml`](Components/app/src/main/res/values/strings.xml)

---

## Инструкция по запуску в Android Studio

1. Откройте **Android Studio**.
2. В главном окне выберите **Open** (или `File` → `Open...`).
3. Укажите папку нужного проекта:
   - `Affirmations` (Лабораторная работа № 4)
   - `FirstApp`, `LinearLayout` или `Components` (Лабораторная работа № 1)
4. Дождитесь синхронизации Gradle (Gradle Sync).
5. Для сборки нажмите `Ctrl + F9` (`Build` → `Make Project`).
6. Для запуска нажмите зелёную кнопку **Run** (`Shift + F10`) и выберите устройство/эмулятор (AVD).

## Сборка через командную строку (CLI)

В папке любого проекта выполните:
```powershell
.\gradlew.bat assembleDebug
```
Собранный файл будет находиться по пути:
`app/build/outputs/apk/debug/app-debug.apk`
