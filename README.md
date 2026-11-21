# Bank Transaction Validation (Java)

Console-based Java example of custom exceptions for withdrawal checks.

## Run
```bash
javac BankTransaction.java
java BankTransaction
```

## Features
Validates withdrawal amount, available balance and withdrawal limit. Reports successful and failed withdrawals and a final summary. The enhanced version rejects invalid initial balances, limits and non-finite withdrawal amounts.

## Important limitations
Classroom demonstration only: monetary values currently use `double` and are not suitable for production banking. Real financial systems should use `BigDecimal`, persistence, auditing, authentication and comprehensive validation. Test zero, negative, `NaN`, too-large and insufficient-funds inputs.
