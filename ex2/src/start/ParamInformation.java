package start;

public class ParamInformation {

    public String name;
    public Boolean value = false;


    public ParamInformation(){

    }

    public ParamInformation(String _name, Boolean _value){
        this.name = _name;
        this.value= _value;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }



}
