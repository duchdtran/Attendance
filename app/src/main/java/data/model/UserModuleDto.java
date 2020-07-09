package data.model;




public class UserModuleDto {
   
    private int userModuleID;
    private ModuleDto moduleDto;
    private UserDto userDto;

    public int getUserModuleID() {
        return userModuleID;
    }

    public void setUserModuleID(int userModuleID) {
        this.userModuleID = userModuleID;
    }

    public ModuleDto getModuleDto() {
        return moduleDto;
    }

    public void setModuleDto(ModuleDto moduleDto) {
        this.moduleDto = moduleDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
