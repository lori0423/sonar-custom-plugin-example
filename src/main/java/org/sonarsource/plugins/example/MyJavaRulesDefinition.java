package org.sonarsource.plugins.example;

/**
 * @author Lori
 * @date 2023/4/24 14:08
 * @description:
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import org.sonar.api.SonarRuntime;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonarsource.analyzer.commons.RuleMetadataLoader;

/**
 * Declare rule metadata in server repository of rules.
 * That allows to list the rules in the page "Rules".
 */
public class MyJavaRulesDefinition implements RulesDefinition {

    // don't change that because the path is hard coded in CheckVerifier
    private static final String RESOURCE_BASE_PATH = "org/sonar/l10n/java/rules/java";

    public static final String REPOSITORY_KEY = "boilerplate-code-analyze";

    public static final String REPOSITORY_NAME = "Boilerplate code custom rules";

    // Add the rule keys of the rules which need to be considered as template-rules
    private static final Set<String> RULE_TEMPLATES_KEY = Collections.emptySet();

    private final SonarRuntime runtime;

    public MyJavaRulesDefinition(SonarRuntime runtime) {
        this.runtime = runtime;
    }

    @Override
    public void define(Context context) {
        NewRepository repository = context.createRepository(REPOSITORY_KEY, "java").setName(REPOSITORY_NAME);

        RuleMetadataLoader ruleMetadataLoader = new RuleMetadataLoader(RESOURCE_BASE_PATH, runtime);

        ruleMetadataLoader.addRulesByAnnotatedClass(repository, new ArrayList<>(JavaRuleList.getChecks()));

        setTemplates(repository);

        repository.done();
    }

    private static void setTemplates(NewRepository repository) {
        RULE_TEMPLATES_KEY.stream()
                .map(repository::rule)
                .filter(Objects::nonNull)
                .forEach(rule -> rule.setTemplate(true));
    }
}
