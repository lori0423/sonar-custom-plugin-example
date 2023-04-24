/*
 * Copyright (C) 2012-2023 SonarSource SA - mailto:info AT sonarsource DOT com
 * This code is released under [MIT No Attribution](https://opensource.org/licenses/MIT-0) license.
 */
package org.sonarsource.plugins.example.rules;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;
import org.sonarsource.plugins.example.utils.FilesUtils;


class NoManualGetterRuleTest {

  @Test
  void check() {
    // Verifies that the check will raise the adequate issues with the expected message.
    // In the test file, lines which should raise an issue have been commented out
    // by using the following syntax: "// Noncompliant {{EXPECTED_MESSAGE}}"
    CheckVerifier.newVerifier()
      .onFile("src/test/files/NoManualGetterRule.java")
      .withCheck(new NoManualGetterRule())
      // tells the analyzer to use the jars present in this folder for tracking bytecode and resolve anntotations such as "org.junit.Test"
      .withClassPath(FilesUtils.getClassPath("target/test-jars"))
      .verifyIssues();
  }

}
