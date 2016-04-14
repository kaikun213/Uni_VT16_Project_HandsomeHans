class AdminClass{

    String pWord;
    String uName;
    String eMail;

    public AdminClass(){
        pWord = "";
        uName = "";
        eMail = "";
    }

    public AdminClass(String pWord, String uName, String eMail){
        this.pWord = pWord;
        this.name = uName;
        this.eMail = eMail;

    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String geteMail() {
        return eMail;
    }
}