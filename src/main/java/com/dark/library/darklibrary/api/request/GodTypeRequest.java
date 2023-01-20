package com.dark.library.darklibrary.api.request;

import com.dark.library.darklibrary.domain.model.GodTypeModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GodTypeRequest {

    private Integer godTypeId;
    @NotNull
    @NotBlank
    private String godTypeName;

    public Integer getGodTypeId() {
        return godTypeId;
    }

    public void setGodTypeId(Integer godTypeId) {
        this.godTypeId = godTypeId;
    }

    public String getGodTypeName() {
        return godTypeName;
    }

    public void setGodTypeName(String godTypeName) {
        this.godTypeName = godTypeName;
    }

    public GodTypeModel convertToEntity(GodTypeModel godTypeModel) {
        godTypeModel.setGodTypeId(this.godTypeId);
        godTypeModel.setGodTypeName(this.godTypeName);
        return godTypeModel;
    }
}
