# Contributing to OneB Common Library

Thank you for your interest in contributing to the OneB Common Library! This document provides guidelines for contributing to this project.

## Getting Started

1. Fork the repository
2. Clone your fork locally
3. Create a new branch for your feature or bug fix
4. Make your changes
5. Test your changes
6. Submit a pull request

## Development Setup

### Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

### Building the Project

```bash
mvn clean compile
```

### Running Tests

```bash
mvn test
```

### Building the Package

```bash
mvn clean package
```

## Code Style Guidelines

- Follow standard Java naming conventions
- Use meaningful variable and method names
- Add Javadoc comments for public methods and classes
- Keep methods focused and concise
- Use appropriate access modifiers

## Testing Guidelines

- Write unit tests for all new functionality
- Ensure all tests pass before submitting a pull request
- Aim for good test coverage
- Use descriptive test method names
- Follow the Arrange-Act-Assert pattern

## Submitting Changes

1. **Create a Feature Branch**: Create a new branch from `main` for your changes
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Make Your Changes**: Implement your feature or bug fix

3. **Write Tests**: Add appropriate tests for your changes

4. **Run Tests**: Ensure all tests pass
   ```bash
   mvn test
   ```

5. **Commit Your Changes**: Use clear, descriptive commit messages
   ```bash
   git commit -m "Add feature: description of what you added"
   ```

6. **Push to Your Fork**:
   ```bash
   git push origin feature/your-feature-name
   ```

7. **Submit a Pull Request**: Create a pull request from your branch to the main repository

## Pull Request Guidelines

- Provide a clear description of the changes
- Reference any related issues
- Ensure all tests pass
- Update documentation if necessary
- Keep pull requests focused on a single feature or bug fix

## Code Review Process

1. All pull requests require review before merging
2. Address any feedback from reviewers
3. Ensure CI/CD checks pass
4. Maintainers will merge approved pull requests

## Reporting Issues

When reporting issues, please include:

- A clear description of the problem
- Steps to reproduce the issue
- Expected behavior
- Actual behavior
- Java version and environment details

## Feature Requests

For feature requests, please:

- Check if the feature already exists
- Provide a clear use case
- Explain why the feature would be beneficial
- Consider if it fits the scope of this library

## Questions?

If you have questions about contributing, feel free to:

- Open an issue for discussion
- Contact the maintainers

Thank you for contributing to OneB Common Library!
