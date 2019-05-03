package com.epion_t3.core.common.bean.scenario;

import com.epion_t3.core.common.annotation.OriginalProcessField;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

import java.io.Serializable;

//@Getter
//@Setter
public class Command implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty
    @OriginalProcessField
    private String id;

    @OriginalProcessField
    private String summary;

    @OriginalProcessField
    private String description;

    @NotEmpty
    @OriginalProcessField
    private String command;

    @OriginalProcessField
    private String target;

    @OriginalProcessField
    private String value;

    @OriginalProcessField
    private ProcessReference ref;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ProcessReference getRef() {
        return ref;
    }

    public void setRef(ProcessReference ref) {
        this.ref = ref;
    }
}
