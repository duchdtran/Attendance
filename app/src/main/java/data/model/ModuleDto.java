package data.model;

import java.util.List;

public class ModuleDto {

    private int moduleID;
    private String nameModule;
    private String description;


    private List<UserModuleDto> listModule;
    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public String getNameModule() {
        return nameModule;
    }

    public void setNameModule(String nameModule) {
        this.nameModule = nameModule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserModuleDto> getListModule() {
        return listModule;
    }

    public void setListModule(List<UserModuleDto> listModule) {
        this.listModule = listModule;
    }
}
