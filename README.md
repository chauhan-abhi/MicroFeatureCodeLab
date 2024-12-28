# 🚧 Micro Feature Architecture Sample 🛠️

Welcome to the Micro Feature Architecture Sample! This project showcases a modular, plug-and-play approach to building dynamic UI screens. Each screen can be configured with a list of widgets, each operating independently, managing its own data fetching, action handling, and rendering.

---

## 🎯 **Objective**

This project aims to provide a robust foundation for:
- **Dynamic UI Composition:** Render screens based on a configuration list received from the server or stored locally.
- **Plug-and-Play Components:** Allow independent widgets to function as reusable, self-contained units.
- **Inter-Component Communication:** Solve challenges related to communication between different widgets on the same screen.
- **Dynamic ViewModel Binding:** Dynamically bind components and their ViewModels based on the configuration.

---

## 📜 **Key Features**

- **Composable Widgets:**
    - Each widget is treated as an isolated micro-feature responsible for its logic and presentation.
    - Widgets can fetch their data independently, enabling greater modularity.

- **Dynamic Configuration:**
    - The screen layout is determined by a config list (JSON or other formats) from the server/local storage.
    - Widgets are dynamically added, removed, or reordered without requiring code changes.

- **Inter-Widget Communication:**
    - Seamlessly manage interactions between independent widgets, ensuring a cohesive user experience.

- **ViewModel Abstraction:**
    - Leverages ViewModel factories and dependency injection to bind widgets to their respective ViewModels dynamically.

- **Extensibility:**
    - Easily add new widgets or micro-features by implementing predefined interfaces.

---

## 🏗 **Architecture Overview**

### **1. Component-Driven Design**
- Each widget is implemented as a **Micro Feature Component**, responsible for:
    - Its data fetching (via ViewModel).
    - Rendering its UI independently.
    - Handling its specific user actions.

### **2. Dependency Injection with Hilt**
- Widgets and their ViewModels are dynamically provided using Hilt multibinding.
- Ensures lifecycle-aware and scoped dependencies.

### **3. Communication & Action Handling**
- A central mechanism for widgets to communicate or trigger actions shared by the parent screen or other widgets.

### **4. JSON-Driven Configuration**
```json
[
  {
    "type": "personalisedJob",
    "config": {
      "title": "Recommended Jobs",
      "dataEndpoint": "/api/jobs"
    }
  },
  {
    "type": "premiumApplicant",
    "config": {
      "title": "Premium Applicants",
      "dataEndpoint": "/api/applicants"
    }
  }
]
```
---

## 🚀 **How It Works**

1. **Receive Configuration List:**
    - The application fetches or loads a configuration list (JSON or another format) from the server or local storage. This list defines the widgets to be displayed and their properties.

2. **Render Widgets Dynamically:**
    - The configuration list is parsed, and widgets are rendered on the screen based on their type and configuration. Each widget is treated as a micro-feature component.

3. **Independent Data Handling:**
    - Each widget operates independently by fetching its required data and updating its UI without affecting other widgets.

4. **Inter-Widget Communication:**
    - A central mechanism facilitates seamless communication between widgets for cohesive user interactions and shared states.

---

## ⚙️ **Setup and Usage**

### **1. Clone the Repository**
Clone the project to your local machine using the following command:
```bash
git clone https://github.com/yourusername/micro-feature-architecture-sample.git
```
### **2. Open in Android Studio**
Ensure you have the latest version of Android Studio installed.

### **3. Build and Run**
Use the default build variant to run the app.
The app renders a sample screen using a predefined config list.