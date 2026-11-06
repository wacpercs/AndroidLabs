# Репозиторий лабораторных работ по Android

Данный репозиторий содержит выполнение лабораторных работ по разработке под Android в Android Studio с использованием XML Views и Jetpack Compose (Material 3).

---

## Структура проектов

| Лабораторная | Проект | Папка | Описание |
| :--- | :--- | :--- | :--- |
| **№ 6** | **Unscramble** | [`Unscramble/`](Unscramble) | Практическая часть + КЗ 4: игра-анаграмма на архитектуре **MVVM + UDF** (`GameViewModel`, `StateFlow`) со 100% покрытием ViewModel unit-тестами. |
| **№ 6** | **ActivityLifecycle** | [`ActivityLifecycle/`](ActivityLifecycle) | КЗ 1: исследование жизненного цикла Android Activity, переопределение всех 7 методов с логированием в Logcat и экраном событий. |
| **№ 6** | **ArtSpace (MVVM)** | [`ArtSpace/`](ArtSpace) | КЗ 2: рефакторинг приложения галереи из Лаб 5 на архитектуру **MVVM** (`ArtSpaceViewModel`, `ArtSpaceUiState`, `StateFlow`, UDF). |
| **№ 6** | **DessertClicker** | [`DessertClicker/`](DessertClicker) | КЗ 3: игра-кликер десертов на архитектуре **MVVM** (`DessertViewModel`, `DessertUiState`, `StateFlow`, UDF) с функцией шаринга счёта. |
| **№ 5** | **TipTime** | [`TipTime/`](TipTime) | Калькулятор чаевых с поднятием состояния, полями ввода с иконками, свитчем округления и тестами (Unit + UI). |
| **№ 5** | **ArtSpace** | [`ArtSpace/`](ArtSpace) | Интерактивная галерея произведений искусств в **Jetpack Compose**: переключение картин через `when`, кнопки «Previous»/«Next», поддержка широкого/альбомного экрана. |
| **№ 4** | **Affirmations** | [`Affirmations/`](Affirmations) | Создание списков в **Jetpack Compose**: `LazyColumn` (практическая часть) и двухколоночная сетка `LazyVerticalGrid` (контрольное задание: Topics). |
| **№ 1** | **First App** | [`FirstApp/`](FirstApp) | Шаблонное приложение с экраном приветствия (`Hello World!`) и базовой разметкой. |
| **№ 1** | **LinearLayout** | [`LinearLayout/`](LinearLayout) | Вертикальная линейная компоновка (`LinearLayout`) с компонентами `TextView` (данные студента, четверостишье на русском и английском). |
| **№ 1** | **Components** | [`Components/`](Components) | Работа с компонентами `EditText`, `ImageView` и `Button` («ОК») в вертикальном `LinearLayout`. |

---

## Лабораторная работа № 6: Жизненный цикл. Архитектурные компоненты

Цель работы: изучить жизненный цикл приложения, архитектурный паттерн **MVVM**, концепцию однонаправленного потока данных (**Unidirectional Data Flow / UDF**), потоки состояния **StateFlow / MutableStateFlow** и модульное тестирование ViewModel.

### 1. Проект "Unscramble" (Практическая часть + Контрольное задание 4)
- **Цель**: Разработка игры-головоломки по расшифровке слов с использованием ViewModel и StateFlow по принципу Single Source of Truth (SSOT).
- **Проект**: [`Unscramble/`](Unscramble)
- **Ключевые компоненты**:
  - `GameUiState`: неизменяемый data-класс состояния UI (`currentScrambledWord`, `currentWordCount`, `score`, `isGuessedWordWrong`, `isGameOver`).
  - `GameViewModel`:
    - Приватное состояние `_uiState: MutableStateFlow<GameUiState>` и публичное `uiState: StateFlow<GameUiState> = _uiState.asStateFlow()`.
    - Реактивное свойство `userGuess by mutableStateOf("") private set`.
    - Методы: `updateUserGuess()`, `checkUserGuess()`, `skipWord()`, `resetGame()`.
  - `GameScreen`: компоновка экрана через `collectAsState()`, валидация ответа с подсвечиванием ошибки «Wrong Guess!» в `OutlinedTextField`, диалог `FinalScoreDialog` при окончании игры (10 слов).
- **Модульное тестирование (100% покрытие `GameViewModel`)**:
  - Расположение: [`GameViewModelTest.kt`](Unscramble/app/src/test/java/com/example/android/unscramble/ui/test/GameViewModelTest.kt).
  - Набор из 5 тестов:
    1. `gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset`: проверка пути успеха (начисление +20 очков, сброс ошибки).
    2. `gameViewModel_IncorrectGuess_ErrorFlagSet`: проверка пути ошибки (установка флага ошибки, счет 0).
    3. `gameViewModel_Initialization_FirstWordLoaded`: проверка корректной инициализации при запуске.
    4. `gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly`: проверка прохождения всех 10 раундов и перехода в `isGameOver = true`.
    5. `gameViewModel_WordSkipped_ScoreUnchangedAndWordCountIncreased`: проверка пропуска слова (увеличение номера слова без изменения очков).
  - Запуск тестов:
    ```powershell
    cd Unscramble
    .\gradlew.bat testDebugUnitTest
    ```

