/*
 * Copyright (C) 2012-2023 SonarSource SA - mailto:info AT sonarsource DOT com
 * This code is released under [MIT No Attribution](https://opensource.org/licenses/MIT-0) license.
 */
package org.sonarsource.plugins.example.rules;

import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.*;

import java.util.List;

@Rule(key = "AvoidAnnotation")
public class AvoidAnnotationRule extends BaseTreeVisitor implements JavaFileScanner {

  private static final String DEFAULT_VALUE = "Inject";

  private JavaFileScannerContext context;

  /**
   * Name of the annotation to avoid. Value can be set by users in Quality profiles.
   * The key
   */
  @RuleProperty(
    defaultValue = DEFAULT_VALUE,
    description = "Name of the annotation to avoid, without the prefix @, for instance 'Override'")
  protected String name;

  @Override
  public void scanFile(JavaFileScannerContext context) {
    this.context = context;
    scan(context.getTree());
  }

  @Override
  public void visitMethod(MethodTree tree) {
    List<AnnotationTree> annotations = tree.modifiers().annotations();
    for (AnnotationTree annotationTree : annotations) {
      TypeTree annotationType = annotationTree.annotationType();
      if (annotationType.is(Tree.Kind.IDENTIFIER)) {
        IdentifierTree identifier = (IdentifierTree) annotationType;
        if (identifier.name().equals(name)) {
          context.reportIssue(this, identifier, String.format("Avoid using annotation @%s", name));
        }
      }
    }

    // The call to the super implementation allows to continue the visit of the AST.
    // Be careful to always call this method to visit every node of the tree.
    super.visitMethod(tree);
  }
}
