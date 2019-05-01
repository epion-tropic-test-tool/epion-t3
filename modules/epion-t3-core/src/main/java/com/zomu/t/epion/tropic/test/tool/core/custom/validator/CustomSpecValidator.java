package com.zomu.t.epion.tropic.test.tool.core.custom.validator;

import com.zomu.t.epion.tropic.test.tool.core.common.bean.CommandInfo;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.CommandSpecInfo;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.CommandSpecStructure;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ET3Notification;
import com.zomu.t.epion.tropic.test.tool.core.common.context.Context;
import com.zomu.t.epion.tropic.test.tool.core.common.context.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.common.type.NotificationType;
import com.zomu.t.epion.tropic.test.tool.core.common.type.StructureType;
import com.zomu.t.epion.tropic.test.tool.core.custom.holder.CustomPackageHolder;
import com.zomu.t.epion.tropic.test.tool.core.scenario.bean.CommandSpecStructureValidateError;
import com.zomu.t.epion.tropic.test.tool.core.scenario.bean.CommandSpecValidateError;
import com.zomu.t.epion.tropic.test.tool.core.message.MessageManager;
import com.zomu.t.epion.tropic.test.tool.core.message.impl.CoreMessages;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class CustomSpecValidator {

    private static final CustomSpecValidator instance = new CustomSpecValidator();

    public static CustomSpecValidator getInstance() {
        return instance;
    }

    /**
     * カスタムコマンドの設計と実装の検証を行う.
     * この検証の意図は、設計＝ユーザーが頼りにする情報と実装に相違がある場合、解析にとても時間がかかる.
     * ユーザビリティの観点から、ツールとしても設計と実装の生合成がある程度取れていることを確認するため.
     *
     * @param context
     * @param executeContext
     * @param customName
     * @param commandInfo
     * @return
     */
    public List<CommandSpecValidateError> validateCommandSpec(
            final Context context, final ExecuteContext executeContext, String customName, final CommandInfo commandInfo) {

        log.debug("validate start {}.{}", customName, commandInfo.getId());

        List<CommandSpecValidateError> result = new ArrayList<>();

        CommandSpecInfo commandSpec =
                CustomPackageHolder.getInstance().getCommandSpec(customName, commandInfo.getId());

        if (commandSpec == null) {
            executeContext.addNotification(ET3Notification.builder()
                    .stage(executeContext.getStage())
                    .level(NotificationType.ERROR)
                    .message(MessageManager.getInstance()
                            .getMessage(CoreMessages.CORE_ERR_0038, customName, commandInfo.getId()))
                    .build());
            return result;
        }

        for (CommandSpecStructure css : commandSpec.getStructures().values()) {
            try {

                Class modelClazz = commandInfo.getModel();
                Field structure = modelClazz.getDeclaredField(css.getName());

                // 設計定義の型
                StructureType type = StructureType.valueOfByValue(css.getType());

                // 実際の実装型
                Class implType = structure.getType();

                switch (type) {
                    case STRING:
                        if (!String.class.isAssignableFrom(implType)) {
                            result.add(CommandSpecStructureValidateError
                                    .commandSpecStructureValidateErrorBuilder()
                                    .stage(executeContext.getStage())
                                    .level(NotificationType.ERROR)
                                    .customName(customName)
                                    .structureName(css.getName())
                                    .message(MessageManager.getInstance().getMessage(
                                            CoreMessages.CORE_ERR_0026, customName, commandInfo.getId(), css.getName()
                                    )).build());
                        }
                        break;
                    case NUMBER:
                        if (!Number.class.isAssignableFrom(implType)) {
                            result.add(CommandSpecStructureValidateError
                                    .commandSpecStructureValidateErrorBuilder()
                                    .stage(executeContext.getStage())
                                    .customName(customName)
                                    .structureName(css.getName())
                                    .message(MessageManager.getInstance().getMessage(
                                            CoreMessages.CORE_ERR_0027, customName, commandInfo.getId(), css.getName()
                                    )).build());
                        }
                        break;
                    case BOOLEAN:
                        if (!Boolean.class.isAssignableFrom(implType)) {
                            result.add(CommandSpecStructureValidateError
                                    .commandSpecStructureValidateErrorBuilder()
                                    .stage(executeContext.getStage())
                                    .customName(customName)
                                    .structureName(css.getName())
                                    .message(MessageManager.getInstance().getMessage(
                                            CoreMessages.CORE_ERR_0028, customName, commandInfo.getId(), css.getName()
                                    )).build());
                        }
                        break;
                    case ARRAY:
                        if (!implType.isArray() && !List.class.isAssignableFrom(implType)) {
                            result.add(CommandSpecStructureValidateError
                                    .commandSpecStructureValidateErrorBuilder()
                                    .stage(executeContext.getStage())
                                    .customName(customName)
                                    .structureName(css.getName())
                                    .message(MessageManager.getInstance().getMessage(
                                            CoreMessages.CORE_ERR_0029, customName, commandInfo.getId(), css.getName()
                                    )).build());
                        }
                        break;
                    case OBJECT:
                        if (implType.isPrimitive()) {
                            result.add(CommandSpecStructureValidateError
                                    .commandSpecStructureValidateErrorBuilder()
                                    .stage(executeContext.getStage())
                                    .customName(customName)
                                    .structureName(css.getName())
                                    .message(MessageManager.getInstance().getMessage(
                                            CoreMessages.CORE_ERR_0030, customName, commandInfo.getId(), css.getName()
                                    )).build());
                        }
                        break;
                    default:
                        break;
                }


            } catch (NoSuchFieldException e) {
                result.add(CommandSpecStructureValidateError
                        .commandSpecStructureValidateErrorBuilder()
                        .stage(executeContext.getStage())
                        .level(NotificationType.ERROR)
                        .error(e)
                        .customName(customName)
                        .structureName(css.getName())
                        .message(MessageManager.getInstance().getMessage(
                                CoreMessages.CORE_ERR_0031, customName, commandInfo.getId(), css.getName())).build());
            }
        }
        return result;
    }

}