---

### 2. Проект "ActivityLifecycle" (Контрольное задание 1)
- **Цель**: Экспериментальное исследование жизненного цикла Android Activity при различных пользовательских сценариях.
- **Проект**: [`ActivityLifecycle/`](ActivityLifecycle)
- **Реализация**:
  - В `MainActivity` переопределены все 7 ключевых методов жизненного цикла:
    - `onCreate()`
    - `onStart()`
    - `onResume()`
    - `onPause()`
    - `onStop()`
    - `onDestroy()`
    - `onRestart()`
  - Логирование в Logcat с тегом `ActivityLifecycle`.
  - Наглядный интерактивный интерфейс на Jetpack Compose со списком событий в реальном времени и фиксацией ориентации экрана.
- **Сценарии жизненного цикла**:
  1. *Запуск приложения:* `onCreate()` → `onStart()` → `onResume()`.
  2. *Сворачивание приложения (кнопка Home):* `onPause()` → `onStop()`.
  3. *Возврат в приложение:* `onRestart()` → `onStart()` → `onResume()`.
  4. *Поворот экрана (смена конфигурации):* `onPause()` → `onStop()` → `onDestroy()` → `onCreate()` → `onStart()` → `onResume()`.
- **Сборка**:
  ```powershell
  cd ActivityLifecycle
  .\gradlew.bat assembleDebug
  ```

---

### 3. Проект "ArtSpace" (Контрольное задание 2)
- **Цель**: Рефакторинг приложения интерактивной галереи искусств из Лабораторной работы № 5 на архитектурный паттерн **MVVM**.
- **Проект**: [`ArtSpace/`](ArtSpace)
- **Выполненные требования**:
  - Создан класс состояния `ArtSpaceUiState` (`currentArtworkIndex`, `currentArtwork`).
  - Создан `ArtSpaceViewModel`:
    - Управление состоянием через `MutableStateFlow` и `asStateFlow()`.
    - Логика переключения картин инкапсулирована в методах `nextArtwork()` и `previousArtwork()`.
  - В `MainActivity.kt` логика состояний полностью отделена от UI и переведена на однонаправленный поток данных (UDF): `val uiState by viewModel.uiState.collectAsState()`.
- **Сборка**:
  ```powershell
  cd ArtSpace
  .\gradlew.bat assembleDebug
  ```

---

### 4. Проект "DessertClicker" (Контрольное задание 3)
- **Цель**: Разработка приложения Dessert Clicker на архитектуре **MVVM + UDF** с использованием `StateFlow`.
- **Проект**: [`DessertClicker/`](DessertClicker)
- **Реализация**:
  - Модель данных `Dessert` с порогами открытия (`startProductionAmount`) и ценами.
  - `DessertUiState`: текущий доход (`revenue`), количество проданных десертов (`dessertsSold`), текущий десерт и стоимость.
  - `DessertViewModel`: обработчик кликов `onDessertClicked()` с автоматическим повышением уровня десерта по мере роста продаж.
  - UI на Compose с красивым макетом десертов, карточкой транзакций и кнопкой `Share` в `TopAppBar` для отправки результатов через Android Intent.
- **Сборка**:
  ```powershell
  cd DessertClicker
  .\gradlew.bat assembleDebug
  ```

---

## Лабораторная работа № 5: Взаимодействие с UI и состоянием. Тесты

### 1. Проект "TipTime" (Калькулятор чаевых)
- **Проект**: [`TipTime/`](TipTime)
- Функция расчета чаевых `calculateTip`, кастомные числовые поля с иконками, свитч округления, Unit-тесты (`TipCalculatorTests`) и UI-тесты (`TipUITests`).

### 2. Проект "ArtSpace" (Галерея искусств)
- **Проект**: [`ArtSpace/`](ArtSpace)
- Адаптивный макет (Portrait / Landscape), карточки шедевров, навигация «Previous» / «Next».

---

## Лабораторная работа № 4: Создание списков (Jetpack Compose)

- **Проект**: [`Affirmations/`](Affirmations)
- `AffirmationList` на `LazyColumn` и `TopicGrid` на `LazyVerticalGrid`.

---

## Лабораторная работа № 1: Знакомство с Android Studio

- `FirstApp/`, `LinearLayout/`, `Components/`.

---

## Инструкция по открытию и запуску в Android Studio

1. Откройте **Android Studio**.
2. Выберите **Open** и укажите папку нужного проекта (`Unscramble`, `ActivityLifecycle`, `ArtSpace` или `DessertClicker`).
3. Дождитесь автоматической синхронизации Gradle.
4. Нажмите **Run** (`Shift + F10`) для запуска выбранного приложения на эмуляторе или физическом устройстве.
5. Для запуска тестов в `Unscramble`: кликните правой кнопкой мыши по `GameViewModelTest` и выберите **Run 'GameViewModelTest'** (или **Run with Coverage**).
