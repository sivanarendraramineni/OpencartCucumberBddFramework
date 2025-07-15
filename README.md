
 # Opencart Cucumber BDD Framework

 
[![GitHub stars](https://img.shields.io/github/stars/sivanrendraramineni/OpencartCucumberBddFramework?style=social)](https://github.com/sivanrendraramineni/OpencartCucumberBddFramework/stargazers)

[![GitHub forks](https://img.shields.io/github/forks/sivanrendraramineni/OpencartCucumberBddFramework?style=social)](https://github.com/sivanrendraramineni/OpencartCucumberBddFramework/network/members)

[![Last Commit](https://img.shields.io/github/last-commit/sivanrendraramineni/OpencartCucumberBddFramework)](https://github.com/sivanrendraramineni/OpencartCucumberBddFramework)

[![Issues](https://img.shields.io/github/issues/sivanrendraramineni/OpencartCucumberBddFramework)](https://github.com/sivanrendraramineni/OpencartCucumberBddFramework/issues)


OpenCart Selenium Cucumber BDD Automation Framework

This repository contains a complete, scalable hybrid automation testing framework designed for real-world testing of the OpenCart web application. Built with Selenium WebDriver, Cucumber BDD, TestNG, and Java, the framework supports modular development, parallel execution, data-driven testing, and seamless CI/CD integration.


🔍 Key Highlights

✅ BDD with Cucumber – Business-readable Gherkin scenarios for behavior-driven development

✅ Data-Driven Testing – Supports both Scenario Outline and Excel/config-based input

✅ Parallel Execution – Enabled via TestNG and ThreadLocal<WebDriver> for thread-safe, fast execution

✅ Retry Mechanism – Auto-retry for failed tests using RetryAnalyzer, improves resilience against flaky UI tests

✅ Smart Reporting –

    • Retries are visible in the console
    
    • Only final result (pass/fail) is logged to Extent Reports with failure screenshots
    
✅ Page Object Model (POM) – Clean and reusable structure for UI elements

✅ Configurable – Easily switch browser or base URL from config.properties

✅ Cross-Browser Support – Chrome, Firefox, and Edge supported

✅ Built-in Logging – Log4j for detailed debug and run-time traceability

✅ CI/CD Ready – Jenkins job integration with GitHub auto-pull and report publishing

🔧 Tech Stack & Tools

Java + Selenium WebDriver

Cucumber BDD + Gherkin

TestNG (parallel execution, retry logic)

ExtentReports + Cucumber HTML Reports

Apache POI (for Excel data-driven testing)

Log4j2 / SLF4J

GitHub Copilot + ChatGPT (AI assistance)

Jenkins (CI/CD automation)

👨‍💻 Author

Siva Narendra Ramineni

www.linkedin.com/in/sivanarendraramineni

QA Automation Engineer | Selenium | Java | TestNG | Cucumber | REST API | Gen AI | GitHub Copilot
