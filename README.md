# Extensible Java Playwright Automation Framework

The purpose of this Framework is to serve as a functional demo and / or template to be used on other projects you might
undertake and assist with training.

## Description

The Playwright for Java project consists of the following elements and technologies:

- [Playwright for Java](https://playwright.dev/java/)
- [Spring Boot](https://spring.io/)
    - Why include SpringBoot in the Framework?
        - Helps with Dependency Injection between test steps
        - Easy environment management (via `application.yml`)
        - Simplifies the development process by utilizing annotations
        - Lets you create a standalone application that runs on its own; as an example this allows easier Dockerizing of
          the automation framework, should it be required.
- [Cucumber](https://cucumber.io/)
    - Behavior-Driven Development (BDD), Cucumber is an intuitive Way to Express Requirements in Human-Readable Form.
      Acts as a great starting point for test automation scenarios.
- [JUnit 5](https://junit.org/junit5/)
- [tinylog](https://tinylog.org/v2/)
    - Simple lightweight static logger.
- [AssertJ](https://assertj.github.io/doc/)
    - What is not to like about fluent assertions?
- [Project Lombok](https://projectlombok.org/)
    - Project Lombok is a library that helps you to write clear, concise, and less repetitive Java code. It makes up for
      some of the shortcomings within the core of Java that will most likely never be improved.

## Getting Started

### Dependencies

* Playwright for Java
* Spring Boot
* Cucumber
* JUnit 5
* tinylog
* AssertJ
* Project Lombok

### Installing

* Running a `mvn clean package` should be enough to get the project built and ready to use.

### Executing program

* Right-click a feature file (`src/main/resources`) and select `Run` or `Debug`

### FAQ

1. Why no test module or test resource directory?
    * This framework is typically testing a target system and not itself; typically a test module / resource folder is
      meant to be testing the project it belongs to.

## Authors

* Bernard Visagie

## License

MIT License

Copyright (c) 2023 - Bernard Visagie

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.