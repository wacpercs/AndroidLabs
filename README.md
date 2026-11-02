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
| **№ 5** | **TipTime** | [`TipTime/`](TipTime) | Взаимодействие с UI и состоянием в **Jetpack Compose**: калькулятор чаевых с поднятием состояния, полями ввода с иконками, свитчем округления и тестами (Unit + UI). |
| **№ 5** | **ArtSpace** | [`ArtSpace/`](ArtSpace) | Интерактивная галерея произведений искусств в **Jetpack Compose**: переключение картин через `when`, кнопки «Previous»/«Next», поддержка широкого/альбомного экрана. |

---

## Лабораторная работа № 5: Взаимодействие с UI и состоянием. Тесты

### 1. Проект "TipTime" (Калькулятор чаевых)
- **Цель**: Изучить работу с состояниями (`remember`, `mutableStateOf`), поднятие состояния (State Hoisting), форматирование валюты и автоматизированное тестирование.
- **Проект**: [`TipTime/`](TipTime)
- **Выполненные требования**:
  1. `calculateTip(amount, tipPercent, roundUp)`: функция вычисления суммы чаевых с поддержкой округления (`kotlin.math.ceil`), аннотированная `@VisibleForTesting`.
  2. `EditNumberField`: универсальное текстовое поле ввода чисел с иконкой и настройками клавиатуры:
     - Поле «Bill Amount» с иконкой банкноты и действием `ImeAction.Next`.
     - Поле «Tip Percentage» с иконкой процента и действием `ImeAction.Done`.
  3. `RoundTheTipRow`: строка с переключателем `Switch` («Round up tip?») для активации округления чаевых в большую сторону.
  4. `TipTimeLayout`: корневой макет со скроллингом `verticalScroll` и безопасными отступами `safeDrawingPadding`.
- **Тестирование**:
  - **Unit-тесты (JVM)**: [`TipCalculatorTests.kt`](TipTime/app/src/test/java/com/example/tiptime/TipCalculatorTests.kt):
    - `calculateTip_20PercentNoRoundup`: проверка расчета 20% без округления.
    - `calculateTip_20PercentRoundup`: проверка расчета с округлением.
  - **UI-тесты (Compose Test Rule)**: [`TipUITests.kt`](TipTime/app/src/androidTest/java/com/example/tiptime/TipUITests.kt):
    - `calculate_20_percent_tip`: ввод суммы "10", процентов "20" и проверка отображения строки `Tip Amount: $2.00`.
- **Сборка и запуск тестов**:
  ```powershell
  cd TipTime
  .\gradlew.bat assembleDebug
  .\gradlew.bat testDebugUnitTest
  ```

---

### 2. Проект "ArtSpace" (Галерея искусств)
- **Цель**: Реализация динамического переключения UI на основе внутреннего состояния с использованием конструкции `when`, адаптивный макет для поддержки широкого экрана (Landscape).
- **Проект**: [`ArtSpace/`](ArtSpace)
- **Выполненные требования**:
  1. `ArtworkWall`: картина в рамке `Surface` с белым фоном, рамкой и тенью `shadow(8.dp)`.
  2. `ArtworkDescriptor`: карточка с названием картины в стиле `headlineSmall` (жирный) и автором со годом в стиле `bodyMedium`.
  3. `NavigationButtons`: две кнопки «Previous» и «Next» для циклического перелистывания галереи.
  4. Переключение через `when`: переход между шедеврами (Мона Лиза, Звёздная ночь, Крик, Большая волна в Канагаве).
  5. **Поддержка широкого экрана (Landscape)**: адаптивный макет через `BoxWithConstraints` (в альбомной ориентации — двухколоночный макет: картина слева, описание и кнопки справа; в портретной ориентации — вертикальный макет со скроллингом).
- **Сборка**:
  ```powershell
  cd ArtSpace
  .\gradlew.bat assembleDebug
  ```

---

## Лабораторная работа № 4: Создание списков (Jetpack Compose)

- **Цель**: Изучить возможности создания производительных прокручиваемых списков и сеток с ленивой загрузкой (`LazyColumn`, `LazyVerticalGrid`) в Jetpack Compose с использованием Material 3.
- **Проект**: [`Affirmations/`](Affirmations)
- **Практическая часть**: `AffirmationList` на базе `LazyColumn` со списком из 10 мотивирующих утверждений и карточками `AffirmationCard`.
- **Контрольное задание**: `TopicGrid` на базе `LazyVerticalGrid` с 2 фиксированными колонками, карточками тем курсов (68x68 dp, бейдж с иконкой `ic_grain`).

---

## Лабораторная работа № 1: Знакомство с Android Studio

- **Проекты**:
  - `FirstApp/`: Базовый шаблон *Empty Activity*.
  - `LinearLayout/`: Вертикальная линейная компоновка `LinearLayout` с тремя компонентами `TextView`.
  - `Components/`: Работа с `EditText`, `ImageView`, `Button` в `LinearLayout`.

---

## Инструкция по запуску в Android Studio

1. Откройте **Android Studio**.
2. В главном окне выберите **Open** (или `File` → `Open...`).
3. Укажите папку нужного проекта:
   - `TipTime` (Лабораторная работа № 5 — Калькулятор чаевых)
   - `ArtSpace` (Лабораторная работа № 5 — Галерея)
   - `Affirmations` (Лабораторная работа № 4)
   - `FirstApp`, `LinearLayout`, `Components` (Лабораторная работа № 1)
4. Дождитесь синхронизации Gradle (Gradle Sync).
5. Для сборки нажмите `Ctrl + F9` (`Build` → `Make Project`).
6. Для запуска нажмите зелёную кнопку **Run** (`Shift + F10`) и выберите устройство/эмулятор (AVD).
7. Для запуска Unit-тестов в `TipTime` щелкните правой кнопкой мыши по файлу `TipCalculatorTests` и выберите **Run 'TipCalculatorTests'**.
