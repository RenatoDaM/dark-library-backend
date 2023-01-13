package com.dark.library.darklibrary.request;

import com.dark.library.darklibrary.model.BookTypeModel;
import com.dark.library.darklibrary.model.GodModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GodRequest {

    private Integer godId;
    @NotNull
    @NotBlank
    private String godName;
    @NotNull
    private Integer godType;

    public Integer getGodId() {
        return godId;
    }

    public void setGodId(Integer godId) {
        this.godId = godId;
    }

    public String getGodName() {
        return godName;
    }

    public void setGodName(String godName) {
        this.godName = godName;
    }

    public Integer getGodType() {
        return godType;
    }

    public void setGodType(Integer godType) {
        this.godType = godType;
    }

    public GodModel convertToEntity(GodModel godModel) {
        godModel.setGodName(this.godName);
        godModel.setGodType(this.godType);
        godModel.setGodId(this.godId);
        return godModel;
    }
}
