package com.epion_t3.core.custom.validator;

import com.epion_t3.core.common.bean.CommandSpecInfo;
import com.epion_t3.core.common.bean.CommandSpecStructure;
import com.epion_t3.core.common.bean.scenario.Command;
import com.epion_t3.core.common.context.Context;
import com.epion_t3.core.common.context.ExecuteContext;
import com.epion_t3.core.common.type.NotificationType;
import com.epion_t3.core.common.type.StructureType;
import com.epion_t3.core.custom.holder.CustomPackageHolder;
import com.epion_t3.core.message.MessageManager;
import com.epion_t3.core.message.impl.CoreMessages;
import com.epion_t3.core.scenario.bean.CommandValidateError;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandValidator {

    private static final CommandValidator instance = new CommandValidator();

    public static CommandValidator getInstance() {
        return instance;
    }

    private CommandValidator() {
        // Do Nothing...
    }

    public List<CommandValidateError> validate(
            final Context context,
            final ExecuteContext executeContext,
            final Command command) {

        List<CommandValidateError> result = new ArrayList<>();

        CommandSpecInfo commandSpecInfo =
                CustomPackageHolder.getInstance().getCustomCommandSpec(command.getClass());

        for (CommandSpecStructure css : commandSpecInfo.getStructures().values()) {
            try {

                // 値の取得
                Object propertyValue = PropertyUtils.getProperty(command, css.getName());

                // 必須チェック
                if (css.getRequired() && propertyValue == null) {
                    result.add(CommandValidateError
                            .commandValidateErrorBuilder()
                            .stage(executeContext.getStage())
                            .level(NotificationType.ERROR)
                            .message(MessageManager.getInstance().getMessage(
                                    CoreMessages.CORE_ERR_0042, command.getCommand(), css.getName())).build());
                    continue;
                }

                // 設計定義の型
                StructureType type = StructureType.valueOfByValue(css.getType());

                switch (type) {

                    case STRING:
                        String stringValue = String.class.cast(propertyValue);

                        // 必須チェック（Stringの場合はEmptyもチェック）
                        if (css.getRequired() && stringValue.isEmpty()) {
                            result.add(CommandValidateError
                                    .commandValidateErrorBuilder()
                                    .stage(executeContext.getStage())
                                    .level(NotificationType.ERROR)
                                    .message(MessageManager.getInstance().getMessage(
                                            CoreMessages.CORE_ERR_0042, command.getCommand(), css.getName())).build());
                            continue;
                        }

                        // パターンチェック
                        if (css.getPattern() != null && !css.getPattern().isEmpty()) {
                            Pattern checkPattern = Pattern.compile(css.getPattern());
                            Matcher checkMatcher = checkPattern.matcher(stringValue);
                            if (!checkMatcher.matches()) {
                                result.add(CommandValidateError
                                        .commandValidateErrorBuilder()
                                        .stage(executeContext.getStage())
                                        .level(NotificationType.ERROR)
                                        .message(MessageManager.getInstance().getMessage(
                                                CoreMessages.CORE_ERR_0043,
                                                command.getCommand(),
                                                css.getName(), css.getPattern())).build());
                                continue;
                            }
                        }
                        break;
                    default:
                        break;
                }

            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                result.add(CommandValidateError
                        .commandValidateErrorBuilder()
                        .stage(executeContext.getStage())
                        .level(NotificationType.ERROR)
                        .error(e)
                        .message(MessageManager.getInstance().getMessage(
                                CoreMessages.CORE_ERR_0044, command.getCommand(), css.getName())).build());
            }
        }
        return result;
    }

    /**
     * 親クラスを含めた全てのプロパティから指定フィールドを走査.
     *
     * @param fieldName フィールド名
     * @return フィールド
     * @throws NoSuchFieldException フィールドが見つからなかった場合
     * @para clazz     対象クラス
     */
    private Field getFieldFromClass(Class clazz, String fieldName)
            throws NoSuchFieldException {
        Field field = null;
        while (clazz != null) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }

        if (field == null) {
            throw new NoSuchFieldException();
        }
        return field;
    }
}
