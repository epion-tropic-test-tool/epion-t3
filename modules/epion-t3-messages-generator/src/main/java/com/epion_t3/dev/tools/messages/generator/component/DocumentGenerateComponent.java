package com.epion_t3.dev.tools.messages.generator.component;

import com.epion_t3.core.common.bean.spec.Command;
import com.epion_t3.core.common.bean.spec.ET3Spec;
import com.epion_t3.dev.tools.messages.generator.bean.CommandOutputModel;
import com.epion_t3.dev.tools.messages.generator.bean.ExecuteOptions;
import com.epion_t3.dev.tools.messages.generator.comparator.FunctionComparator;
import com.epion_t3.dev.tools.messages.generator.comparator.StructureComparator;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.apache.commons.collections.CollectionUtils;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DocumentGenerateComponent {

    private static DocumentGenerateComponent instance = new DocumentGenerateComponent();

    private DocumentGenerateComponent() {
        // Do Nothing...
    }

    public static DocumentGenerateComponent getInstance() {
        return instance;
    }

    public void generate(ET3Spec spec, ExecuteOptions options) {


        // Command
        for (Command command : spec.getCommands()) {

            // Locale毎のコマンド出力モデルマップ
            Map<String, CommandOutputModel> commandOutputModelMap = new HashMap<>();

            // 対象のLocaleについてコマンド出力モデルを作成
            spec.getLanguages().stream().forEach(x -> {
                Locale locale = Locale.forLanguageTag(x);
                CommandOutputModel com = new CommandOutputModel();
                com.setLocale(locale);
                commandOutputModelMap.put(x, com);
            });


            commandOutputModelMap.forEach((k, v) -> {
                        // ID
                        v.setId(command.getId());
                        // Command Kind
                        v.setAssertCommand(command.getAssertCommand());
                        v.setEvidenceCommand(command.getEvidenceCommand());
                    }
            );

            // Summary
            command.getSummary().stream()
                    .forEach(x -> {
                        if (commandOutputModelMap.containsKey(x.getLang())) {
                            commandOutputModelMap.get(x.getLang())
                                    .addSummary(x.getContents());
                        }
                    });

            // Functions
            command.getFunction().stream()
                    .sorted(FunctionComparator.getInstance())
                    .forEach(x -> {
                        x.getSummary().forEach(y -> {
                            if (commandOutputModelMap.containsKey(y.getLang())) {
                                commandOutputModelMap.get(y.getLang())
                                        .addFunction(y.getContents());
                            }
                        });
                    });

            // Structure Description
            command.getStructure().stream()
                    .sorted(StructureComparator.getInstance())
                    .forEach(x -> {
                        if (CollectionUtils.isNotEmpty(x.getDescription())) {
                            x.getDescription().forEach(y -> {
                                if (commandOutputModelMap.containsKey(y.getLang())) {
                                    commandOutputModelMap.get(y.getLang())
                                            .addStructureDescription(y.getContents());
                                }
                            });
                        }
                    });

            for (Map.Entry<String, CommandOutputModel> entry : commandOutputModelMap.entrySet()) {
                createCommand(entry.getValue());
            }

        }


    }


    private String createCommand(CommandOutputModel com) {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("command.mustache");
        StringWriter sw = new StringWriter();
        m.execute(sw, com);
        sw.flush();
        String contents = sw.toString();
        System.out.println(contents);
        return contents;
    }

    private String createConfiguration() {
        return null;
    }

    private String createFlow() {
        return null;
    }


}
