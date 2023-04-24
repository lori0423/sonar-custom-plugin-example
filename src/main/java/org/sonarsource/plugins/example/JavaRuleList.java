package org.sonarsource.plugins.example;

import org.sonar.plugins.java.api.JavaCheck;
import org.sonarsource.plugins.example.rules.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Lori
 * @date 2023/4/24 14:07
 * @description:
 */
public final class JavaRuleList {

    private JavaRuleList() {
    }

    public static List<Class<? extends JavaCheck>> getChecks() {
        List<Class<? extends JavaCheck>> checks = new ArrayList<>();
        checks.addAll(getJavaChecks());
        checks.addAll(getJavaTestChecks());
        return Collections.unmodifiableList(checks);
    }

    /**
     * These rules are going to target MAIN code only
     */
    public static List<Class<? extends JavaCheck>> getJavaChecks() {
        return Collections.unmodifiableList(Arrays.asList(
                AvoidAnnotationRule.class
                ));
    }

    /**
     * These rules are going to target TEST code only
     */
    public static List<Class<? extends JavaCheck>> getJavaTestChecks() {
        return Collections.unmodifiableList(Arrays.asList(
                NoManualGetterRule.class));
    }
}
