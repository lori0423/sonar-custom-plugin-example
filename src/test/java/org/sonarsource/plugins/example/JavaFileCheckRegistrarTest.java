/*
 * Copyright (C) 2012-2023 SonarSource SA - mailto:info AT sonarsource DOT com
 * This code is released under [MIT No Attribution](https://opensource.org/licenses/MIT-0) license.
 */
package org.sonarsource.plugins.example;

import org.junit.jupiter.api.Test;
import org.sonar.plugins.java.api.CheckRegistrar;

import static org.assertj.core.api.Assertions.assertThat;

class JavaFileCheckRegistrarTest {

  @Test
  void checkNumberRules() {
    CheckRegistrar.RegistrarContext context = new CheckRegistrar.RegistrarContext();

    JavaFileCheckRegistrar registrar = new JavaFileCheckRegistrar();
    registrar.register(context);

    assertThat(context.checkClasses()).hasSize(1);
    assertThat(context.testCheckClasses()).hasSize(1);
  }

}
