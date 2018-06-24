package com.zomu.t.t3.core.application;

import com.zomu.t.t3.basic.command.model.FileCopy;
import com.zomu.t.t3.core.model.context.Context;
import com.zomu.t.t3.core.scenario.parser.ScenarioParser;
import com.zomu.t.t3.v10.model.context.ContextV10;
import com.zomu.t.t3.v10.model.scenario.Information;
import com.zomu.t.t3.v10.model.scenario.T3Base;
import com.zomu.t.t3.v10.parser.ScenarioParserV10;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Application_bak {


    public static void main(String[] args) throws IOException {


        Context context = new ContextV10(Paths.get("/Users/takashimanozomu/work/30_pgworkspaces/intellij/t3-core/modules/t3-core/src/main/resources/sample"));

        ScenarioParser scenarioParser = new ScenarioParserV10();
        scenarioParser.parse(context);


//        Files.find(Paths.get("/Users/takashimanozomu/work/30_pgworkspaces/intellij/t3-core/modules/t3-core/src/main/resources"), Integer.MAX_VALUE, (p, attr) -> p.toFile().getName().endsWith("costom.yaml")).forEach(x->x.forEach(System.out::println));

        List customs = new ArrayList<>();
        Files.find(Paths.get("/Users/takashimanozomu/work/30_pgworkspaces/intellij/t3-core/modules/t3-core/src/main/resources"), Integer.MAX_VALUE, (p, attr) -> p.toFile().getName().equals("custom.yaml")).forEach(x -> {
            try {
                customs.add(context.getObjectMapper().readValue(x.toFile(), T3Base.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK); // ブロックスタイルにする
        options.setIndicatorIndent(2); // プロパティのインデントを2に設定する
        options.setIndent(4); // インデントを4に設定する


        T3Base base = new T3Base();
        Information info = new Information();
        info.setId("id");
        info.setSummary("Summary");
        info.setDescription("Description");
        base.setInfo(info);


        FileCopy fileCopy = new FileCopy();
        fileCopy.setId("111");
        fileCopy.setCommand("filecopy");
        fileCopy.setTo("/to");
        fileCopy.setFrom("/from");
        base.getProcesses().add(fileCopy);

        Yaml yaml = new Yaml(options);
        String data = yaml.dump(base);
        System.out.println(data);


        try (InputStream is = Application_bak.class.getClassLoader().getResourceAsStream("test2.yaml")) {
            Object t3 = yaml.load(is);
            System.out.println("-----------------------");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (InputStream is = Application_bak.class.getClassLoader().getResourceAsStream("test3.yaml")) {
            System.out.println("-----------------------");
            T3Base val = context.getObjectMapper().readValue(is, T3Base.class);
            System.out.println(context.getObjectMapper().writeValueAsString(val));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
