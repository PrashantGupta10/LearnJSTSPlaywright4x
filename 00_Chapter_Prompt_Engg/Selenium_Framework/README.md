# Salesforce Selenium Framework

Java 25+ and Maven are required. Selenium Manager resolves the browser driver; install Chrome, Firefox, or Edge for the browser selected in the test configuration.

## Run

```powershell
mvn clean test
```

The valid-login test reads `SF_USERNAME` and `SF_PASSWORD` from the environment and skips when either value is missing. The invalid-login test uses the requested `admin` / `admin` values. The target is Salesforce's public login page, whose A/B variants, MFA, CAPTCHA, and network protections can affect browser-test results.

## Configuration

Defaults are in `src/test/resources/config.properties`. Override them with Maven system properties:

```powershell
mvn test -Dbrowser=chrome -Dheadless=true -Dexplicit.wait.seconds=30
```

Supported browsers are `chrome`, `firefox`, and `edge`. Set `-Dbase.url=...` to target a Salesforce org-specific login URL. Failure screenshots are written to `target/screenshots`.

## Credentials

Set credentials in the current PowerShell session before running the suite:

```powershell
$env:SF_USERNAME = "your-test-username"
$env:SF_PASSWORD = "your-test-password"
mvn test
```

Do not commit credentials or use production accounts for automation.